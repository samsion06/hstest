package com.example.UserbasePublic;

import com.example.utils.HttpConfigUtil;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserBaseServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.net.URI;

public class Test
{
    public static void main(String[] args) {
            try {
                CloseableHttpClient httpClient= HttpClients.createDefault();
                URI uri;
                uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/base/user/getUserInfoByMobile", "");
                HttpPost post = new HttpPost(uri);
                UserBaseServiceProto.UserInfoByMobileRequest.Builder builder = UserBaseServiceProto.UserInfoByMobileRequest.newBuilder();
                builder.setChannelId(1);
                builder.setMobileAreaCode("86");
                builder.setMobile("17702015334");
                post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
                post.setHeader("Content-Type", "application/x-protobuf");

                HttpResponse response = httpClient.execute(post);
                JsonFormat jsonFormat =new JsonFormat();
                if (response.getStatusLine().getStatusCode() == 200) {
                    ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                    System.out.println("result:" + resp.getCodeValue());
                    System.out.println("msg:" + resp.getMsg());
                    System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                    if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserBaseServiceProto.UserBaseInfo.class)) {
                        System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserBaseServiceProto.UserBaseInfo.class)));
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
}
