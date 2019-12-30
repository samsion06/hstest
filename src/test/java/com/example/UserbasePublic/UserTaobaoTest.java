package com.example.UserbasePublic;

import com.example.utils.CheckReponseResult;
import com.example.utils.DataTransfer;
import com.example.utils.HttpConfig;
import com.hs.user.base.proto.HsrjUserTaobaoAuthInfoServiceProto;
import com.hs.user.base.proto.UserBaseServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.IOException;
import java.net.URI;

@SpringBootTest
public class UserTaobaoTest extends AbstractTestNGSpringContextTests {


    private static Integer channelId=1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @Test(description = "1.淘宝授权" +
            "            2.授权取消 ")
    public void authAndCancel(){
        try {
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/taobao/auth", "");
            post = new HttpPost(uri);
            //byteArrayEntity = DataTransfer.HsrjUserTaobaoAuthRequest("p88vcdo", 1);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponse(response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }














}
