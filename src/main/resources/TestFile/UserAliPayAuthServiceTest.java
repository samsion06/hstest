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

public class UserAliPayAuthServiceTest {
    private CloseableHttpClient httpClient;
    private static final String SCHEME = "http";
    private static final String HOST = "127.0.0.1";
    private static final int POST = 8080;
 /*  private static final String HOST = "172.18.66.88";
    private static final int POST = 8090;*/

   /* private static final String HOST = "172.18.0.112";
    private static final int POST = 8080;*/

    @Test
    public void aliPayBindingtChannelIdTest(){
        try {
            URI uri = new URI("http", null, HOST, 8080, "/aliPay/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayBidingRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayBidingRequest.newBuilder();
            builder.setChannelId(0);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void aliPayBindingtChannelUserIdTest(){
        try {
            URI uri = new URI("http", null, HOST, 8080, "/aliPay/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayBidingRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayBidingRequest.newBuilder();
            builder.setChannelUserId(null);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void aliPayBindingAlipayRealname_null_AlipayAccountlTest(){
        try {
            URI uri = new URI("http", null, HOST, 8080, "/aliPay/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayBidingRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayBidingRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("2060849123");
            builder.setAlipayRealname("");
            builder.setAlipayAccount("13680443014");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void aliPayBindingAlipayRealname_AlipayAccountl_nullTest(){
        try {
            URI uri = new URI("http", null, HOST, 8080, "/aliPay/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayBidingRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayBidingRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("2060849123");
            builder.setAlipayRealname("小名");
            builder.setAlipayAccount("");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }




    @Test
    public void aliPayBindingAlipayRealname_AlipayAccountTest(){
        try {
            URI uri = new URI("http", null, HOST, 8080, "/aliPay/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayBidingRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayBidingRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("2060849125");
            builder.setAlipayRealname("老子");
            builder.setAlipayAccount("13680443014");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @Test
    public void aliPayBindingAlipayWriteLogTest(){
        try {
            URI uri = new URI("http", null, HOST, 8080, "/aliPay/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayBidingRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayBidingRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("2060849125");
            builder.setAlipayRealname("dddd子");
            builder.setAlipayAccount("3dd684okii");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void aliPayBindingAlipayRealname_AlipayAccount_updateTest(){
        try {
            URI uri = new URI("http", null, HOST, 8080, "/aliPay/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayBidingRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayBidingRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("2060849123");
            builder.setAlipayRealname("磊小小");
            builder.setAlipayAccount("136804430147");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void aliPayBindingTest(){
        try {
            URI uri = new URI("http", null, HOST, 8080, "/aliPay/binding", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayBidingRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayBidingRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("20608496");
            builder.setAlipayRealname("小小2");
            builder.setAlipayAccount("18680443010");
            builder.setIdentityCard("8888888888888102");
            builder.setNickName("1232");
            builder.setHeadImg("/sdf/sdf/sdf/sdf2");
            builder.setSex(1);
            builder.setAlipayUserId("324234234232");
            builder.setProvince("广东省");
            builder.setCity("广州市");
            builder.setIsCertified(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void authCancelChannelIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth/cancel", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthCancelRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthCancelRequest.newBuilder();
            builder.setChannelId(0);

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
            e.printStackTrace();
        }
    }

    @Test
    public void authCancelChannelUserIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth/cancel", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthCancelRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthCancelRequest.newBuilder();
            builder.setChannelUserId("");

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
            e.printStackTrace();
        }
    }

    @Test
    public void authCancelChannelUserId_not_existTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth/cancel", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthCancelRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthCancelRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("werwerwer");

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
            e.printStackTrace();
        }
    }

    @Test
    public void authCancelTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth/cancel", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthCancelRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthCancelRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("2060849123");

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
            e.printStackTrace();
        }
    }

    @Test
    public void authChannelIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthRequest.newBuilder();
            builder.setChannelId(0);
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
            e.printStackTrace();
        }
    }


    @Test
    public void authChannelUserIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthRequest.newBuilder();
            builder.setChannelUserId("");
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
            e.printStackTrace();
        }
    }

    @Test
    public void auth_not_existTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("xxxxxxxxx");
            builder.setAlipayUserId("32132132");
            builder.setCity("广州市");
            builder.setHeadImg("/sdfs/sdf/ssdfds/df");
            builder.setIdentityCard("1295830423843582394");
            builder.setIsCertified(1);
            builder.setNickName("夺地崮");
            builder.setProvince("广东自");
            builder.setSex(1);
            builder.setIsStudentCertified("1");
            builder.setUserStatus("1");
            builder.setUserType("1");
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
            e.printStackTrace();
        }
    }



    @Test
    public void authTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("2060849123");
            builder.setAlipayUserId("32132132");
            builder.setCity("广州市");
            builder.setHeadImg("/sdfs/sdf/ssdfds/df");
            builder.setIdentityCard("1295830423843582394");
            builder.setIsCertified(1);
            builder.setNickName("夺地崮");
            builder.setProvince("广东自");
            builder.setSex(1);
            builder.setIsStudentCertified("1");
            builder.setUserStatus("1");
            builder.setUserType("1");
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
            e.printStackTrace();
        }
    }



    @Test
    public void authWriteLogTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("2060849123");
            builder.setAlipayUserId("32132132");
            //builder.setCity("广州市");
            //builder.setHeadImg("/sdfs/sdf/ssdfds/df");
            // builder.setIdentityCard("1295830423843582394");
            builder.setIsCertified(1);
            //builder.setNickName("夺地崮");
            builder.setProvince("广东自");
            builder.setSex(1);
            //builder.setIsStudentCertified("1");
            //builder.setUserStatus("1");
            //builder.setUserType("1");
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
            e.printStackTrace();
        }
    }


    @Test
    public void aliPayAuthInfoChannelIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth/info", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthInfoRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthInfoRequest.newBuilder();
            builder.setChannelId(0);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserAliPayAuthServiceProto.UserAliPayAuthInfoResponse resp = UserAliPayAuthServiceProto.UserAliPayAuthInfoResponse.parseFrom(response.getEntity().getContent());
                System.out.println("msg:" +  jsonFormat.printToString(resp.getUserAliPayAuth()));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void aliPayAuthInfoChannelUserIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth/info", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthInfoRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthInfoRequest.newBuilder();
            builder.setChannelUserId("");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserAliPayAuthServiceProto.UserAliPayAuthInfoResponse resp = UserAliPayAuthServiceProto.UserAliPayAuthInfoResponse.parseFrom(response.getEntity().getContent());
                System.out.println("msg:" +  jsonFormat.printToString(resp.getUserAliPayAuth()));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void aliPayAuthInfo_not_existTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth/info", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthInfoRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthInfoRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("xxxxxxxxx");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserAliPayAuthServiceProto.UserAliPayAuthInfoResponse resp = UserAliPayAuthServiceProto.UserAliPayAuthInfoResponse.parseFrom(response.getEntity().getContent());
                if (resp.getUserAliPayAuth() == UserAliPayAuthServiceProto.UserAliPayAuth.getDefaultInstance()
                 ||UserAliPayAuthServiceProto.UserAliPayAuth.getDefaultInstance().equals(resp.getUserAliPayAuth())
                ) {
                    System.out.println("msg:" + jsonFormat.printToString(resp));
                } else {
                    System.out.println("msg:" + jsonFormat.printToString(resp));
                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Test
    public void aliPayAuthInfoTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/aliPay/auth/info", "", null);
            HttpPost post = new HttpPost(uri);
            UserAliPayAuthServiceProto.UserAliPayAuthInfoRequest.Builder builder = UserAliPayAuthServiceProto.UserAliPayAuthInfoRequest.newBuilder();
            builder.setChannelId(1);
            //builder.setChannelUserId("173056");
            builder.setChannelUserId("2060849123");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserAliPayAuthServiceProto.UserAliPayAuthInfoResponse resp = UserAliPayAuthServiceProto.UserAliPayAuthInfoResponse.parseFrom(response.getEntity().getContent());
                System.out.println("msg:" +  jsonFormat.printToString(resp.getUserAliPayAuth()));
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
