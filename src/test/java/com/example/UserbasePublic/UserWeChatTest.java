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
public class UserWeChatTest  extends AbstractTestNGSpringContextTests{

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;//数据库取数据用

    private static String AppId="Appid01";
    private static Integer ChannelId=1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @Test(description = "1.微信绑定" +
            "            2.微信解绑 ")
    public void bindingAndunBinding(){
        String openId= DataUtils.getRandomString(9);    //随机生成openId
        String ChannelUserId=String.valueOf((int)((Math.random()*9+1)*1000)); //随机生成ChannelUserId
        try {
            httpClient = HttpClients.createDefault();
            //微信绑定
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/binding","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userWeChatAuthRequest(AppId,ChannelId,ChannelUserId,openId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            String bindResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals(bindResponseMsg,"RESP_CODE_SUCCESS");
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"WeChatInfoBind",ChannelUserId,ChannelUserId);

            //解除绑定
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/unBinding","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userWeChatAuthUnBindRequest(openId,ChannelId,ChannelUserId,AppId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String unbindResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals(unbindResponseMsg,"RESP_CODE_SUCCESS");
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"WeChatInfoUnbind","null",ChannelUserId);

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

            //微信绑定
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/loginByOneKey","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.userWeChatOneKeyLoginRequest(ChannelId,"17702015334","177417","86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
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

    @Test(description = "根据微信ID和OPENID获取用户信息")
    public void getByOpenId() {
        try {

            httpClient=HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/info/pd/get/by/unionId/openId","");
            System.out.println(uri);
            post = new HttpPost(uri);;
            byteArrayEntity = DataTransferUtil.userInfoUnionIdOpenIdRequestConvertBuilder(ChannelId, "ox-FY1f0_ub3FnM_v9n7ITb1q-f0", "oBrt31Sg6EqD9DJxB0Mz9EOl-Pp4");
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

    @Test(description = "根据渠道用户Id查询用户微信列表信息 X")
    public void getWeChatByChannelUserId(){
        try{

            httpClient=HttpClients.createDefault();
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/weChat/getWeChatByChannelUserId","");
            post = new HttpPost(uri);;
            byteArrayEntity =  DataTransferUtil.getUserWeChatAuthByChannelUserIdRequest(ChannelId,"9692091","123");
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












}
