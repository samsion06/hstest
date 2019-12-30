package com.example.UserbasePublic;

import com.example.mapper.UserBaseInfoMapper;
import com.example.utils.CheckDatabase;
import com.example.utils.CheckReponseResult;
import com.example.utils.DataTransferUtil;
import com.example.utils.HttpConfigUtil;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.io.IOException;
import java.net.URI;

@SpringBootTest
public class UserTaobaoTest extends AbstractTestNGSpringContextTests {

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;
    private static Integer channelId=1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @Test(description = "1.淘宝授权 6个参数齐" +
            "            2.授权查询 4个参数齐" +
            "            3.取消授权 1个参数齐")
    public void authAndCancel(){
        try {
            String channelUserId="184003";
            String tbAccount="327420130";
            Long companyId=1642L;
            Long tbAccountId=327420130L;

            //淘宝授权
            httpClient = HttpClients.createDefault(); //184003
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/taobao/auth", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.HsrjUserTaobaoAuthRequest(channelUserId, 528467632L,528467634L,companyId,tbAccountId,tbAccount);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String authResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",authResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"TaoBaoAuth","1",channelUserId);

            //授权查询
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/taobao/auth/info", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.HsrjUserTaobaoAuthQueryRequest(channelUserId,tbAccount,companyId,tbAccountId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponse(response);

            //取消收授权
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/taobao/auth/cancel", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.HsrjUserTaobaoAuthCancelRequest(channelUserId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String cancelResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",cancelResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"TaoBaoCancel","1",channelUserId);

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

   // @Test(description = "取消授权")
    public void test1(){
        String channelUserId="184003";
        String tbAccount="327420130";
        Long companyId=1642L;
        Long tbAccountId=327420130L;

        try{
            httpClient = HttpClients.createDefault();
            //取消收授权
            uri = new URI(HttpConfigUtil.scheme, HttpConfigUtil.url, "/taobao/auth/cancel", "");
            post = new HttpPost(uri);
            byteArrayEntity = DataTransferUtil.HsrjUserTaobaoAuthCancelRequest(channelUserId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            String cancelResponseMsg = CheckReponseResult.AssertResponse(response);
            Assert.assertEquals("RESP_CODE_SUCCESS",cancelResponseMsg);
            CheckDatabase.CheckDatabaseInfo(userBaseInfoMapper,"TaoBaoCancel","1",channelUserId);

        }catch (Exception e){
            e.printStackTrace();
        }
    }












}
