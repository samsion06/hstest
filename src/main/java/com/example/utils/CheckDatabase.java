package com.example.utils;

import com.example.domain.UserAddressInfo;
import com.example.domain.UserAliPayInfo;
import com.example.domain.UserWeChatInfo;
import com.example.mapper.UserBaseInfoMapper;
import org.testng.Assert;
import org.testng.Reporter;

public class CheckDatabase {

    private static UserAliPayInfo userAliPayInfo;
    private static UserWeChatInfo userWeChatInfos;
    private static UserAddressInfo userAddressInfo;
    private static String AllMsg="数据库全部匹配：";
    private static String PartMsg="数据库部分匹配：";

    /**
     * @param userBaseInfoMapper 查询数据库
     * @param method  用于区分查询那个表
     * @param TargetOutPut  目标值
     * @param channel_user_id 通过channel_user_id查询
     */

    //数据库检查
    public static void CheckDatabaseInfo(UserBaseInfoMapper userBaseInfoMapper, String method, String TargetOutPut,String channel_user_id){
        switch (method){
            //检查的点不同,所以要分开
            case "WeChatInfoUnbind": //微信解绑
                userWeChatInfos = userBaseInfoMapper.queryWeChatInfo(channel_user_id);
                int is_delete = userWeChatInfos.getIs_delete(); //比对
                Assert.assertEquals(1,is_delete);
                Reporter.log(AllMsg+"is_delete值变更为："+is_delete);
                break;
            case "WeChatInfoBind": //微信绑定
                userWeChatInfos = userBaseInfoMapper.queryWeChatInfo(channel_user_id);
                Assert.assertEquals(channel_user_id,userWeChatInfos.getChannel_user_id());
                System.out.println(userWeChatInfos);
                Reporter.log(AllMsg+userWeChatInfos);
                break;
            case "AliPayBind"://支付宝绑定
                userAliPayInfo=userBaseInfoMapper.queryAliPayInfo(channel_user_id);
                //Assert.assertEquals(userWeChatInfos.getChannel_user_id(),channel_user_id);
                System.out.println(userAliPayInfo);
                Reporter.log(AllMsg+userAliPayInfo);
                break;
            case "AliPayAuth"://支付宝授权
                userAliPayInfo=userBaseInfoMapper.queryAliPayInfo(channel_user_id);
                int bindStatus=userAliPayInfo.getStatus();
                Assert.assertEquals(1,bindStatus);
                Reporter.log(PartMsg+"Status值变更为："+bindStatus);
                break;
            case "AliPayCancel"://取消授权
                userAliPayInfo=userBaseInfoMapper.queryAliPayInfo(channel_user_id);
                int unbindStatus=userAliPayInfo.getStatus();
                Assert.assertEquals(2,unbindStatus);
                Reporter.log(PartMsg+"Status值变更为："+unbindStatus);
                break;
            case "AddressAdd"://添加爱收货地址
                userAddressInfo=userBaseInfoMapper.queryUserAddressInfo(channel_user_id);
                System.out.println(userAddressInfo);
                Reporter.log(AllMsg+userWeChatInfos);
            case "AddressGet":
                userAddressInfo=userBaseInfoMapper.queryUserAddressInfo(channel_user_id);
            case "AddressUpadate":
                userAddressInfo=userBaseInfoMapper.queryUserAddressInfo(channel_user_id);
            case "AddressDelete":
                userAddressInfo=userBaseInfoMapper.queryUserAddressInfo(channel_user_id);


            default:
                System.out.println("没找到方法");
                break;
        }
    }
}
