package com.hs.user.base;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.HsrjUserTaobaoAuthInfoServiceProto;
import com.hs.user.base.proto.ResultResponse;
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

public class UserHsrjTaoBaoAuthServiceTest {
    private CloseableHttpClient httpClient;
    private static final String SCHEME = "http";
    private static final String HOST = "127.0.0.1";
    private static final int POST = 8080;
 /*  private static final String HOST = "172.18.66.88";
    private static final int POST = 8090;*/

   /* private static final String HOST = "172.18.0.112";
    private static final int POST = 8080;*/

    @Test
    public void taobaoAuthChannelUserIdTest(){
        try {
            URI uri = new URI("http", null, HOST, POST, "/taobao/auth", "", null);
            HttpPost post = new HttpPost(uri);
            HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.Builder builder = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.newBuilder();
            builder.setChannelUserId("");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void taobaoAuthNewTest(){
        try {
            //URI uri = new URI("http", null, HOST, POST, "/taobao/auth", "", null);
            URI uri = new URI("http", null, "172.18.0.112", 8080, "/taobao/auth", "", null);
            HttpPost post = new HttpPost(uri);
            HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.Builder builder = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.newBuilder();
            builder.setChannelUserId("2060849422222");
            builder.setCompanyId(1);
            builder.setRelationId(2);
            builder.setTbAccountId(3);
            builder.setTbAccount("4");
            builder.setSpecialId(5);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void taobaoAuthTest(){
        try {
            URI uri = new URI("http", null, HOST, POST, "/taobao/auth", "", null);
            HttpPost post = new HttpPost(uri);
            HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.Builder builder = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.newBuilder();
            builder.setChannelUserId("20608494");
            builder.setCompanyId(23);
            builder.setRelationId(213);
            builder.setTbAccountId(12312);
            builder.setTbAccount("12321312");
            builder.setSpecialId(32312);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void taobaoAuthExist_and_updateTest(){
        try {
            URI uri = new URI("http", null, HOST, POST, "/taobao/auth", "", null);
            HttpPost post = new HttpPost(uri);
            HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.Builder builder = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.newBuilder();
            builder.setChannelUserId("20608494");
            builder.setCompanyId(1);
            builder.setRelationId(2);
            builder.setTbAccountId(3);
            builder.setTbAccount("4");
            builder.setSpecialId(5);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }




    @Test
    public void taobaoAuthCancelChannelUserIdTest(){
        try {
            URI uri = new URI("http", null, "172.18.0.112", 8080, "/taobao/auth/cancel", "", null);
            //URI uri = new URI("http", null, HOST, POST, "/taobao/auth/cancel", "", null);
            HttpPost post = new HttpPost(uri);
            HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.Builder builder = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.newBuilder();
            builder.setChannelUserId("2060849422222");
            builder.setCompanyId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void taobaoAuthCancelTest(){
        try {
            URI uri = new URI("http", null, HOST, POST, "/taobao/auth/cancel", "", null);
            HttpPost post = new HttpPost(uri);
            HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.Builder builder = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.newBuilder();
            builder.setChannelUserId("20608494");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void taobaoAuthCancelNotAuthTest(){
        try {
            URI uri = new URI("http", null, HOST, POST, "/taobao/auth/cancel", "", null);
            HttpPost post = new HttpPost(uri);
            HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.Builder builder = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthRequest.newBuilder();
            builder.setChannelUserId("20608494qweqweqweqweqw");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void taobaoAuthInfoChannelUserIdTest(){
        try {
            URI uri = new URI("http", null, HOST, POST, "/taobao/auth/info", "", null);
            HttpPost post = new HttpPost(uri);
            HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthQueryRequest.Builder builder = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthQueryRequest.newBuilder();
            builder.setChannelUserId("");
            builder.setCompanyId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthInfoResponse resp = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthInfoResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void taobaoAuthInfoTest(){
        try {
            URI uri = new URI("http", null, HOST, POST, "/taobao/auth/info", "", null);
            HttpPost post = new HttpPost(uri);
            HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthQueryRequest.Builder builder = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthQueryRequest.newBuilder();
            builder.setChannelUserId("20608494");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthInfoResponse resp = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthInfoResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void taobaoAuthInfoNotExistTest(){
        try {
            URI uri = new URI("http", null, HOST, POST, "/taobao/auth/info", "", null);
            HttpPost post = new HttpPost(uri);
            HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthQueryRequest.Builder builder = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthQueryRequest.newBuilder();
            builder.setChannelUserId("xcxxxxxxxxxxx");
            //builder.setChannelUserId("20608494");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthInfoResponse resp = HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthInfoResponse.parseFrom(response.getEntity().getContent());
                if (resp.getResponseCode().getCode() == HsrjUserTaobaoAuthInfoServiceProto.ResponseCodeEnum.RESP_CODE_SUCCESS.getNumber()) {

                    if (resp.getHsrjUserTaobaoAuthInfo() == HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthInfo.getDefaultInstance()) {//默认值判断（空值）
                        System.out.println("defaultInstance:" + jsonFormat.printToString(resp));
                    }
                    if ( HsrjUserTaobaoAuthInfoServiceProto.HsrjUserTaobaoAuthInfo.getDefaultInstance().equals(resp.getHsrjUserTaobaoAuthInfo()) ) {//手动设置空对象判断
                        System.out.println("set empty:" + jsonFormat.printToString(resp));
                    }else{
                        System.out.println("result:" + jsonFormat.printToString(resp.getHsrjUserTaobaoAuthInfo()));
                    }
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
