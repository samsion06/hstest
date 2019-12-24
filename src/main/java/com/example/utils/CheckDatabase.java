package com.example.utils;

import com.example.Obj.UserWeChatInfo;
import com.example.mapper.UserBaseInfoMapper;
import org.testng.Assert;
import org.testng.Reporter;

public class CheckDatabase {

    private static UserWeChatInfo userBaseInfos;
    private static String AllMsg="数据库部分匹配";
    private static String PartMsg="数据库全部匹配";

    /**
     * @param userBaseInfoMapper 查询数据库
     * @param method  用于区分查询那个表
     * @param TargetOutPut  目标值
     * @param channel_user_id 通过channel_user_id查询
     */

    //数据库检查
    public static void CheckDatabaseInfo(UserBaseInfoMapper userBaseInfoMapper, String method, String TargetOutPut, String channel_user_id){
        switch (method){
            //检查的点不同,所以要分开 根据ChannelUserId查找
            case "WeChatInfoUnbind":
                userBaseInfos = userBaseInfoMapper.queryWeChatInfo(channel_user_id);
                int is_delete = userBaseInfos.getIs_delete();
                //比对
                Assert.assertEquals(is_delete,TargetOutPut);
                Reporter.log(AllMsg+"user_alipay_auth_info表is_delete字段值变化为："+is_delete);
                break;
            case "WeChatInfoBind":
                userBaseInfos = userBaseInfoMapper.queryWeChatInfo(channel_user_id);
                Assert.assertEquals(userBaseInfos.getChannel_user_id(),TargetOutPut);
                Reporter.log(PartMsg+"：user_alipay_auth_info表所有字段为："+userBaseInfos);
                break;
            case "添加地址方法":
                    System.out.println(userBaseInfoMapper.queryUserBaseInfo());

            default:
                System.out.println("没找到方法");
                break;
        }
    }
}
