package com.example.UserbasePublic;

import com.example.mapper.UserBaseInfoMapper;
import com.example.utils.*;
import com.hs.user.base.proto.UserBaseServiceProto;
import com.hs.user.base.proto.UserWeChatAuthServiceProto;
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

@SpringBootTest
public class UserWeChatTest extends AbstractTestNGSpringContextTests{

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;//数据库取数据用

    private static String AppId="Appid01";
    private static Integer channelId =1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @Test(description = "1.微信绑定" +
            "            2.微信解绑 ")
    public void bindingAndunBinding(){
        String openId= DataUtils.getRandomString(9);    //随机生成openId
        String channelUserId=String.valueOf((int)((Math.random()*9+1)*1000)); //随机生成ChannelUserId
        try {
            httpClient = HttpClients.createDefault();
            //微信绑定
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/binding","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userWeChatAuthRequest(AppId, channelId,channelUserId,openId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String bindResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",bindResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"WeChatInfoBind",channelUserId,channelUserId);

            //解除绑定
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/unBinding","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userWeChatAuthUnBindRequest(openId, channelId,channelUserId,AppId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String unbindResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",unbindResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"WeChatInfoUnbind","null",channelUserId);

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Test(description = "用户一键登录微信")
    public void loginByOneKey(){ ;
        try{

            //用户一键登录微信
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/loginByOneKey","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userWeChatOneKeyLoginRequest(channelId,"17702015334","177417","86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class);

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

    @Test(description = "用户微信登录(幂等)" +
                        "根据openId查询用户微信列表信息 ")
    public void weChatLoginAndGetInfoByOpenId(){
        try{
            String openId="oBrt31Sg6EqD9DJxB0Mz9EOl-Pp4";
            String appId="Appid01";
            //用户微信登录(幂等)
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/login","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.UserWeChatAuthLoginRequest(channelId,"3692091",openId,appId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserWeChatAuthServiceProto.UserWeChatAuthLoginResponse.class);

            //根据openId查询用户微信列表信息
            httpClient=HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/getWeChatByOpenId","");
            post = new HttpPost(uri);;
            byteArrayEntity =  DataTransferUtil.getUserWeChatAuthByOpenIdRequest(channelId,openId,appId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class);

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

    @Test(description = "检查手机号绑定")
    public void checkPhoneTest(){


    }
}
