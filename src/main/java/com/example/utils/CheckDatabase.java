package com.example.utils;

import com.example.Obj.UserAliPayInfo;
import com.example.Obj.UserWeChatInfo;
import com.example.mapper.UserBaseInfoMapper;
import org.testng.Assert;
import org.testng.Reporter;

public class CheckDatabase {

    private static UserAliPayInfo userAliPayInfo;
    private static UserWeChatInfo userWeChatInfos;
    private static String AllMsg="数据库部分匹配";
    private static String PartMsg="数据库全部匹配";

    /**
     * @param userBaseInfoMapper 查询数据库
     * @param method  用于区分查询那个表
     * @param TargetOutPut  目标值
     * @param channel_user_id 通过channel_user_id查询
     */

    //数据库检查
    public static void CheckDatabaseInfo(UserBaseInfoMapper userBaseInfoMapper, String method, String TargetOutPut,String channel_user_id){
        switch (method){
            //检查的点不同,所以要分开 根据ChannelUserId查找
            case "WeChatInfoUnbind":
                userWeChatInfos = userBaseInfoMapper.queryWeChatInfo(channel_user_id);
                int is_delete = userWeChatInfos.getIs_delete();
                //比对
                Assert.assertEquals(is_delete,TargetOutPut);
                Reporter.log(AllMsg+"user_alipay_auth_info表is_delete字段值变化为："+is_delete);
                break;
            case "WeChatInfoBind":
                userWeChatInfos = userBaseInfoMapper.queryWeChatInfo(channel_user_id);
                Assert.assertEquals(userWeChatInfos.getChannel_user_id(),TargetOutPut);
                Reporter.log(PartMsg+"：user_alipay_auth_info表所有字段为："+userWeChatInfos);
                break;
            case "AliPayBind":
                userAliPayInfo=userBaseInfoMapper.queryAliPayInfo(channel_user_id);
                Assert.assertEquals(userWeChatInfos.getChannel_user_id(),channel_user_id);
                Reporter.log(PartMsg+"：user_alipay_auth_info表所有字段为"+userAliPayInfo);
                break;
            case "AliPayAuth":
                break;
            default:
                System.out.println("没找到方法");
                break;
        }
    }
}
