package com.example.UserbasePublic;

import com.example.mapper.UserBaseInfoMapper;
import com.example.utils.*;
import com.hs.user.base.proto.UserBaseServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;

//https://blog.csdn.net/qq_16605855/article/details/81183990  testNg集成springboot框架
@SpringBootTest
public class UserbaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;//数据库取数据用

    private static Integer ChannelId = 1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @Test(description = "1.用户登录" +
                     "   2.修改昵称" +
                     "   3.修改头像")
    public void LoginAndUpdate() {
        String mobile = "17720130632";
        String pwd = "123456";
        String nickname = DataUtils.getRandomString(9);//随机生成用户名
        String headimgurl = DataUtils.getRandomString(15);//随机生成用户名
        //注册后user_base_info,user_login_info,hsrj_user_info 三个表都会有数据,user_base_info登录得时候的mobile_area_code有值就要传递
        try {
            httpClient = HttpClients.createDefault();

            //登录
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/base/user/info/pd/login", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransfer.userInfoPdLoginRequestConvertBuilder(ChannelId, mobile, pwd, "86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String result = CheckReponseResult.AssertResponses(response, UserBaseServiceProto.userInfoPdCombine.class);
            String ChannelUserId = DataUtils.substring(result, "userId", 10, ",", 1);
            System.out.println(ChannelUserId);

            //修改昵称
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/base/user/nick/name/update", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransfer.userNickNameUpdateRequestConvertBuilder(ChannelId, ChannelUserId, nickname);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String nickNameResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS", nickNameResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper, "NickNameUpdate", nickname, ChannelUserId);

            //修改头像
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/base/user/head/img/update", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransfer.userHeadImgUpdateRequestConvertBuilder(ChannelId, ChannelUserId, headimgurl);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String headUrlImg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS", headUrlImg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper, "HeadUrlImg", headimgurl, ChannelUserId);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test(description = "根据邀请码获取用户信息")
    public void getInfoByInviteCode() {
        try {

            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/base/get/by/invite/code", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransfer.userInviteCodeQueryRequest("p88vcdo", 1);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.UserInfoInviteCodeResponse.class);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test(description = "修改手机号-修改密码-登录")
    public void LogiNameAndPwdUpdate(){
        String ChannelUserId="178803";
        try{
            httpClient = HttpClients.createDefault();
            String mobile="177"+(int)((Math.random()*9+1)*10000000); //修改登录得手机号
            //修改手机号
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/base/user/mobile/update", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransfer.userMobileUpdateRequestConvertBuilder(1, mobile, ChannelUserId, "86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String mobileResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",mobileResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"MobileUpadate",mobile,ChannelUserId);

            //178803 将密码转换成MD5加密方式
            String pwd="123456";
            String md5pwd = MD5Util.toMD5(pwd.trim().toUpperCase());
            System.out.println(md5pwd);
            //修改登录密码
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/user/pwd/update", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransfer.userPwdUpdateRequestConvertBuilder(ChannelUserId, 1, md5pwd);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String pwdResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",pwdResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"PwdUpdate",md5pwd,ChannelUserId);

            //再次登录
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/base/user/info/pd/login", "");
            post = new HttpPost(uri);
            System.out.println("mobile"+mobile+"pwd"+pwd);
            byteArrayEntity = DataTransfer.userInfoPdLoginRequestConvertBuilder(ChannelId, mobile, pwd, "86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.userInfoPdCombine.class);

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    //@Test(description = "忘记密码")
    public void forgetPassword(){
        try{

            //用户忘记登录密码
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/user/forget/pwd", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransfer.userForgetPwdRequest(ChannelId,"123456","17720130632","86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            //校验结果
            CheckReponseResult.AssertResponse(response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //@Test(description = "注册")
    public void register() {
        //生成手机号码
        String mobile="177"+(int)((Math.random()*9+1)*10000000);
        String ChannelUserId=String.valueOf((int)((Math.random()*9+1)*1000));
        try{
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/base/user/register", "");
            post = new HttpPost(uri);
            DataTransfer.userBaseRegisterRequestConvertBuilder(mobile);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserBaseServiceProto.UserBaseInfo.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



