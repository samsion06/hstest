package com.example.utils;

import com.example.Obj.UserAlipayAuthInfo;
import com.example.mapper.UserBaseInfoMapper;
import org.testng.Assert;
import org.testng.Reporter;

public class CheckDatabase {

    private static int is_delete;
    private static UserAlipayAuthInfo userBaseInfos;

    //数据库检查
    public static void CheckDatabaseInfo(UserBaseInfoMapper userBaseInfoMapper, String method, String TargetOutPut, String channel_user_id){
        switch (method){
            case "WeChatInfoUnbind":
                userBaseInfos = userBaseInfoMapper.queryWeChatInfo(channel_user_id);
                is_delete = userBaseInfos.getIs_delete();
                Assert.assertEquals(is_delete,1);
                Reporter.log("数据库部分匹配：user_alipay_auth_info表is_delete字段值变化为："+is_delete);
                break;
            case "WeChatInfoBind":
                userBaseInfos = userBaseInfoMapper.queryWeChatInfo(channel_user_id);
                Reporter.log("数据库全部匹配：user_alipay_auth_info表所有字段为："+userBaseInfos);
                break;
            default:
                System.out.println("没找到方法");
                break;
        }
    }
}
