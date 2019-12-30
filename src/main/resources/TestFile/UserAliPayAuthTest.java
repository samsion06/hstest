package com.hs.user.base;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserAliPayAuthServiceProto;
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

public class UserAliPayAuthTest {
   private CloseableHttpClient httpClient;
    private static  String SCHEME = "http";
    private static  String HOST = "127.0.0.1";
    private static  int POST = 8080;

    @Test
    public void bindTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayBidingRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayBidingRequest.newBuilder();
            builder.setChannelId(1);
            //builder.setInviteCode("4ahstod");
            builder.setChannelUserId("3692091");
            builder.setAlipayAccount("css_0612@qq.com");
            builder.setAlipayRealname("css");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserAliPayAuthServiceProto.UserAliPayAuthRequest.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserAliPayAuthServiceProto.UserAliPayAuthRequest.class)));
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


    @Before
    public void before(){
        httpClient = HttpClients.createDefault();
    }

    @After
    public void after() throws IOException {
        httpClient.close();
    }

}
