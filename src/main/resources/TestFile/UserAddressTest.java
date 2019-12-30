package com.hs.user.base;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserAddressServiceProto;
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

public class UserAddressTest {
    private CloseableHttpClient httpClient;
    private static final String SCHEME = "http";
    private static final String HOST = "127.0.0.1";
    private static final int POST = 8080;
 /*  private static final String HOST = "172.18.66.88";
    private static final int POST = 8090;*/

    @Test
    public void addUserAddressTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/address/add", "", null);
            HttpPost post = new HttpPost(uri);
            UserAddressServiceProto.UserAddressInfoAddRequest.Builder builder = UserAddressServiceProto.UserAddressInfoAddRequest.newBuilder();
            builder.setAddress("琶洲，电商产业园");
            builder.setCity("239877080689422904");
            builder.setDistrict("239877080689422917");
            builder.setMobile("13456783219");
            builder.setProvince("239877080689422903");
            builder.setUserName("白银");
            builder.setChannelUserId("chann0123");
            builder.setAddressTag(1);
            builder.setIsDefault(1);
            builder.setChannelId(1);

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserAddressServiceProto.UserAddressInfoResponse.class)));
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
    public void deleteAddressIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/address/delete", "", null);
            HttpPost post = new HttpPost(uri);
            UserAddressServiceProto.UserAddressRequest.Builder builder = UserAddressServiceProto.UserAddressRequest.newBuilder();
            builder.setChannelId(2);
            builder.setChannelUserId("chann0123");
            builder.setAddressId("1ee459cd4c9e41fa84e2720e85ae7f3a");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserAddressServiceProto.UserAddressInfoResponse.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserAddressServiceProto.UserAddressInfoResponse.class)));
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
    public void getByAddressIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/address/getByAddressId", "", null);
            HttpPost post = new HttpPost(uri);
            UserAddressServiceProto.UserAddressRequest.Builder builder = UserAddressServiceProto.UserAddressRequest.newBuilder();
            builder.setAddressId("1ee459cd4c9e41fa84e2720e85ae7f3a");
            builder.setChannelId(2);
            builder.setChannelUserId("chann0123");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserAddressServiceProto.UserAddressInfoResponse.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserAddressServiceProto.UserAddressInfoResponse.class)));
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
    public void updateUserAddressTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/address/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserAddressServiceProto.UserAddressInfoUpdateRequest.Builder builder = UserAddressServiceProto.UserAddressInfoUpdateRequest.newBuilder();
            builder.setAddressId("1ee459cd4c9e41fa84e2720e85ae7f3a");
            builder.setChannelId(2);
            builder.setChannelUserId("chann0123");
            builder.setProvince("广东省");
            builder.setCity("中山市");
            builder.setDistrict("小览镇");
            builder.setAddress("小夫夺协老大哥地");
            builder.setAddressTag(4);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("result:" + resp.getCode().name());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    @Test
    public void updateUserAddressTagTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/address/tag/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserAddressServiceProto.UserAddressTagRequest.Builder builder = UserAddressServiceProto.UserAddressTagRequest.newBuilder();
            builder.setAddressId("1ee459cd4c9e41fa84e2720e85ae7f3a");
            builder.setChannelUserId("chann0123");
            builder.setChannelId(2);
            builder.setAddressTag(2);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("result:" + resp.getCode().name());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void queryUserAddressByPageTest(){
        try {
            //URI uri = new URI(SCHEME, null, HOST, POST, "/address/query", "", null);
            URI uri = new URI(SCHEME, null, "172.18.0.112", 8080, "/address/query", "", null);
            HttpPost post = new HttpPost(uri);
            UserAddressServiceProto.UserAddressPageRequest.Builder builder = UserAddressServiceProto.UserAddressPageRequest.newBuilder();
            builder.setChannelId(2);
            builder.setChannelUserId("8e2d3aa325dc46a9986b12581f4fa7d4");
            builder.setPageNum(1);
            builder.setPageSize(3);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserAddressServiceProto.UserAddressPage.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserAddressServiceProto.UserAddressPage.class)));
                } else {
                    System.out.println("result:" + resp.getData());
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void getSysAreaTreeTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/address/sys/area/tree", "", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserAddressServiceProto.SysAreaNodeTreeResponse.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserAddressServiceProto.SysAreaNodeTreeResponse.class)));
                } else {
                    System.out.println("result:" + resp.getData());
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void getSysSubAreaTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/address/sys/sub/area", "", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");
            UserAddressServiceProto.UserSysSubAreaRequest.Builder builder = UserAddressServiceProto.UserSysSubAreaRequest.newBuilder();
            builder.setParentId("0");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserAddressServiceProto.SysAreaNodeTreeResponse.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserAddressServiceProto.SysAreaNodeTreeResponse.class)));
                } else {
                    System.out.println("result:" + resp.getData());
                }
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
