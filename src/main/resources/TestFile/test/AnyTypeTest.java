package com.hs.user.base.test;

import com.google.protobuf.Any;
import com.google.protobuf.InvalidProtocolBufferException;
import com.hs.user.base.proto.ResultResponse;
import com.hs.user.base.proto.UserLoginInfoServiceProto;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

public class AnyTypeTest {
    private CloseableHttpClient httpClient;
    private  int times=100000;


    @Test
    public void anyTypeTest() {
        UserLoginInfoServiceProto.UserLoginInfo.Builder userLogTest = UserLoginInfoServiceProto.UserLoginInfo.newBuilder();

            try {
                userLogTest.setUserType(1);
                userLogTest.setBirthday("2019-09-01");
                userLogTest.setStatus(1);
                userLogTest.setSource(1);
                userLogTest.setSex(1);
                userLogTest.setRole(1);
                userLogTest.setRealName("小明");
                userLogTest.setNickName("小明");
                userLogTest.setOperator(1);
                userLogTest.setMobile("13498988998");
                userLogTest.setHeadImgUrl("/sd/sdf/sdf/sdfsdfsdf/sdf/");
                userLogTest.setGrandfatherId(1);
                userLogTest.setCompanyId(123213123);
                userLogTest.setChannelUserId("sdfwe3212321dsfsd23fdf");
                userLogTest.setChannelId(21323123124l);
                userLogTest.setId(12321312l);
                userLogTest.setIsDelete(0);
                userLogTest.setFatherId(23123123);
                Long startTime =System.currentTimeMillis();
                for (int i=0;i<times;i++) {
                	UserLoginInfoServiceProto.UserLoginInfo.parseFrom(userLogTest.build().toByteArray());
                }
                System.out.println(times+"次序列化与反序列化 非Any类型总耗时："+((System.currentTimeMillis()-startTime)/1000d)+" 秒");
            } catch (InvalidProtocolBufferException e) {
                e.printStackTrace();
            }

    }


    @Test
    public void AnyTypeTest2() {
        ResultResponse.ResultSet.Builder resp =ResultResponse.ResultSet.newBuilder();
        resp.setCode(ResultResponse.ResponseCode.RESP_CODE_SUCCESS);
        resp.setMsg(ResultResponse.ResponseCode.RESP_CODE_SUCCESS.name());
        UserLoginInfoServiceProto.UserLoginInfo.Builder userLogTest = UserLoginInfoServiceProto.UserLoginInfo.newBuilder();
        userLogTest.setUserType(1);
        userLogTest.setBirthday("2019-09-01");
        userLogTest.setStatus(1);
        userLogTest.setSource(1);
        userLogTest.setSex(1);
        userLogTest.setRole(1);
        userLogTest.setRealName("小明");
        userLogTest.setNickName("小明");
        userLogTest.setOperator(1);
        userLogTest.setMobile("13498988998");
        userLogTest.setHeadImgUrl("/sd/sdf/sdf/sdfsdfsdf/sdf/");
        userLogTest.setGrandfatherId(1);
        userLogTest.setCompanyId(123213123);
        userLogTest.setChannelUserId("sdfwe3212321dsfsd23fdf");
        userLogTest.setChannelId(21323123124l);
        userLogTest.setId(12321312l);
        userLogTest.setIsDelete(0);
        userLogTest.setFatherId(23123123);

        try {
            Long startTime =System.currentTimeMillis();
            ResultResponse.ResultSet  sult;
            for (int i=0;i<times;i++) {
                resp.setData(Any.pack(userLogTest.build()));
                sult =ResultResponse.ResultSet.parseFrom(resp.build().toByteArray());
                sult.getData().unpack(UserLoginInfoServiceProto.UserLoginInfo.class);
            }
            System.out.println(times+"次序列化与反序列化 Any类型总耗时："+((System.currentTimeMillis()-startTime)/1000d)+" 秒");
        } catch (InvalidProtocolBufferException e) {
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
