package com.hs.user.base;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserWeChatAuthServiceProto;
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

public class UserWeChatAuthTest {
    private CloseableHttpClient httpClient;
    private static final String SCHEME = "http";
    private static final String HOST = "127.0.0.1";
    private static final int POST = 8080;

    @Test
    public void loginTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/login", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthLoginRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthLoginRequest.newBuilder();
            //builder.setOpenId("oBrt31QHfJmv8qRr0J218FABVYac");
            builder.setUnionId("ox-FY1aglpGDHfZR93I0UcQtaq0c");
            builder.setChannelUserId("34234234324");
            builder.setChannelId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                JsonFormat jsonFormat =new JsonFormat();
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                UserWeChatAuthServiceProto.UserWeChatAuthLoginResponse resp ;
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
                if (resultSet.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resultSet.getData().is(UserWeChatAuthServiceProto.UserWeChatAuthLoginResponse.class)) {
                    resp = resultSet.getData().unpack(UserWeChatAuthServiceProto.UserWeChatAuthLoginResponse.class);
                    System.out.println("result:" +jsonFormat.printToString(resp));
                }

            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void loginK8sTest(){
        try {
            URI uri = new URI(SCHEME, null, "172.18.0.112", 8080,"/weChat/login", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthLoginRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthLoginRequest.newBuilder();
            builder.setOpenId("oBrt31QHfJmv8qRr0J218FABVYac");
            builder.setChannelUserId("11550108");
            builder.setChannelId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                UserWeChatAuthServiceProto.UserWeChatAuthLoginResponse resp ;
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
                if (resultSet.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resultSet.getData().is(UserWeChatAuthServiceProto.UserWeChatAuthLoginResponse.class)) {
                    resp = resultSet.getData().unpack(UserWeChatAuthServiceProto.UserWeChatAuthLoginResponse.class);
                    System.out.println("result:" + resp.getNickName());
                    System.out.println("result:" + resp.getHeadImgUrl());
                    System.out.println("result:" + resp.getOpenId());
                }

            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void bindChannelIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setChannelId(0);

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void bindChannelUserIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setChannelUserId("");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void bindAppidTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setChannelUserId("180805");
            builder.setChannelId(1);
            builder.setAppId("");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void bindOpenIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setChannelUserId("180805");
            builder.setChannelId(1);
            builder.setOpenId("");
            builder.setAppId("1111");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void bindUnionIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setChannelUserId("18080999999");
            builder.setChannelId(1);
            builder.setOpenId("oZmwj1tpZEGa22Qod03OhFxsQfvg22");
            builder.setUnionId("nZmwj1tpZEGa22Qod03OhFxsQfvgunion");
            builder.setAppId("1111");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void bind_not_exist_and_addTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setChannelUserId("111111111");
            builder.setChannelId(1);
            builder.setOpenId("oZmwj1tpZEGa22Qod03OhFxsQfvg");
            builder.setUnionId("1017");
            builder.setAppId("1111");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void bind_exist_and_updateTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setChannelUserId("111111111");
            builder.setChannelId(1);
            builder.setOpenId("oZmwj1tpZEGa22Qod03OhFxsQfvg");
            builder.setUnionId("1017");
            builder.setAppId("1111");
            builder.setSex(1);
            builder.setProvince("广东省2");
            builder.setCity("广州");
            builder.setCountry("中国");
            builder.setHeadImg("/sdfwefw/gwerw/efwe/gwefwef/we");
            builder.setNickName("小小小包");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void bindWriteLogTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setChannelUserId("111111111");
            builder.setChannelId(1);
            builder.setOpenId("oZmwj1tpZEGa22Qod03OhFxsQfvg");
            builder.setUnionId("1017");
            builder.setAppId("1111");
            builder.setSex(2);
            builder.setProvince("广东省212312");
            builder.setCity("广州12312");
            builder.setCountry("中国12321");
            builder.setHeadImg("/sdfwefw/gwerw/efwe/gwefwef/we");
            builder.setNickName("小小小包12321");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void bindTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setOpenId("oZmwj1tpZEGa22Qod03OhFxsQfvg");
            builder.setUnionId("1017");
            builder.setHeadImg("311117");
            builder.setNickName("张dfd疯444323211000");
            builder.setChannelUserId("180805");
            builder.setChannelId(1);
            builder.setSex(1);
            builder.setProvince("广东省2");
            builder.setCity("广州");
            builder.setCountry("中国");
            builder.setAppId("1321312");
            
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    @Test
    public void bindOpenUnionId_OpenId_EmptyTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setOpenId("");
            builder.setUnionId("ox-FY1a6b1ryU7aGP5a2bMr5LulQ");
            builder.setHeadImg("http://images.huasheng100.com/public/1522251839635381.jpg");
            builder.setNickName("钟卓秋");
            builder.setChannelUserId("180805");
            builder.setChannelId(1);
            builder.setSex(1);
            builder.setProvince("广东省");
            builder.setCity("广州");
            builder.setCountry("中国");
            builder.setAppId("1321312");
            
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void bindOpenUnionId_UnionId_EmptyTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setOpenId("oZmwj1qoTUxJ_brArb46vumTclLY");
            builder.setUnionId("");
            builder.setHeadImg("http://images.huasheng100.com/public/1522251839635381.jpg");
            builder.setNickName("钟卓秋");
            builder.setChannelUserId("180805");
            builder.setChannelId(1);
            builder.setSex(1);
            builder.setProvince("广东省");
            builder.setCity("广州");
            builder.setCountry("中国");
            builder.setAppId("1321312");
            
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    @Test
    public void bindOpenUnionId_OpenId_UnionId_EmptyTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setOpenId("");
            builder.setUnionId("");
            builder.setHeadImg("http://images.huasheng100.com/public/1522251839635381.jpg");
            builder.setNickName("钟卓秋");
            builder.setChannelUserId("180805");
            builder.setChannelId(1);
            builder.setSex(1);
            builder.setProvince("广东省");
            builder.setCity("广州");
            builder.setCountry("中国");
            builder.setAppId("1321312");
            
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    
    @Test
    public void bindOpenUnionId_OpenId_Exist_Update_ChannelUserIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setOpenId("oZmwj1qoTUxJ_brArb46vumTclLY");
            //builder.setUnionId("ox-FY1a6b1ryU7aGP5a2bMr5LulQ");
            builder.setHeadImg("http://images.huasheng100.com/public/1522251839635381.jpg");
            builder.setNickName("钟卓秋");
            builder.setChannelUserId("180806");
            builder.setChannelId(1);
            builder.setSex(1);
            builder.setProvince("广东省");
            builder.setCity("广州");
            builder.setCountry("中国");
            builder.setAppId("1321312");
            
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    
    
    @Test
    public void bindOpenUnionId_UnionId_Exist_Update_ChannelUserIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            //builder.setOpenId("oZmwj1qoTUxJ_brArb46vumTclLY");
            builder.setUnionId("ox-FY1a6b1ryU7aGP5a2bMr5LulQ");
            builder.setHeadImg("http://images.huasheng100.com/public/1522251839635381.jpg");
            builder.setNickName("钟卓秋");
            builder.setChannelUserId("180806");
            builder.setChannelId(1);
            builder.setSex(1);
            builder.setProvince("广东省");
            builder.setCity("广州");
            builder.setCountry("中国");
            builder.setAppId("1321312");
            
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void bindOpenUnionId_Exist_Update_ChannelUserIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthRequest.newBuilder();
            builder.setOpenId("oZmwj1qoTUxJ_brArb46vumTclLY");
            builder.setUnionId("ox-FY1a6b1ryU7aGP5a2bMr5LulQ");
            builder.setHeadImg("http://images.huasheng100.com/public/1522251839635381.jpg");
            builder.setNickName("钟卓秋");
            builder.setChannelUserId("180806");
            builder.setChannelId(1);
            builder.setSex(1);
            builder.setProvince("广东省");
            builder.setCity("广州");
            builder.setCountry("中国");
            builder.setAppId("1321312");
            
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Test
    public void unBindinChannelIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/unBinding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.newBuilder();
            builder.setChannelId(0);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void unBindingChannelUserIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/unBinding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.newBuilder();
            builder.setChannelUserId("");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unBindingOpenIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/unBinding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.newBuilder();
            builder.setOpenId("");
            builder.setChannelUserId("178429");
            builder.setChannelId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void unBindingAppIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/unBinding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.newBuilder();
            //builder.setOpenId("oBrt31W0DLax0PAqpKbMtyYQzLek");
            builder.setUnionId("nZmwj1tpZEGa22Qod03OhFxsQfvgunion");
            builder.setChannelUserId("18080999999");
            builder.setChannelId(1);
            builder.setAppId("1111");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void unBindingNot_existTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/unBinding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.newBuilder();
            builder.setOpenId("oZmwj1tpZEGa22Qod03OhFxsQfvg111232");
            builder.setChannelUserId("1784293423423");
            builder.setChannelId(1);
            builder.setAppId("1111343");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void unBinding(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/unBinding", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthUnBindRequest.newBuilder();
            //builder.setOpenId("oZmwj1tpZEGa22Qod03OhFxsQfvg22");
            builder.setUnionId("nZmwj1tpZEGa22Qod03OhFxsQfvgunion");
            builder.setAppId("1111");
            builder.setChannelUserId("34234234324");
            builder.setChannelId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getWeChatByOpenId(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/getWeChatByOpenId", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.getUserWeChatAuthByOpenIdRequest.Builder builder = UserWeChatAuthServiceProto.getUserWeChatAuthByOpenIdRequest.newBuilder();
            builder.setOpenId("oZmwj1tpZEGa22Qod03OhFxsQfvg22");
            //builder.setUnionId("1011");
           // builder.setChannelUserId("173076");
            builder.setChannelId(1);
            builder.setAppId("1111");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse resp ;
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
                if (resultSet.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resultSet.getData().is(UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class)) {
                    resp = resultSet.getData().unpack(UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class);
                    System.out.println("result:" + resp.getNickName());
                    System.out.println("result:" + resp.getHeadImgUrl());
                    System.out.println("result:" + resp.getOpenId());
                }

            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getWeChatByChannelUserId(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/getWeChatByChannelUserId", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.getUserWeChatAuthByChannelUserIdRequest.Builder builder = UserWeChatAuthServiceProto.getUserWeChatAuthByChannelUserIdRequest.newBuilder();
            builder.setChannelUserId("1111143");
            //builder.setUnionId("1011");
            // builder.setChannelUserId("173076");
            builder.setChannelId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {


                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                JsonFormat jsonFormat = new JsonFormat();

                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserWeChatAuthServiceProto.UserWeChatAuthInfoResponseList.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserWeChatAuthServiceProto.UserWeChatAuthInfoResponseList.class)));
                } else {
                    System.out.println("result:" + resp.getData());
                }

            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void loginByOneKey(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/weChat/loginByOneKey", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatOneKeyLoginRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatOneKeyLoginRequest.newBuilder();
            builder.setInviteChannelUserId("173076");
            builder.setMobile("18680443010");
            builder.setMobileAreaCode("86");
            builder.setChannelId(2);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse resp ;
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
                if (resultSet.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resultSet.getData().is(UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class)) {
                    resp = resultSet.getData().unpack(UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class);
                    System.out.println("result:" + resp.getNickName());
                    System.out.println("result:" + resp.getHeadImgUrl());
                    System.out.println("result:" + resp.getOpenId());
                    System.out.println("mobile="+resp.getMobile());
                    System.out.println("channelUserId="+resp.getChannelUserId());
                }

            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void getCheckPhoneTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/weChat/checkPhone", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthCheckPhoneRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthCheckPhoneRequest.newBuilder();
            builder.setOpenId("oZmwj1j-Osl3MXKvUtG4B-oHQ-SI");
            builder.setChannelUserId("34234234324");
            builder.setChannelId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserWeChatAuthServiceProto.UserWeChatAuthCheckPhoneResponse.class)) {
                    UserWeChatAuthServiceProto.UserWeChatAuthCheckPhoneResponse entity = resp.getData().unpack(UserWeChatAuthServiceProto.UserWeChatAuthCheckPhoneResponse.class);
                    System.out.println("result:" + entity.getMobile());
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void getCheckPhoneK8sTest() {
        try {
            URI uri = new URI("http", null, "172.18.0.112", 8080, "/weChat/checkPhone", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthCheckPhoneRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthCheckPhoneRequest.newBuilder();
            builder.setOpenId("oBrt31QHfJmv8qRr0J218FABVYac");
            builder.setChannelUserId("11550108");
            builder.setChannelId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserWeChatAuthServiceProto.UserWeChatAuthCheckPhoneResponse.class)) {
                    UserWeChatAuthServiceProto.UserWeChatAuthCheckPhoneResponse entity = resp.getData().unpack(UserWeChatAuthServiceProto.UserWeChatAuthCheckPhoneResponse.class);
                    System.out.println("result:" + entity.getMobile());
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

   @Test
    public void bindPhoneWriteLogTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/weChat/bindingPhone", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthBindingPhoneRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthBindingPhoneRequest.newBuilder();
            builder.setOpenId("oBrt31epHkTX4M9lpH12mIOxsdm4");
            builder.setChannelUserId("176879");
            builder.setChannelId(1);
            builder.setMobile("980897686754");
            builder.setMobileAreaCode("86");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @Test
    public void bindPhoneWriteLogMobileAreaCode_NUll_Test() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/weChat/bindingPhone", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthBindingPhoneRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthBindingPhoneRequest.newBuilder();
            builder.setOpenId("oBrt31epHkTX4M9lpH12mIOxsdm4");
            builder.setChannelUserId("176879");
            builder.setChannelId(1);
            builder.setMobile("867876867");
            //builder.setMobileAreaCode("86");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    @Test
    public void setWxNoChannelIdTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/weChat/wxno/set", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatWxNoRequest.Builder builder=UserWeChatAuthServiceProto.UserWeChatWxNoRequest.newBuilder();
            builder.setOpenId("oBrt31epHkTX4M9lpH12mIOxsdm4");
            builder.setUnionId("oBrt31epHkTX4M9lpH12mIOxsdmUnqq");
            builder.setWxNo("9358324293");
            builder.setChannelUserId("176879");
            builder.setChannelId(0);
            //builder.setMobileAreaCode("86");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Test
    public void setWxNoOpenIdTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/weChat/wxno/set", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatWxNoRequest.Builder builder=UserWeChatAuthServiceProto.UserWeChatWxNoRequest.newBuilder();
            builder.setOpenId("");
            builder.setUnionId("oBrt31epHkTX4M9lpH12mIOxsdmUnqq");
            builder.setWxNo("9358324293");
            builder.setChannelUserId("176879");
            builder.setChannelId(1);
            //builder.setMobileAreaCode("86");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Test
    public void setWxNoUnionIdTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/weChat/wxno/set", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatWxNoRequest.Builder builder=UserWeChatAuthServiceProto.UserWeChatWxNoRequest.newBuilder();
            builder.setOpenId("oBrt31epHkTX4M9lpH12mIOxsdm4");
            builder.setUnionId("");
            builder.setWxNo("9358324293");
            builder.setChannelUserId("176879");
            builder.setChannelId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    @Test
    public void setWxNoTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/weChat/wxno/set", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatWxNoRequest.Builder builder=UserWeChatAuthServiceProto.UserWeChatWxNoRequest.newBuilder();
            builder.setOpenId("oBrt31epHkTX4M9lpH12mIOxsdm4");
            builder.setUnionId("oBrt31epHkTX4M9lpH12mIOxsdmUnqq");
            builder.setWxNo("9358324293");
            builder.setChannelUserId("176879");
            builder.setChannelId(1);
            //builder.setMobileAreaCode("86");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
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
