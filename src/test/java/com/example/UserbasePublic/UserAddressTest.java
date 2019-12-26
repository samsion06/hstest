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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.net.URI;

@SpringBootTest
public class UserAddressTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;

    private static Integer channelId=1;
    private static CloseableHttpClient httpClient ;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @Test(description = "1.添加收货地址" +
            "          2.获取收货地址" +
            "          3.更新收货地址"+
            "          4.删除收货地址" )
    public void addressCURD(){
        httpClient=HttpClients.createDefault();
        String address= DataUtils.getRandomString(9);//随机地址
        String ChannelUserId=String.valueOf((int)((Math.random()*9+1)*1000));
        String name= DataUtils.getRandomString(9);//随机用户名
        try{
            //添加收货地址
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/address/add","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransfer.UserAddressInfoAddRequest(ChannelUserId,channelId,address);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            //返回对象信息得话已经是从数据库取了一次了,不用数据库再判断
            String addaddressResponseMsg = CheckReponseResult.AssertResponses(response, UserAddressServiceProto.UserAddressInfoResponse.class);
            //截取addressId传入下一个接口
            String addressId = DataUtils.substring(addaddressResponseMsg, "addressId:\"", 16, "\",", 0);

            //获取收货地址
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/address/getByAddressId","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransfer.UserAddressRequest(ChannelUserId,channelId,addressId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserAddressServiceProto.UserAddressInfoResponse.class);

            //更新收货地址
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/address/update","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransfer.UserAddressInfoUpdateRequest(ChannelUserId,channelId,addressId,name);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String updateResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",updateResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"AddressUpadate",name,ChannelUserId);

            //删除收货地址
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/address/delete","");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransfer.UserAddressDelete(ChannelUserId,channelId,addressId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String deleteResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",deleteResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"AddressDelete","1",ChannelUserId);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test(description ="分页查询用户收货地址列表")
    public void addressQuery(){
        try{
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/address/query", null);
            post = new HttpPost(uri);
            byteArrayEntity = DataTransfer.UserAddressPageRequest("17702015334",channelId,1,1);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserAddressServiceProto.UserAddressPage.class);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    }



























