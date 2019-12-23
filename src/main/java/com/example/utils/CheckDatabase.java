package com.example.utils;

import com.example.Obj.UserBaseInfo;
import com.example.mapper.UserBaseInfoMapper;
import org.testng.Assert;

import java.util.List;

public class CheckDatabase {
    //数据库检查
    public static void CheckDatabaseInfo(UserBaseInfoMapper userBaseInfoMapper, String method, String TargetOutPut, String channel_user_id){
        switch (method){
            case "queryWeChatInfo":
                List<UserBaseInfo> userBaseInfos = userBaseInfoMapper.queryWeChatInfo(channel_user_id);
                for(UserBaseInfo userbaseinfo:userBaseInfos) {
                    int is_delete = userbaseinfo.getIs_delete();
                    Assert.assertEquals(is_delete, Integer.parseInt(TargetOutPut));
                }
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
