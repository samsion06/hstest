package com.example.utils;
import com.example.Obj.UserBaseInfo;
import com.example.mapper.UserBaseInfoMapper;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.googlecode.protobuf.format.JsonFormat;
import com.hs.user.base.proto.ResultResponse;
import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.Reporter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckReponseResult {
    private static JsonFormat jsonFormat;
    private static String resultContent = null;
    private static Map<String, Object> map;

    static {
        jsonFormat = new JsonFormat();
        map = new HashMap();
    }

    //截取返回内容  10  1 "userId" ","
    public static String substring(String result,String beginString,Integer beginindex,String endString,Integer endindex){
        int begin = result.indexOf(beginString) + beginindex;
        int end = result.indexOf(endString) - endindex;
        String channelUserId = result.substring(begin, end);
        return  channelUserId;
    }

    //返回字符串
    public static String checkResponseResult(HttpResponse response, Class<? extends Message> clazz) throws IOException {
        System.out.println(clazz);
        if (response.getStatusLine().getStatusCode() == 200) {
            ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
            if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(clazz)) {
                resultContent = jsonFormat.printToString(resp.getData().unpack(clazz));
            }else {
                System.out.println(resp.getCode());
            }
        }else{
            System.out.println(response.getStatusLine().getStatusCode());
        }
        return resultContent;
    }

    //返回对象和字符串
    public static Map<String, Object> checkResponseCodeAndObj(HttpResponse response, Class<? extends Message> clazz) throws Exception {
        System.out.println(clazz);
        if (response.getStatusLine().getStatusCode() == 200) {
            //ResultResponse.ResultSet.parseFrom这种格式转换返回内容
            ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
            //判断返回内容
            if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS && resp.getData().is(clazz)) {
                resultContent = jsonFormat.printToString(resp.getData().unpack(clazz));
                System.out.println("resultContent:" + resultContent);
                map.put("resultContent", resultContent);
                map.put("resultObject", resp);
            } else {
                System.out.println(resp.getCode());
            }
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
        return map;
    }

    //无返回对象
    public static void checkResponseCode(HttpResponse response) throws IOException {
        if (response.getStatusLine().getStatusCode() == 200) {
            ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
            if (resp.getCode() == ResultResponse.ResponseCode.RESP_CODE_SUCCESS) {
                System.out.println(resp.getCode());
            } else {
                System.out.println(resp.getCode());
            }
        } else {
            System.out.println(response.getStatusLine().getStatusCode());
        }
    }

    //返回内容检查 带对象
    public static String AssertResponses(HttpResponse response, Class<? extends Message> clazz) throws IOException {
        System.out.println(clazz);
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
        ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
        Assert.assertEquals(resp.getCode(),ResultResponse.ResponseCode.RESP_CODE_SUCCESS );
        Assert.assertTrue(resp.getData().is(clazz));
        resultContent = jsonFormat.printToString(resp.getData().unpack(clazz));
        System.out.println(resultContent);
        //记录结果
        Reporter.log(resultContent);
        //Assert.assertEquals(1,2);
        return  resultContent;
    }
    //返回内容检查 不带对象
    public static String AssertResponse(HttpResponse response) throws IOException {
        Assert.assertEquals(response.getStatusLine().getStatusCode(),200);
        ResultResponse.ResultSet resp = ResultResponse.ResultSet.parseFrom(response.getEntity().getContent());
        Assert.assertEquals(resp.getCode(),ResultResponse.ResponseCode.RESP_CODE_SUCCESS );
        resultContent=resp.getMsg();
       // System.out.println("出参: \n"+resultContent);
        Reporter.log(resultContent);
        return resultContent;
    }

    //数据库检查
    public static void CheckDatabaseInfo(UserBaseInfoMapper userBaseInfoMapper,String method,String TargetOutPut,String channel_user_id){
        //方法名看UserBaseInfoMapper.xml里面
        if(method.equals("queryWeChatInfo")){
            List<UserBaseInfo> userBaseInfos = userBaseInfoMapper.queryWeChatInfo(channel_user_id);
            for(UserBaseInfo userbaseinfo:userBaseInfos) {
                int is_delete = userbaseinfo.getIs_delete();
                Assert.assertEquals(is_delete, Integer.parseInt(TargetOutPut));
            }
        }else if(method.equals("queryUserBaseInfo")){
            System.out.println("调用queryUserBaseInfo方法");
        }else{
            System.out.println("没找到方法");
        }
    }
}
