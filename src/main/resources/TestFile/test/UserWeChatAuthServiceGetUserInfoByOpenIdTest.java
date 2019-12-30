package com.hs.user.base.test;

import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserWeChatAuthServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;

public class UserWeChatAuthServiceGetUserInfoByOpenIdTest {

    public static void main(String [] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/weChat/getWeChatUserByOpenIdOrUnionId", "", null);
            HttpPost post = new HttpPost(uri);
            UserWeChatAuthServiceProto.UserWeChatAuthLoginRequest.Builder builder = UserWeChatAuthServiceProto.UserWeChatAuthLoginRequest.newBuilder();
            //builder.setOpenId("oBrt31epHkTX4M9lpH12mIOxsdm4");
            //builder.setChannelUserId("176879");
            builder.setChannelId(1);
            builder.setOpenId("oZmwj1tpZEGa22Qod03OhFxsQfvg22");
            builder.setUnionId("nZmwj1tpZEGa22Qod03OhFxsQfvgunion");
            builder.setAppId("1111");
            builder.setChannelUserId("34234234324");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class) ) {
                    UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse entity = resp.getData().unpack(UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class);
                    System.out.println("result:" + entity.getChannelUserId());
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            httpClient.close();
        }
    }
}
