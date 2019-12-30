package com.hs.user.base;

import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserIdCardIdentifyServiceProto;
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

public class IdCardIdentifyTest {

    private CloseableHttpClient httpClient;
    private static final String SCHEME = "http";
    private static final String HOST = "127.0.0.1";
    private static final int POST = 8080;

    @Test
    public void queryStatus(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/idCard/queryStatus", "", null);
            HttpPost post = new HttpPost(uri);
            UserIdCardIdentifyServiceProto.UserIdCardStatusQueryRequest.Builder builder = UserIdCardIdentifyServiceProto.UserIdCardStatusQueryRequest.newBuilder();
            builder.setChannelUserId("56903884577");
            builder.setChannelId(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resultSet =ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                UserIdCardIdentifyServiceProto.UserIdCardIdentifyInfo resp ;
                System.out.println("result:" + resultSet.getCodeValue());
                System.out.println("msg:" + resultSet.getMsg());
                if (resultSet.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resultSet.getData().is(UserIdCardIdentifyServiceProto.UserIdCardIdentifyInfo.class)) {
                    resp = resultSet.getData().unpack(UserIdCardIdentifyServiceProto.UserIdCardIdentifyInfo.class);
                    System.out.println("result:" + resp.getRealName());
                    System.out.println("result:" + resp.getIdCardNum());
                }

            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void identify(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/user/idCard/identify", "", null);
            HttpPost post = new HttpPost(uri);
            UserIdCardIdentifyServiceProto.UserIdCardIdentifyRequest.Builder builder = UserIdCardIdentifyServiceProto.UserIdCardIdentifyRequest.newBuilder();
            builder.setRealName("向亚运");
            builder.setIdCardNum("431224199009227572");
            builder.setChannelUserId("56903884577");
            builder.setChannelId(1);
            builder.setAttachmentUrl("http://www.baidu.com");

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


    @Before
    public void before(){
        httpClient = HttpClients.createDefault();
    }

    @After
    public void after() throws IOException {
        httpClient.close();
    }
}
