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
import java.io.IOException;
import java.net.URI;

//https://blog.csdn.net/qq_16605855/article/details/81183990  testNg集成springboot框架
@SpringBootTest
public class UserbaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
   private UserBaseInfoMapper userBaseInfoMapper;//数据库取数据用

   private static CloseableHttpClient httpClient = HttpClients.createDefault();
   private static ByteArrayEntity byteArrayEntity ;
   private static URI uri ;
   private static HttpPost post;
   private static HttpResponse response;

         @org.testng.annotations.Test(description = "1.用户登录" +
                    "                            2.修改昵称" +
                    "                             3.修改头像")
    public void LoginAndUpdate(){
             String mobile="17702015335";
             String pwd="123456";
             String nickname=DataUtils.getRandomString(9);//随机生成用户名
             //注册后user_base_info,user_login_info,hsrj_user_info 三个表都会有数据,
             //user_base_info登录得时候的mobile_area_code有值就要传递
        try {
            //登录
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/base/user/info/pd/login","");
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserInfoPdLoginRequestConvertBuilder(1, mobile, pwd,"86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String result = CheckReponseResult.AssertResponses(response, UserBaseServiceProto.userInfoPdCombine.class);
            String channelUserId = DataUtils.substring(result, "userId", 10, ",", 1);
            System.out.println(channelUserId);

            //修改昵称
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/base/user/nick/name/update","");
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserNickNameUpdateRequestConvertBuilder(1, channelUserId, nickname);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponse(response);
            //修改头像
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/base/user/head/img/update", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserHeadImgUpdateRequestConvertBuilder(1, channelUserId, "http://images.huasheng100.com/public/1565334681520252.jpg");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponse(response);

            System.out.println(mobile);


            //修改手机号
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/base/user/mobile/update", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserMobileUpdateRequestConvertBuilder(1, mobile.toString(), channelUserId,"86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponse(response);
            //转换密码成md5

            String md5pwd=MD5Util.toMD5(pwd.toString()).toUpperCase();
            //修改密码
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/user/pwd/update", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserPwdUpdateRequestConvertBuilder(channelUserId,1,md5pwd);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponse(response);
            //再次登录
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/base/user/info/pd/login", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserInfoPdLoginRequestConvertBuilder(1, mobile.toString(), pwd.toString(),"86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponses(response, UserBaseServiceProto.userInfoPdCombine.class);
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

    @org.testng.annotations.Test(description = "根据邀请码获取用户信息")
    public void test9(){
        try{
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/base/get/by/invite/code", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserInviteCodeQueryRequest("p88vcdo",1);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCodeAndObj(response, UserBaseServiceProto.UserInfoInviteCodeResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


}