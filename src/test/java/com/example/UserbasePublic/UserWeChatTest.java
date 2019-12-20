package com.example.UserbasePublic;

import com.example.Obj.UserBaseInfo;
import com.example.mapper.UserBaseInfoMapper;
import com.example.utils.CheckReponseResult;
import com.example.utils.ConvertData;
import com.example.utils.DataUtils;
import com.example.utils.HttpConfig;
import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserBaseServiceProto;
import com.hs.user.base.proto.UserWeChatAuthServiceProto;
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
import java.util.List;

@SpringBootTest
public class UserWeChatTest  extends AbstractTestNGSpringContextTests{

    @Autowired
    private UserBaseInfoMapper userBaseInfoMapper;//数据库取数据用

    private static String AppId="Appid01";
    private static Integer ChannelId=1;
    private static CloseableHttpClient httpClient;
    private static ByteArrayEntity byteArrayEntity;
    private static URI uri;
    private static HttpPost post;
    private static HttpResponse response;

    @org.testng.annotations.Test(description = "1.微信绑定" +
            "                              2.微信解绑 ")
    public void bindingAndunBinding(){
        //随机生成openId 和 channeluserid
        String openId= DataUtils.getRandomString(9);
        String ChannelUserId=String.valueOf((int)((Math.random()*9+1)*1000));
        System.out.println("传入随机生成得："+"openId:"+openId+"\b ChannelUserId:"+ChannelUserId);
        System.out.println("开始执行：bindingAndunBinding（）方法");
        try {
            httpClient = HttpClients.createDefault();
            //微信绑定
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/weChat/binding","");
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserWeChatAuthRequest(AppId,ChannelId,ChannelUserId,openId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            CheckReponseResult.AssertResponse(response);
            //解除绑定
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/weChat/unBinding","");
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserWeChatAuthUnBindRequest(openId,ChannelId,ChannelUserId,AppId);
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            //普通状态检验
            String msg = CheckReponseResult.AssertResponse(response);
            //数据库再次验证
            if(msg.equals("RESP_CODE_SUCCESS")){
                CheckReponseResult.CheckDatabaseInfo(userBaseInfoMapper,"queryWeChatInfo","1",ChannelUserId);
            }else{
                System.out.println(msg);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("bindingAndunBinding（）方法结束");
    }

    @Test  //用户微信一键登录
    public void loginByOneKey(){
        System.out.println("开始执行：loginByOneKey（）方法");
        try{
            //微信绑定
            httpClient = HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/weChat/loginByOneKey","");
            post = new HttpPost(uri);
            byteArrayEntity = ConvertData.UserWeChatOneKeyLoginRequest(ChannelId,"17702015334","177417","86");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            HttpResponse response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response,UserWeChatAuthServiceProto.UserWeChatAuthInfoResponse.class);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("loginByOneKey（）方法结束");
    }

    @Test //根据微信ID和OPENID获取用户信息
    public void getByOpenId() {
         //System.out.println(userBaseInfoMapper.queryUserBaseInfo());
        System.out.println("开始执行：getByOpenId（）方法");
        try {
            httpClient=HttpClients.createDefault();
            uri = new URI(HttpConfig.scheme, HttpConfig.url, "/base/user/info/pd/get/by/unionId/openId","");
           //uri = new URI(HttpConfig.scheme, null, "172.18.0.112", 8080, "/base/user/info/pd/get/by/unionId/openId", "", null);
            System.out.println(uri);
            post = new HttpPost(uri);;
            byteArrayEntity = ConvertData.UserInfoUnionIdOpenIdRequestConvertBuilder(ChannelId, "ox-FY1f0_ub3FnM_v9n7ITb1q-f0", "oBrt31Sg6EqD9DJxB0Mz9EOl-Pp4");
            post.setEntity(byteArrayEntity);
            post.setHeader("Content-Type", "application/x-protobuf");
            response = httpClient.execute(post);
            CheckReponseResult.AssertResponses(response, UserBaseServiceProto.userInfoPdCombine.class);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        System.out.println("getByOpenId（）方法结束");
    }
}
