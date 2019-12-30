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

public class UserBaseInfoGetByMobileTest {

    public static void main(String [] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/getUserInfoByMobile", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInfoByMobileRequest.Builder builder = UserBaseServiceProto.UserInfoByMobileRequest.newBuilder();
            builder.setChannelId(222222);
            builder.setMobileAreaCode("88");
            builder.setMobile("18888888666");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                if ( resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserBaseServiceProto.UserBaseInfo.class) ) {
                    UserBaseServiceProto.UserBaseInfo userBaseInfo =  resp.getData().unpack(UserBaseServiceProto.UserBaseInfo.class);
                    System.out.println("result:" + userBaseInfo.getNickName());
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
