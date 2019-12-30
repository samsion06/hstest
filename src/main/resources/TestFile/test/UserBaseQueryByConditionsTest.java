package com.hs.user.base.test;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserBaseServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;
import java.net.URI;

public class UserBaseQueryByConditionsTest {

    public static void main(String [] args) throws IOException {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        try {
            URI uri = new URI("http", null, "172.18.0.112", 8080, "/base/user/getUsersByCondition", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBaseInfoByConditionRequest.Builder builder = UserBaseServiceProto.UserBaseInfoByConditionRequest.newBuilder();
            builder.setChannelUserId("3692080");
            builder.setChannelId(1);
            builder.setPushNo("P88vcdo");
            //builder.setMobileAreaCode("88");
            //builder.setMobile("18888888666");
            builder.setPageNum(1);
            builder.setPageSize(10);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserBaseServiceProto.UserBaseInfoByConditionPage.class)));
                } else {
                    System.out.println("result:" + resp.getCode().name());
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
