package com.hs.user.base.test;

import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserBaseServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;

public class UserBaseInfoBindingNewMobileTest {

    public static void main(String [] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/binding/new/mobile", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBindingNewMobileRequest.Builder builder = UserBaseServiceProto.UserBindingNewMobileRequest.newBuilder();
            //builder.setChannelUserId("01569061374228");
            builder.setChannelUserId("521577181861916");
            //builder.setChannelId(54305345);
            builder.setChannelId(1);
            //builder.setMobileAreaCode("88");
            builder.setMobile("18888888666");
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
        } finally {
            httpClient.close();
        }
    }


}
