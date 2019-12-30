package com.hs.user.base;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.UserBaseServiceProto;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;

public class UserBaseSysTest {
    private CloseableHttpClient httpClient;
    private static final String SCHEME = "http";
    private static final String HOST = "127.0.0.1";
    private static final int POST = 8080;
    @Test
    public void getInstancesTest(){

    }

    @Test
    public void checkTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/check", "", null);
            HttpGet post = new HttpGet(uri);
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                int size =response.getEntity().getContent().available();
                byte[] data =new byte[size];
                int read = response.getEntity().getContent().read(data,0, size);
                if (read>0) {
                    System.out.println((new String(data)));
                }

            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/test", "", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.userInfoPdCombine resp =  UserBaseServiceProto.userInfoPdCombine.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
                /**
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS
                        && resp.getData().is(UserBaseServiceProto.UserBaseInfo.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserBaseServiceProto.UserBaseInfo.class)));
                } else {
                    System.out.println("result:" + resp.getData());
                }
                 **/
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
