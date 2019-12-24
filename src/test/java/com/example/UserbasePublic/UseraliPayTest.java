package com.example.UserbasePublic;

import com.example.mapper.UserBaseInfoMapper;
import com.example.utils.*;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserAliPayAuthServiceProto;
import com.hs.user.base.proto.UserIdCardIdentifyServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;

@SpringBootTest
public class UseraliPayTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;

    private static Integer channelId=1;
    private static CloseableHttpClient httpClient;
    private static URI uri ;
    private static HttpPost post ;
    private static HttpResponse response ;
    private static ByteArrayEntity byteArrayEntity ;

    @org.testng.annotations.Test(description = "1.绑定支付宝" +
            "                              2.用户支付宝授权" +
            "                              3.用户支付宝取消授权 OK")
    public void BindAndAuthAndCancel(){
        //生成随机数
        String channelUserId=String.valueOf((int)((Math.random()*9+1)*1000));
        String alipayUserId=String.valueOf((int)((Math.random()*9+1)*1000));
        String alipayRealname= DataUtils.getRandomString(9);
        String alipayAccount="177"+(int)((Math.random()*9+1)*10000000);
        try {
            httpClient= HttpClients.createDefault();
            //绑定支付宝
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/aliPay/binding","");
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAliPayBidingRequest(channelUserId,channelId,alipayRealname,alipayAccount,alipayUserId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            String bindResponseMsg = CheckReponseResult.AssertResponse(response);
            if(bindResponseMsg.equals("RESP_CODE_SUCCESS")){
                CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"AliPayBind","1",channelUserId);
            }else{
                System.out.println(bindResponseMsg);
            }
            //用户支付宝授权
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/aliPay/auth","");
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAliPayAuthRequest(channelUserId,channelId,alipayUserId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponse(response);
            if(bindResponseMsg.equals("RESP_CODE_SUCCESS")){
                CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"AliPayAuth","1",channelUserId);
            }else{
                System.out.println(bindResponseMsg);
            }
            //用户取消授权
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/aliPay/auth/cancel","");
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAliPayAuthCancelRequest(channelUserId,channelId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String unbindResponseMsg = CheckReponseResult.AssertResponse(response);
            if(unbindResponseMsg.equals("RESP_CODE_SUCCESS")){
                CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"AliPayCancel","1",channelUserId);
            }else{
                System.out.println(bindResponseMsg);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
    }

   @Test(description = "用户支付宝授权信息查询")
    public void authGetInfo(){
        String channeluserId="2571";
        try {
            httpClient= HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/aliPay/auth/info","");
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAliPayAuthInfoRequest(channeluserId, channelId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
            UserAliPayAuthServiceProto.UserAliPayAuthInfoResponse resp=  UserAliPayAuthServiceProto.UserAliPayAuthInfoResponse.parseFrom(response.getEntity().getContent());
            Reporter.log(resp.toString());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
    }

//        @org.testng.annotations.Test(description = "1.实名认证" +
//                    "                               2.实名认证查询")
    public void test6(){
        String channeluserId="2571";
        try {
//            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/user/idCard/identify","");
//            post = new HttpPost(uri);
//            byteArrayEntity = ConvertData.UserIdCardIdentifyRequestConvertBuilder(channeluserId, 1, "向亚运","431224199009227572","http://www.baidu.com");
//            post.setEntity(byteArrayEntity);
//            post.setHeader("Content-Type", "application/x-protobuf");
//            response = httpClient.execute(post);
//            CheckReponseResult.AssertResponse(response);
            httpClient= HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/user/idCard/queryStatus","");
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserIdCardStatusQueryRequestConvertBuilder(channeluserId, channelId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserIdCardIdentifyServiceProto.UserIdCardIdentifyInfo.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
            }
        }
    }
}




