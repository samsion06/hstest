package com.hs.user.base;

import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserBaseServiceProto;
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

public class UserInfoTest {
    private CloseableHttpClient httpClient;
    private static  String SCHEME = "http";
    private static  String HOST = "127.0.0.1";
    private static  int POST = 8080;
//    private static final String HOST = "172.18.0.112";
//    private static final int POST = 8080;

    @Test
    public void getUserInfoTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/getUserBaseInfo", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInfoRequest.Builder builder = UserBaseServiceProto.UserInfoRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("178803");
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


    @Test
    public void getUserInfoPdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/info/pd/get/by/channelUserId", "", null);
            //URI uri = new URI(SCHEME, null, "172.18.0.112", 8080, "/base/user/info/pd/get/by/channelUserId", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInfoRequest.Builder builder = UserBaseServiceProto.UserInfoRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("241577267213063");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserBaseServiceProto.userInfoPdCombine.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserBaseServiceProto.userInfoPdCombine.class)));
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



    @Test
    public void getUserInfoByMobileTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/getUserInfoByMobile", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInfoByMobileRequest.Builder builder = UserBaseServiceProto.UserInfoByMobileRequest.newBuilder();
            builder.setChannelId(1);
            builder.setMobileAreaCode("86");
            builder.setMobile("13486667245");
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


    @Test
    public void getUserInfoPdByMobileTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/info/pd/get/by/mobile", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInfoByMobileRequest.Builder builder = UserBaseServiceProto.UserInfoByMobileRequest.newBuilder();
            builder.setChannelId(1);
            builder.setMobileAreaCode("68");
            builder.setMobile("798989866");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserBaseServiceProto.userInfoPdCombine.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserBaseServiceProto.userInfoPdCombine.class)));
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



    @Test
    public void getUserInfoPdLoginTest(){
        try {
            URI uri = new URI(SCHEME, null, "172.18.0.112", 8080, "/base/user/info/pd/login", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInfoPdLoginRequest.Builder builder = UserBaseServiceProto.UserInfoPdLoginRequest.newBuilder();
            builder.setChannelId(1);
            //builder.setMobileAreaCode("400");
            builder.setMobile("17702015335");
            builder.setPwd("123456");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserBaseServiceProto.userInfoPdCombine.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserBaseServiceProto.userInfoPdCombine.class)));
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


    @Test
    public void getUserInfoPdByInviteCodeTest(){
        try {

            //URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/info/pd/get/by/invite/code", "", null);
            URI uri = new URI(SCHEME, null, "172.18.0.112", 8080, "/base/user/info/pd/get/by/invite/code", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInviteCodeQueryRequest.Builder builder = UserBaseServiceProto.UserInviteCodeQueryRequest.newBuilder();
            builder.setChannelId(1);
            //builder.setInviteCode("4ahstod");
            //builder.setInviteCode("4ahstod");
            builder.setInviteCode("13650846501");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserBaseServiceProto.userInfoPdCombine.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserBaseServiceProto.userInfoPdCombine.class)));
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


    @Test
    public void getUserInfoPdByUnionIdOrOpenIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/info/pd/get/by/unionId/openId", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInfoUnionIdOpenIdRequest.Builder builder = UserBaseServiceProto.UserInfoUnionIdOpenIdRequest.newBuilder();
            builder.setChannelId(1);
            //builder.setOpenId("oBrt31R4O4Zrd48TIybHLQO462mA");
            //builder.setUnionId("ox-FY1S7mUlBilGVgDSMLfattSYc");
            //builder.setOpenId("oZmwj1tpZEGa22Qod03OhFxsQfvg22");
            //builder.setUnionId("nZmwj1tpZEGa22Qod03OhFxsQfvgunion");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserBaseServiceProto.userInfoPdCombine.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserBaseServiceProto.userInfoPdCombine.class)));
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


    @Test
    public void userHeadImgUpdateTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/head/img/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserHeadImgUpdateRequest.Builder builder = UserBaseServiceProto.UserHeadImgUpdateRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("178803");
            builder.setHeadImageUrl("https://thirdwx.qlogo.cn/mmopen/vi_32/R5lQElgibXMKpLz3wqTdMAQontMnV5BDfX8YvHoia4U4cpmu5hiclLFw9eb9ADJ40NgUflKgficZ8yBKlC7cp92kvQ/132");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("result:" + resp.getCode().name());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Test
    public void userUpdateTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBaseInfoUpdateRequest.Builder builder = UserBaseServiceProto.UserBaseInfoUpdateRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("178803");
            builder.setSex(1);
            builder.setUserType(3);
            builder.setRealName("程现");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            JsonFormat jsonFormat =new JsonFormat();
            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserBaseInfoUpdateResponse resp = UserBaseServiceProto.UserBaseInfoUpdateResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void userNickNameUpdateTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/nick/name/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserNickNameUpdateRequest.Builder builder = UserBaseServiceProto.UserNickNameUpdateRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("178803");
            builder.setNickName("xiaogege");

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);

            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("result:" + resp.getCode().name());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void userMobileUpdateChannelIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/mobile/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserMobileUpdateRequest.Builder builder = UserBaseServiceProto.UserMobileUpdateRequest.newBuilder();
            builder.setChannelId(0);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("result:" + resp.getCode().name());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @Test
    public void userMobileUpdateChannelUserIdTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/mobile/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserMobileUpdateRequest.Builder builder = UserBaseServiceProto.UserMobileUpdateRequest.newBuilder();
            builder.setChannelUserId("");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("result:" + resp.getCode().name());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void userMobileUpdateMobileNewTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/mobile/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserMobileUpdateRequest.Builder builder = UserBaseServiceProto.UserMobileUpdateRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("721577265782492");
            builder.setMobileNew("123456333");
            builder.setMobileAreaCodeNew("86");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("result:" + resp.getCode().name());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @Test
    public void userMobileUpdateUser_not_existTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/mobile/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserMobileUpdateRequest.Builder builder = UserBaseServiceProto.UserMobileUpdateRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("178803sdfsdfsfs");
            builder.setMobileNew("11457692089");
            builder.setMobileAreaCodeNew("86");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("result:" + resp.getCode().name());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void userMobileUpdateTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/mobile/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserMobileUpdateRequest.Builder builder = UserBaseServiceProto.UserMobileUpdateRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("178803");
            builder.setMobileNew("11457692089");
            builder.setMobileAreaCodeNew("86");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("result:" + resp.getCode().name());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void userMobileUpdateMobileAreaCode_NULL_Test(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/mobile/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserMobileUpdateRequest.Builder builder = UserBaseServiceProto.UserMobileUpdateRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("178803");
            builder.setMobileNew("6654645435");
            //builder.setMobileAreaCodeNew("86");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("result:" + resp.getCode().name());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void userRoleUpdateTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/role/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserRoleUpdateRequest.Builder builder = UserBaseServiceProto.UserRoleUpdateRequest.newBuilder();
            builder.setChannelId(1);
            builder.setChannelUserId("178803");
            builder.setRole(2);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("result:" + resp.getCode().name());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @Test
    public void userTagUpdateTest(){
        try {
            URI uri = new URI(SCHEME, null, HOST, POST, "/base/user/tag/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInfoTagRequest.Builder builder = UserBaseServiceProto.UserInfoTagRequest.newBuilder();
            builder.setChannelUserId("178803");
            builder.setUserTag(2);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("msg:" + resp.getMsg());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("result:" + resp.getCode().name());
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void getUserInfoByInviteCodeTest(){
        try {

           // URI uri = new URI(SCHEME, null, HOST, POST, "/base/get/by/invite/code", "", null);
            URI uri = new URI(SCHEME, null, "172.18.0.112", 8080, "/base/get/by/invite/code", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInviteCodeQueryRequest.Builder builder = UserBaseServiceProto.UserInviteCodeQueryRequest.newBuilder();
            //builder.setInviteCode("P88VCdO");
            builder.setInviteCode("13650846501");
            builder.setChannelId(1);
            //builder.setChannelUserId("361568706210078");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserBaseServiceProto.UserInfoInviteCodeResponse.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserBaseServiceProto.UserInfoInviteCodeResponse.class)));
                } else {
                    System.out.println("result:" + resp.getData());
                }

            } else {
                System.out.println(response.getStatusLine().getStatusCode());
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    @Test
    public void mobileAreaCodeTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/mobile/area/code/list", "", null);
            HttpPost post = new HttpPost(uri);
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                    System.out.println("result:" + resp.getCodeValue());
                    System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                    System.out.println("msg:" + resp.getMsg());
                    if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserBaseServiceProto.InterMobileAreaCodeResponse.class)) {
                        System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserBaseServiceProto.InterMobileAreaCodeResponse.class)));
                    } else {

                     }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }





    @Test
    public void userPdRegisterChannelIdTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/pd/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBasePdRegisterRequest.Builder builder = UserBaseServiceProto.UserBasePdRegisterRequest.newBuilder();
            UserBaseServiceProto.UserBaseRegister.Builder  userBaseRegisterBuild = UserBaseServiceProto.UserBaseRegister.newBuilder();

            userBaseRegisterBuild.setChannelId(0);
            builder.setUserBaseRegister(userBaseRegisterBuild);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserInfoPdRegisterResponse resp = UserBaseServiceProto.UserInfoPdRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void userPdRegisterMobileTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/pd/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBasePdRegisterRequest.Builder builder = UserBaseServiceProto.UserBasePdRegisterRequest.newBuilder();
            UserBaseServiceProto.UserBaseRegister.Builder  userBaseRegisterBuild = UserBaseServiceProto.UserBaseRegister.newBuilder();

            userBaseRegisterBuild.setChannelId(1);
            userBaseRegisterBuild.setMobile("");
            builder.setUserBaseRegister(userBaseRegisterBuild);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserInfoPdRegisterResponse resp = UserBaseServiceProto.UserInfoPdRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void userPdRegisterInviterTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/pd/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBasePdRegisterRequest.Builder builder = UserBaseServiceProto.UserBasePdRegisterRequest.newBuilder();
            UserBaseServiceProto.UserBaseRegister.Builder  userBaseRegisterBuild = UserBaseServiceProto.UserBaseRegister.newBuilder();
            UserBaseServiceProto.UserBasePdRegister.Builder  userBasePdRegisterBuild = UserBaseServiceProto.UserBasePdRegister.newBuilder();

            userBaseRegisterBuild.setChannelId(1);
            userBaseRegisterBuild.setMobile("12909082411");
            userBasePdRegisterBuild.setRegisterInviteCode("xxxxxxxxxx");
            builder.setUserBaseRegister(userBaseRegisterBuild);
            builder.setUserBasePdRegister(userBasePdRegisterBuild);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserInfoPdRegisterResponse resp = UserBaseServiceProto.UserInfoPdRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void userPdRegisterMoble_Register_DoneTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/pd/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBasePdRegisterRequest.Builder builder = UserBaseServiceProto.UserBasePdRegisterRequest.newBuilder();
            UserBaseServiceProto.UserBaseRegister.Builder  userBaseRegisterBuild = UserBaseServiceProto.UserBaseRegister.newBuilder();
            UserBaseServiceProto.UserBasePdRegister.Builder  userBasePdRegisterBuild = UserBaseServiceProto.UserBasePdRegister.newBuilder();

            userBaseRegisterBuild.setChannelId(1);
            userBaseRegisterBuild.setMobileAreaCode("68");
            userBaseRegisterBuild.setMobile("12690824711");
            userBasePdRegisterBuild.setRegisterInviteCode("Mw2mABG");
            builder.setUserBaseRegister(userBaseRegisterBuild);
            builder.setUserBasePdRegister(userBasePdRegisterBuild);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserInfoPdRegisterResponse resp = UserBaseServiceProto.UserInfoPdRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void userPdRegisterLoginInfoTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/pd/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBasePdRegisterRequest.Builder builder = UserBaseServiceProto.UserBasePdRegisterRequest.newBuilder();
            UserBaseServiceProto.UserBaseRegister.Builder  userBaseRegisterBuild = UserBaseServiceProto.UserBaseRegister.newBuilder();
            UserBaseServiceProto.UserBasePdRegister.Builder  userBasePdRegisterBuild = UserBaseServiceProto.UserBasePdRegister.newBuilder();

            userBaseRegisterBuild.setChannelId(1);
            userBaseRegisterBuild.setMobileAreaCode("68");
            userBaseRegisterBuild.setMobile("12690824711");
            userBasePdRegisterBuild.setRegisterInviteCode("Mw2mABG");
            builder.setUserBaseRegister(userBaseRegisterBuild);
            builder.setUserBasePdRegister(userBasePdRegisterBuild);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserInfoPdRegisterResponse resp = UserBaseServiceProto.UserInfoPdRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }


    @Test
    public void userPdRegisterTest() {
        try {
            URI uri = new URI("http", null, HOST, POST, "/base/user/pd/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBasePdRegisterRequest.Builder builder = UserBaseServiceProto.UserBasePdRegisterRequest.newBuilder();
            UserBaseServiceProto.UserBaseRegister.Builder  userBaseRegisterBuild = UserBaseServiceProto.UserBaseRegister.newBuilder();
            UserBaseServiceProto.UserBasePdRegister.Builder  userBasePdRegisterBuild = UserBaseServiceProto.UserBasePdRegister.newBuilder();
            UserBaseServiceProto.UserLoginRegister.Builder  UserLoginRegister = UserBaseServiceProto.UserLoginRegister.newBuilder();

            userBaseRegisterBuild.setMobileAreaCode("68");
            userBaseRegisterBuild.setMobile("18888888891");
//            userBaseRegisterBuild.setChannelUserId("56903884868");

            userBaseRegisterBuild.setHeadImgUrl("/sdf/sdf/sdfsdf");
            userBaseRegisterBuild.setBirthday("2019-04-04");
            userBaseRegisterBuild.setSex(1);
            userBaseRegisterBuild.setNickName("大花生");
            userBaseRegisterBuild.setSource(1);
            userBaseRegisterBuild.setRealName("大花生");
            userBaseRegisterBuild.setChannelId(1);
            userBaseRegisterBuild.setIdentityCard("434233425567868");
            userBaseRegisterBuild.setUserRole(1);

            UserLoginRegister.setLoginPwd(userBaseRegisterBuild.getMobile());
            UserLoginRegister.setLoginName(userBaseRegisterBuild.getMobile());

            userBasePdRegisterBuild.setRegisterInviteCode("Mw2mABG");
            userBasePdRegisterBuild.setInviteCode("jkdsfwe");
            userBasePdRegisterBuild.setCompanyId(1);
            userBasePdRegisterBuild.setOperator(1);
            userBasePdRegisterBuild.setIsOfflineOperator(1);
            userBasePdRegisterBuild.setIsOfflineUser(1);
            userBasePdRegisterBuild.setUserTagStatus(1);

            builder.setUserBaseRegister(userBaseRegisterBuild);
            builder.setUserBasePdRegister(userBasePdRegisterBuild);
            builder.setUserLoginRegister(UserLoginRegister);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserInfoPdRegisterResponse resp = UserBaseServiceProto.UserInfoPdRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }




    @Test
    public void userPdRegisterExistTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/pd/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBasePdRegisterRequest.Builder builder = UserBaseServiceProto.UserBasePdRegisterRequest.newBuilder();
            UserBaseServiceProto.UserBaseRegister.Builder  userBaseRegisterBuild = UserBaseServiceProto.UserBaseRegister.newBuilder();
            UserBaseServiceProto.UserBasePdRegister.Builder  userBasePdRegisterBuild = UserBaseServiceProto.UserBasePdRegister.newBuilder();
            UserBaseServiceProto.UserLoginRegister.Builder  UserLoginRegister = UserBaseServiceProto.UserLoginRegister.newBuilder();

            userBaseRegisterBuild.setMobileAreaCode("68");
            userBaseRegisterBuild.setMobile("12650824711");
            userBaseRegisterBuild.setChannelUserId("3453409540303");

            userBaseRegisterBuild.setHeadImgUrl("/sdf/sdf/sdfsdf");
            userBaseRegisterBuild.setBirthday("2019-04-04");
            userBaseRegisterBuild.setSex(1);
            userBaseRegisterBuild.setNickName("大花生");
            userBaseRegisterBuild.setSource(1);
            userBaseRegisterBuild.setRealName("大花生");
            userBaseRegisterBuild.setChannelId(1);
            userBaseRegisterBuild.setIdentityCard("434233425567868");
            userBaseRegisterBuild.setUserRole(1);

            UserLoginRegister.setLoginPwd(userBaseRegisterBuild.getMobile());
            UserLoginRegister.setLoginName(userBaseRegisterBuild.getMobile());

            userBasePdRegisterBuild.setRegisterInviteCode("Mw2mABG");
            userBasePdRegisterBuild.setInviteCode("jkdsfwe");
            userBasePdRegisterBuild.setCompanyId(1);
            userBasePdRegisterBuild.setOperator(1);
            userBasePdRegisterBuild.setIsOfflineOperator(1);
            userBasePdRegisterBuild.setIsOfflineUser(1);
            userBasePdRegisterBuild.setUserTagStatus(1);

            builder.setUserBaseRegister(userBaseRegisterBuild);
            builder.setUserBasePdRegister(userBasePdRegisterBuild);
            builder.setUserLoginRegister(UserLoginRegister);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserInfoPdRegisterResponse resp = UserBaseServiceProto.UserInfoPdRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    @Test
    public void userPdRegisterInviteCode_NO_Test() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/pd/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBasePdRegisterRequest.Builder builder = UserBaseServiceProto.UserBasePdRegisterRequest.newBuilder();
            UserBaseServiceProto.UserBaseRegister.Builder  userBaseRegisterBuild = UserBaseServiceProto.UserBaseRegister.newBuilder();
            UserBaseServiceProto.UserBasePdRegister.Builder  userBasePdRegisterBuild = UserBaseServiceProto.UserBasePdRegister.newBuilder();
            UserBaseServiceProto.UserLoginRegister.Builder  UserLoginRegister = UserBaseServiceProto.UserLoginRegister.newBuilder();

            userBaseRegisterBuild.setMobileAreaCode("68");
            userBaseRegisterBuild.setMobile("145690824611");
            userBaseRegisterBuild.setChannelUserId(Math.round((int)(Math.random()*10))+""+System.currentTimeMillis());
            userBaseRegisterBuild.setHeadImgUrl("/sdf/sdf/sdfsdf");
            userBaseRegisterBuild.setBirthday("2019-04-04");
            userBaseRegisterBuild.setSex(1);
            userBaseRegisterBuild.setNickName("大花生");
            userBaseRegisterBuild.setSource(1);
            userBaseRegisterBuild.setRealName("大花生");
            userBaseRegisterBuild.setChannelId(1);
            userBaseRegisterBuild.setIdentityCard("434233425567868");
            userBaseRegisterBuild.setUserRole(1);

            UserLoginRegister.setLoginPwd(userBaseRegisterBuild.getMobile());
            UserLoginRegister.setLoginName(userBaseRegisterBuild.getMobile());

            userBasePdRegisterBuild.setRegisterInviteCode("Mw2mABG");
            userBasePdRegisterBuild.setCompanyId(1);
            userBasePdRegisterBuild.setOperator(1);
            userBasePdRegisterBuild.setIsOfflineOperator(1);
            userBasePdRegisterBuild.setIsOfflineUser(1);
            userBasePdRegisterBuild.setUserTagStatus(1);

            builder.setUserBaseRegister(userBaseRegisterBuild);
            builder.setUserBasePdRegister(userBasePdRegisterBuild);
            builder.setUserLoginRegister(UserLoginRegister);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserInfoPdRegisterResponse resp = UserBaseServiceProto.UserInfoPdRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @Test
    public void userPdRegisterChannelUserId_NO_Test() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/pd/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBasePdRegisterRequest.Builder builder = UserBaseServiceProto.UserBasePdRegisterRequest.newBuilder();
            UserBaseServiceProto.UserBaseRegister.Builder  userBaseRegisterBuild = UserBaseServiceProto.UserBaseRegister.newBuilder();
            UserBaseServiceProto.UserBasePdRegister.Builder  userBasePdRegisterBuild = UserBaseServiceProto.UserBasePdRegister.newBuilder();
            UserBaseServiceProto.UserLoginRegister.Builder  UserLoginRegister = UserBaseServiceProto.UserLoginRegister.newBuilder();

            userBaseRegisterBuild.setMobileAreaCode("68");
            userBaseRegisterBuild.setMobile("10690824611");
           // userBaseRegisterBuild.setChannelUserId(Math.round((int)(Math.random()*10))+""+System.currentTimeMillis());
            userBaseRegisterBuild.setHeadImgUrl("/sdf/sdf/sdfsdf");
            userBaseRegisterBuild.setBirthday("2019-04-04");
            userBaseRegisterBuild.setSex(1);
            userBaseRegisterBuild.setNickName("大花生");
            userBaseRegisterBuild.setSource(1);
            userBaseRegisterBuild.setRealName("大花生");
            userBaseRegisterBuild.setChannelId(1);
            userBaseRegisterBuild.setIdentityCard("434233425567868");
            userBaseRegisterBuild.setUserRole(1);

            UserLoginRegister.setLoginPwd(userBaseRegisterBuild.getMobile());
            UserLoginRegister.setLoginName(userBaseRegisterBuild.getMobile());

            userBasePdRegisterBuild.setRegisterInviteCode("Mw2mABG");
            userBasePdRegisterBuild.setCompanyId(1);
            userBasePdRegisterBuild.setOperator(1);
            userBasePdRegisterBuild.setIsOfflineOperator(1);
            userBasePdRegisterBuild.setIsOfflineUser(1);
            userBasePdRegisterBuild.setUserTagStatus(1);

            builder.setUserBaseRegister(userBaseRegisterBuild);
            builder.setUserBasePdRegister(userBasePdRegisterBuild);
            builder.setUserLoginRegister(UserLoginRegister);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserInfoPdRegisterResponse resp = UserBaseServiceProto.UserInfoPdRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @Test
    public void userPdRegisterWechatTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/pd/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBasePdRegisterRequest.Builder builder = UserBaseServiceProto.UserBasePdRegisterRequest.newBuilder();
            UserBaseServiceProto.UserBaseRegister.Builder  userBaseRegisterBuild = UserBaseServiceProto.UserBaseRegister.newBuilder();
            UserBaseServiceProto.UserBasePdRegister.Builder  userBasePdRegisterBuild = UserBaseServiceProto.UserBasePdRegister.newBuilder();
            UserBaseServiceProto.UserLoginRegister.Builder  UserLoginRegister = UserBaseServiceProto.UserLoginRegister.newBuilder();
            UserBaseServiceProto.UserPdWeChatRegister.Builder  userPdWeChatRegister = UserBaseServiceProto.UserPdWeChatRegister.newBuilder();

            userBaseRegisterBuild.setMobileAreaCode("68");
            userBaseRegisterBuild.setMobile("798989866");
            userBaseRegisterBuild.setChannelUserId(Math.round((int)(Math.random()*100))+""+System.currentTimeMillis());
            userBaseRegisterBuild.setHeadImgUrl("/sdf/sdf/sdfsdf");
            userBaseRegisterBuild.setBirthday("2019-04-04");
            userBaseRegisterBuild.setSex(1);
            userBaseRegisterBuild.setNickName("大花生");
            userBaseRegisterBuild.setSource(1);
            userBaseRegisterBuild.setRealName("大花生");
            userBaseRegisterBuild.setChannelId(1);
            //userBaseRegisterBuild.setIdentityCard("434233425567868");
            userBaseRegisterBuild.setUserRole(1);

            userPdWeChatRegister.setOpenId("424234234543434");
            userPdWeChatRegister.setUnionId("234234234234");
            userPdWeChatRegister.setSex(1);
            userPdWeChatRegister.setAppId("21312321234");
            //userPdWeChatRegister.setCity("简阳");
            //userPdWeChatRegister.setCountry("中国");
            userPdWeChatRegister.setHeadImg("/sdfsd/fsdf/sdfsdfdf");
            userPdWeChatRegister.setNickName("胡乃亮");
            userPdWeChatRegister.setProvince("湖南省");

            //UserLoginRegister.setLoginPwd(userBaseRegisterBuild.getMobile());
            UserLoginRegister.setLoginName(userBaseRegisterBuild.getMobile());

            userBasePdRegisterBuild.setRegisterInviteCode("Mw2mABG");
            userBasePdRegisterBuild.setInviteCode("jkdsfwe");
            userBasePdRegisterBuild.setCompanyId(1);
            userBasePdRegisterBuild.setOperator(1);
            //userBasePdRegisterBuild.setIsOfflineOperator(1);
            //userBasePdRegisterBuild.setIsOfflineUser(1);
            userBasePdRegisterBuild.setUserTagStatus(1);

            builder.setUserBaseRegister(userBaseRegisterBuild);
            builder.setUserBasePdRegister(userBasePdRegisterBuild);
            builder.setUserLoginRegister(UserLoginRegister);
            builder.setUserPdWeChatRegister(userPdWeChatRegister);

            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserInfoPdRegisterResponse resp = UserBaseServiceProto.UserInfoPdRegisterResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }



    @Test
    public void userZyyxRegisterTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/register", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserBaseRegisterRequest.Builder builder = UserBaseServiceProto.UserBaseRegisterRequest.newBuilder();
            UserBaseServiceProto.UserBaseRegister.Builder  userBaseRegisterBuild = UserBaseServiceProto.UserBaseRegister.newBuilder();
            UserBaseServiceProto.UserBasePdRegister.Builder  userBasePdRegisterBuild = UserBaseServiceProto.UserBasePdRegister.newBuilder();
            UserBaseServiceProto.UserLoginRegister.Builder  UserLoginRegister = UserBaseServiceProto.UserLoginRegister.newBuilder();
            UserBaseServiceProto.UserWeChatRegister.Builder  UserWeChatRegister = UserBaseServiceProto.UserWeChatRegister.newBuilder();
            UserBaseServiceProto.UserZyyxRegister.Builder  userZyyxRegister = UserBaseServiceProto.UserZyyxRegister.newBuilder();

            userBaseRegisterBuild.setMobileAreaCode("68");
            userBaseRegisterBuild.setMobile("534565654");
            userBaseRegisterBuild.setChannelUserId(Math.round((int)(Math.random()*100))+""+System.currentTimeMillis());
            userBaseRegisterBuild.setHeadImgUrl("/sdf/sdf/sdfsdf");
            userBaseRegisterBuild.setBirthday("2019-06-04");
            userBaseRegisterBuild.setSex(0);
            userBaseRegisterBuild.setNickName("小华直邮");
            userBaseRegisterBuild.setSource(1);
            userBaseRegisterBuild.setRealName("小华直邮");
            userBaseRegisterBuild.setChannelId(1);
            userBaseRegisterBuild.setIdentityCard("634233425567868");
            userBaseRegisterBuild.setUserRole(1);


            UserWeChatRegister.setOpenId("");
            UserWeChatRegister.setUnionId("");

            userZyyxRegister.setRegisterRecommendUserId("661571043571580");

            UserLoginRegister.setLoginPwd(userBaseRegisterBuild.getMobile());
            UserLoginRegister.setLoginName(userBaseRegisterBuild.getMobile());
            userBasePdRegisterBuild.setRegisterInviteCode("Mw2mABG");
            userBasePdRegisterBuild.setInviteCode("jkdsfwe");
            userBasePdRegisterBuild.setCompanyId(1);
            userBasePdRegisterBuild.setOperator(1);
            userBasePdRegisterBuild.setIsOfflineOperator(1);
            userBasePdRegisterBuild.setIsOfflineUser(1);
            userBasePdRegisterBuild.setUserTagStatus(1);

            builder.setUserBaseRegister(userBaseRegisterBuild);
            builder.setUserBasePdRegister(userBasePdRegisterBuild);
            builder.setUserLoginRegister(UserLoginRegister);
            builder.setUserWeChatRegister(UserWeChatRegister);
            builder.setUserZyyxRegister(userZyyxRegister);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");

            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + resp.getCodeValue());
                System.out.println("businessExceptionCode:" + resp.getBusinessExceptionCode());
                System.out.println("msg:" + resp.getMsg());
                if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(UserBaseServiceProto.UserInfoInviteCodeResponse.class)) {
                    System.out.println("result:" + jsonFormat.printToString(resp.getData().unpack(UserBaseServiceProto.UserInfoInviteCodeResponse.class)));
                } else {

                }
            } else {
                System.out.println(response.getStatusLine().getStatusCode());

            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Test
    public void userStatusChannelIdTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/status/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserStatusUpdateRequest.Builder builder = UserBaseServiceProto.UserStatusUpdateRequest.newBuilder();
            
            builder.setChannelId(0);
            builder.setChannelUserId("71575526564016");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserStatusUpdateResponse resp = UserBaseServiceProto.UserStatusUpdateResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Test
    public void userStatusChannelUserIdTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/status/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserStatusUpdateRequest.Builder builder = UserBaseServiceProto.UserStatusUpdateRequest.newBuilder();
            
            builder.setChannelId(1);
            builder.setChannelUserId("");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserStatusUpdateResponse resp = UserBaseServiceProto.UserStatusUpdateResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Test
    public void userStatusChannelUserId_Not_ExistTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/status/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserStatusUpdateRequest.Builder builder = UserBaseServiceProto.UserStatusUpdateRequest.newBuilder();
            
            builder.setChannelId(1);
            builder.setChannelUserId("xxxxxxxxxxx");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserStatusUpdateResponse resp = UserBaseServiceProto.UserStatusUpdateResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Test
    public void userStatusUserStatusLtTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/status/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserStatusUpdateRequest.Builder builder = UserBaseServiceProto.UserStatusUpdateRequest.newBuilder();
    
            builder.setChannelId(1);
            builder.setChannelUserId("71575526564016");
            builder.setUserStatus(-1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserStatusUpdateResponse resp = UserBaseServiceProto.UserStatusUpdateResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    @Test
    public void userStatusUserStatusGtTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/status/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserStatusUpdateRequest.Builder builder = UserBaseServiceProto.UserStatusUpdateRequest.newBuilder();
            
            builder.setChannelId(1);
            builder.setChannelUserId("721577265782492");
            builder.setUserStatus(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserStatusUpdateResponse resp = UserBaseServiceProto.UserStatusUpdateResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    @Test
    public void userStatusTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/status/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserStatusUpdateRequest.Builder builder = UserBaseServiceProto.UserStatusUpdateRequest.newBuilder();
            
            builder.setChannelId(1);
            builder.setChannelUserId("71575526564016");
            builder.setUserStatus(1);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserStatusUpdateResponse resp = UserBaseServiceProto.UserStatusUpdateResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @Test
    public void userStatusFrozenTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/status/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserStatusUpdateRequest.Builder builder = UserBaseServiceProto.UserStatusUpdateRequest.newBuilder();
            
            builder.setChannelId(1);
            builder.setChannelUserId("71575526564016");
            builder.setUserStatus(0);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserStatusUpdateResponse resp = UserBaseServiceProto.UserStatusUpdateResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    @Test
    public void userStatusFrozenCancelTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/status/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserStatusUpdateRequest.Builder builder = UserBaseServiceProto.UserStatusUpdateRequest.newBuilder();
            
            builder.setChannelId(1);
            builder.setChannelUserId("71575526564016");
            builder.setUserStatus(2);
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.UserStatusUpdateResponse resp = UserBaseServiceProto.UserStatusUpdateResponse.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    @Test
    public void userInviteCodeChannelUserIdTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/invite/code/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInviteCodeUpdateRequest.Builder builder = UserBaseServiceProto.UserInviteCodeUpdateRequest.newBuilder();
            builder.setChannelUserId("");
            builder.setInviteCode("mw2mabg");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.ResponseCode resp = UserBaseServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
                
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    
    @Test
    public void userInviteCodeTest() {
        try {
            URI uri = new URI("http", null, "127.0.0.1", 8080, "/base/user/invite/code/update", "", null);
            HttpPost post = new HttpPost(uri);
            UserBaseServiceProto.UserInviteCodeUpdateRequest.Builder builder = UserBaseServiceProto.UserInviteCodeUpdateRequest.newBuilder();
            builder.setChannelUserId("721577265782492");
            builder.setInviteCode("mw2mabb");
            post.setEntity(new ByteArrayEntity(builder.build().toByteArray()));
            post.setHeader("Content-Type", "application/x-protobuf");
            
            HttpResponse response = httpClient.execute(post);
            JsonFormat jsonFormat =new JsonFormat();
            if (response.getStatusLine().getStatusCode() == 200) {
                UserBaseServiceProto.ResponseCode resp = UserBaseServiceProto.ResponseCode.parseFrom(response.getEntity().getContent());
                System.out.println("result:" + jsonFormat.printToString(resp));
            } else {
                System.out.println(response.getStatusLine().getStatusCode());
                
            }
        } catch (Exception e) {
            System.out.println(e);
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
