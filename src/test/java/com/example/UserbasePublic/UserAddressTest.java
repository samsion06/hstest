package com.example.UserbasePublic;

import com.example.mapper.UserBaseInfoMapper;
import com.example.utils.*;
import com.hs.user.base.proto.UserAddressServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;

import java.net.URI;
import java.util.Map;

public class UserAddressTest {
    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;

    private static Integer channelId=1;
    private static String address="广州海珠区你老母2号";
    private static String addressid="774195ceb7ce455b95c69d2beb1f5723";
    private static String username="xiaoming";

    private static CloseableHttpClient httpClient ;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @Test(description = "1.添加收货地址" +
            "          2.获取收货地址" +
            "          3.更新收货地址"+
            "          4.删除收货地址" )
    public void address(){
        httpClient=HttpClients.createDefault();
        String address= DataUtils.getRandomString(9);
        String ChannelUserId=String.valueOf((int)((Math.random()*9+1)*1000));
        try{
            //添加收货地址
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/address/add","");
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAddressInfoAddRequest(ChannelUserId,channelId,address);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            //返回对象信息得话已经是从数据库取了一次了,不用数据库再判断
            String addaddressResponseMsg = CheckReponseResult.AssertResponses(response, UserAddressServiceProto.UserAddressInfoResponse.class);
            //截取addressId传入下一个接口
            String addressId = DataUtils.substring(addaddressResponseMsg, "addressId:\"", 16, "\",", 0);
            Reporter.log("截取addressId传入下个接口："+addressId);
            //获取收货地址
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/address/getByAddressId","");
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAddressRequest(ChannelUserId,channelId,addressId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserAddressServiceProto.UserAddressInfoResponse.class);

            //更新收货地址
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/address/update", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAddressInfoUpdateRequest(ChannelUserId,channelId,addressid,username);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponse(response);
            //删除收货地址
            uri = new URI(HttpConfig.scheme, null, HttpConfig.url, HttpConfig.port, "/address/delete", "", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAddressRequest(ChannelUserId,channelId,addressid);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponse(response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //@org.testng.annotations.Test(description ="分页查询用户收货地址列表")
    public void test2(){
        try{
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/address/query", null);
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserAddressPageRequest("17702015334",channelId,1,1);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.checkResponseCodeAndObj(response,UserAddressServiceProto.UserAddressPage.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    }



























