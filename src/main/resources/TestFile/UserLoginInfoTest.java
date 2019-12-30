package com.hs.user.base;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserLoginInfoServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

public class UserLoginInfoTest {
    private CloseableHttpClient httpClient;
    private static final String SCHEME = "http";
    private static final String HOST = "127.0.0.1";
    private static final int POST = 8080;
   /*private static final String HOST = "172.18.0.112";
    private static final int POST = 8080;*/
    @Test
    public void userLoginTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/login", "", null);
            HttpPost post = new HttpPost(uri);
            UserLoginInfoServiceProto.UserLoginInfoRequest.Builder builder = UserLoginInfoServiceProto.UserLoginInfoRequest.newBuilder();
            builder.setMobileAreaCode("86");
            builder.setMobile("11457692089");
            builder.setPwd("2D6A2582369542424C50C24D24EE4FF3");
            builder.setChannelId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS &&resp.getData().is(UserLoginInfoServiceProto.UserLoginInfo.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserLoginInfoServiceProto.UserLoginInfo.class)));
                } else {
                    System.out.println("result:" + resp.getCode().name());
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void userForgetPwdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/forget/pwd", "", null);
            HttpPost post = new HttpPost(uri);
            UserLoginInfoServiceProto.UserForgetPwdRequest.Builder builder = UserLoginInfoServiceProto.UserForgetPwdRequest.newBuilder();
           /* builder.setMobileAreaCode("86");
            builder.setMobile("11457692089");
            builder.setLoginPwd("2D6A258236114576920899542424C50C24D24EE4FF3");*/
            //builder.setLoginPwd("D7072549B3B97972239FBA84E8437BAF");
            builder.setLoginPwd("D7072549B3B97972239FBA84E8437BAF");
            builder.setMobile("13711598682");
            builder.setChannelId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("result:" + resp.getCode());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void userPwdUpdateChannelIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/pwd/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserLoginInfoServiceProto.UserPwdUpdateRequest.Builder builder = UserLoginInfoServiceProto.UserPwdUpdateRequest.newBuilder();
            builder.setChannelId(0);

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserLoginInfoServiceProto.UserUpdatePwdResponse.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserLoginInfoServiceProto.UserUpdatePwdResponse.class)));
                } else {
                    System.out.println("result:" + resp.getCode().name());
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void userPwdUpdateChannelUserIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/pwd/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserLoginInfoServiceProto.UserPwdUpdateRequest.Builder builder = UserLoginInfoServiceProto.UserPwdUpdateRequest.newBuilder();
            builder.setChannelUserId("");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserLoginInfoServiceProto.UserUpdatePwdResponse.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserLoginInfoServiceProto.UserUpdatePwdResponse.class)));
                } else {
                    System.out.println("result:" + resp.getCode().name());
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void userPwdUpdateUser_not_existTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/pwd/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserLoginInfoServiceProto.UserPwdUpdateRequest.Builder builder = UserLoginInfoServiceProto.UserPwdUpdateRequest.newBuilder();
            builder.setLoginPwdNew("D7072549B3B97972239FBA84E8437BAF");
            builder.setChannelId(1);
            builder.setChannelUserId("56903884760");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserLoginInfoServiceProto.UserUpdatePwdResponse.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserLoginInfoServiceProto.UserUpdatePwdResponse.class)));
                } else {
                    System.out.println("result:" + resp.getCode().name());
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void userPwdUpdateTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/pwd/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserLoginInfoServiceProto.UserPwdUpdateRequest.Builder builder = UserLoginInfoServiceProto.UserPwdUpdateRequest.newBuilder();
            builder.setLoginPwdNew("2D6A2582369542424C50C24D24EE4FF3");
            builder.setChannelId(1);
            builder.setChannelUserId("178803");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserLoginInfoServiceProto.UserUpdatePwdResponse.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserLoginInfoServiceProto.UserUpdatePwdResponse.class)));
                } else {
                    System.out.println("result:" + resp.getCode().name());
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void userPwdUpdateLoginPwdNewTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/pwd/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserLoginInfoServiceProto.UserPwdUpdateRequest.Builder builder = UserLoginInfoServiceProto.UserPwdUpdateRequest.newBuilder();
            builder.setLoginPwdNew("");
            builder.setChannelId(1);
            builder.setChannelUserId("178803");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserLoginInfoServiceProto.UserUpdatePwdResponse.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserLoginInfoServiceProto.UserUpdatePwdResponse.class)));
                } else {
                    System.out.println("result:" + resp.getCode().name());
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Before
    public void before(){
        httpClient = HttpClients.createDefault();
    }

    @After
    public void after() throws IOException {
        httpClient.close();
    }
}
