package com.example.utils;

import com.example.Obj.UserBaseInfo;
import com.example.mapper.UserBaseInfoMapper;
import org.testng.Assert;
import org.testng.Reporter;

import java.util.List;

public class CheckDatabase {
    //数据库检查
    public static void CheckDatabaseInfo(UserBaseInfoMapper userBaseInfoMapper, String method, String TargetOutPut, String channel_user_id){
        Integer is_delete=null;
        switch (method){
            case "queryWeChatInfo_unbind":
                List<UserBaseInfo> userBaseInfos = userBaseInfoMapper.queryWeChatInfo(channel_user_id);
                for(UserBaseInfo userbaseinfo:userBaseInfos) {
                    is_delete = userbaseinfo.getIs_delete();
                    Assert.assertEquals(is_delete.intValue(), Integer.parseInt(TargetOutPut));
                }
                Reporter.log("数据库部分匹配：user_alipay_auth_info表is_delete字段值变化为："+is_delete);
                break;
            case "queryUserBaseInfo":
                System.out.println("调用queryUserBaseInfo方法");
                break;
            default:
                System.out.println("没找到方法");
                break;
        }
    }
}
