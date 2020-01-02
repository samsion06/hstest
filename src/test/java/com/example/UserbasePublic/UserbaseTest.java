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

//https://blog.csdn.net/qq_16605855/article/details/81183990(testNg集成SpringBoot)
@SpringBootTest
public class UserbaseTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;//数据库取数据用

    private static Integer channelId=1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @Test(description = "1.用户登录" +
                     "   2.获取用户基础信息" +
                     "   3.修改昵称" +
                     "   4.修改头像")
    public void LoginAndUpdate() {
        String mobile = "17720130632"; //3692091
        String pwd = "123456";
        String nickname = DataUtils.getRandomString(9);//随机生成用户名
        String headimgurl = DataUtils.getRandomString(15);//随机生成用户名
        //注册后user_base_info,user_login_info,hsrj_user_info 三个表都会有数据,user_base_info登录得时候的mobile_area_code有值就要传递
        try {
            httpClient = HttpClients.createDefault();

            //登录
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/info/pd/login", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userInfoPdLoginRequestConvertBuilder(channelId, mobile, pwd, "86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String result = CheckReponseResult.AssertResponses(response, UserBaseServiceProto.userInfoPdCombine.class);
            String ChannelUserId = DataUtils.substring(result, "userId", 10, ",", 1);
            System.out.println(ChannelUserId);

            //获取用户基础信息
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/getUserBaseInfo","");
            System.out.println(uri);
            post = new HttpPost(uri);;
            byteArrayEntity = DataTransferUtil.UserInfoRequest(channelId, ChannelUserId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.UserBaseInfo.class);

            //修改昵称
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/nick/name/update", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userNickNameUpdateRequestConvertBuilder(channelId, ChannelUserId, nickname);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String nickNameResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS", nickNameResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper, "NickNameUpdate", nickname, ChannelUserId);

            //修改头像
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/head/img/update", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userHeadImgUpdateRequestConvertBuilder(channelId, ChannelUserId, headimgurl);
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

    @Test(description = "1.根据邀请码获取用户信息" +
                        "2.根据邀请码获取用户信息(幂等)")
    public void getInfoByInviteCode() {
        try {

            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/get/by/invite/code", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userInviteCodeQueryRequest("p88vcdo", channelId,"UserInfoInviteCodeResponse");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.UserInfoInviteCodeResponse.class);

            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/info/pd/get/by/invite/code", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userInviteCodeQueryRequest("p88vcdo", channelId,"userInfoPdCombine");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.userInfoPdCombine.class);


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

    @Test(description = "1.根据手机号码获取用户信息" +
                        "2.根据手机号码获取用户信息（聚合）")
    public void getUserInfoByMobile(){
        try{

            //规则：userbase里面得手机号和要在映射表userindx里面得indexid有才行
            //根据手机号码获取用户信息
            String mobile="18756989065";
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/getUserInfoByMobile", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserInfoByMobileRequest(mobile,"86",channelId,"UserBaseInfo");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserBaseServiceProto.UserBaseInfo.class);

            //根据手机号码获取用户信息（聚合）
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/info/pd/get/by/mobile", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserInfoByMobileRequest(mobile,"86",channelId,"userInfoPdCombine");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserBaseServiceProto.userInfoPdCombine.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "1.修改手机号" +
                        "2.修改密码" +
                        "3.登录")
    public void mdfMobileAndPwdUpdate(){
        String ChannelUserId="178803"; //17786709004
        try{
            httpClient = HttpClients.createDefault();
            String mobile="177"+(int)((Math.random()*9+1)*10000000); //修改登录得手机号
            //修改手机号
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/mobile/update", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userMobileUpdateRequestConvertBuilder(channelId, mobile, ChannelUserId, "86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String mobileResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",mobileResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"MobileUpdate",mobile,ChannelUserId);

            //178803 将密码转换成MD5加密方式
            String pwd="123456";
            String md5pwd = MD5Util.toMD5(pwd.trim().toUpperCase());
            System.out.println(md5pwd);
            //修改登录密码
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/pwd/update", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userPwdUpdateRequestConvertBuilder(ChannelUserId, channelId, md5pwd);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String pwdResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",pwdResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"PwdUpdate",md5pwd,ChannelUserId);

            //再次登录
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/info/pd/login", "");
            post = new HttpPost(uri);
            System.out.println("mobile"+mobile+"pwd"+pwd);
            byteArrayEntity = DataTransferUtil.userInfoPdLoginRequestConvertBuilder(channelId, mobile, pwd, "86");
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


    @Test(description = "忘记密码(修改密码)")
    public void forgetPassword(){
        try{

            String loginPwd="123456";
            String md5pwd = MD5Util.toMD5(loginPwd.trim().toUpperCase());
            //用户忘记登录密码
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/user/forget/pwd", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userForgetPwdRequest(channelId,md5pwd,"15053755782","86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            //校验结果
            CheckReponseResult.AssertResponse(response);

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "根据查询条件查询用户列表")
    public void getUsersByConditionTest(){
        try{

            //根据查询条件查询用户列表
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/getUsersByCondition", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserBaseInfoByConditionRequest("3693070",channelId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            //校验结果
            CheckReponseResult.AssertResponses(response,UserBaseServiceProto.UserBaseInfoByConditionPage.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description = "根据微信ID和OPENID获取用户信息")
    public void getByOpenIdAndUnionldId() {
        try {

            httpClient=HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/info/pd/get/by/unionId/openId","");
            System.out.println(uri);
            post = new HttpPost(uri);;
            byteArrayEntity = DataTransferUtil.userInfoUnionIdOpenIdRequestConvertBuilder(channelId, "ox-FY1f0_ub3FnM_v9n7ITb1q-f0", "oBrt31Sg6EqD9DJxB0Mz9EOl-Pp4");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.userInfoPdCombine.class);

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

    @Test(description = "修改用户身份状态")
    public void userStatusUpdate(){

        try{
            httpClient=HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/status/update ","");
            System.out.println(uri);
            post = new HttpPost(uri);;
            byteArrayEntity = DataTransferUtil.UserStatusUpdateRequest(channelId, "ox-FY1f0_ub3FnM_v9n7ITb1q-f0", "oBrt31Sg6EqD9DJxB0Mz9EOl-Pp4");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.userInfoPdCombine.class);

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}



