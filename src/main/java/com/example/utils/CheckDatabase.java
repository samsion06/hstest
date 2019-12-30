package com.example.utils;

import com.example.domain.*;
import com.example.mapper.UserBaseInfoMapper;
import org.testng.Assert;
import org.testng.Reporter;

public class CheckDatabase {

    private static UserAliPayInfo userAliPayInfo;
    private static UserWeChatInfo userWeChatInfos;
    private static UserAddressInfo userAddressInfo;
    private static UserBaseInfo userBaseInfo;
    private static UserLoginInfo userLoginInfo;
    private static UserTaobaoInfo userTaobaoInfo;

    private static String AllMsg="数据库全部匹配：";
    private static String PartMsg="数据库部分匹配：";

    /**
     * @param userBaseInfoMapper 查询数据库
     * @param method  用于区分查询那个表 （检查的点不同,所以要分开）
     * @param targetOutPut  目标值
     * @param channelUserId 通过channel_user_id查询
     */

    //数据库检查
    public static void CheckDatabaseInfo(UserBaseInfoMapper userBaseInfoMapper,String method, String targetOutPut,String channelUserId){
        switch (method){
            //微信解绑
            case "WeChatInfoUnbind":
                userWeChatInfos = userBaseInfoMapper.queryWeChatInfo(channelUserId);
                int wechatIsDelete = userWeChatInfos.getIsDelete(); //比对
                Assert.assertEquals(1,wechatIsDelete);
                Reporter.log(AllMsg+"is_delete值变更为："+wechatIsDelete);
                break;
            case "WeChatInfoBind": //微信绑定
                userWeChatInfos = userBaseInfoMapper.queryWeChatInfo(channelUserId);
                Assert.assertEquals(channelUserId,userWeChatInfos.getChannelUserId());
                System.out.println(userWeChatInfos);
                Reporter.log(AllMsg+userWeChatInfos);
                break;
            case "AliPayBind"://支付宝绑定
                userAliPayInfo=userBaseInfoMapper.queryAliPayInfo(channelUserId);
                //Assert.assertEquals(userWeChatInfos.getChannel_user_id(),channelUserId);
                System.out.println(userAliPayInfo);
                Reporter.log(AllMsg+userAliPayInfo);
                break;
            case "AliPayAuth"://支付宝授权
                userAliPayInfo=userBaseInfoMapper.queryAliPayInfo(channelUserId);
                int bindStatus=userAliPayInfo.getStatus();
                Assert.assertEquals(1,bindStatus);
                Reporter.log(PartMsg+"Status值变更为："+bindStatus);
                break;
            case "AliPayCancel"://取消授权
                userAliPayInfo=userBaseInfoMapper.queryAliPayInfo(channelUserId);
                int unbindStatus=userAliPayInfo.getStatus();
                Assert.assertEquals(2,unbindStatus);
                Reporter.log(PartMsg+"Status值变更为："+unbindStatus);
                break;
            case "AddressUpadate"://更新收货地址
                userAddressInfo=userBaseInfoMapper.queryUserAddressInfo(channelUserId);
                String name=userAddressInfo.getName();
                Assert.assertEquals(targetOutPut,name); //名称是否有更新
                Reporter.log(PartMsg+"name值变更为："+name);
                break;
            case "AddressDelete"://删除收货地址
                userAddressInfo=userBaseInfoMapper.queryUserAddressInfo(channelUserId);
                System.out.println(userAddressInfo);
                int addressIsDelete = userAddressInfo.getIsDelete(); //比对
                Assert.assertEquals(1,addressIsDelete);
                Reporter.log(PartMsg+"is_delete值变更为："+addressIsDelete);
                break;
            case "NickNameUpdate": //昵称更新
                userBaseInfo=userBaseInfoMapper.queryUserBaseInfo(channelUserId);
                String nickname=userBaseInfo.getNickName();
                Assert.assertEquals(targetOutPut,nickname);
                Reporter.log(PartMsg+"nick_name值变更为："+nickname);
                break;
            case "HeadUrlImg": //修改头像
                userBaseInfo=userBaseInfoMapper.queryUserBaseInfo(channelUserId);
                String headurlimg=userBaseInfo.getHeadImg();
                Assert.assertEquals(targetOutPut,headurlimg);
                Reporter.log(PartMsg+"head_img值变更为："+headurlimg);
                break;
            case "MobileUpadate"://修改手机
                userLoginInfo=userBaseInfoMapper.queryUserLoginInfo(channelUserId);
                String mobile=userLoginInfo.getLoginName();
                Assert.assertEquals(targetOutPut,mobile);
                Reporter.log(PartMsg+"login_name值变更为："+mobile);
                break;
            case "PwdUpdate"://修改密码
                userLoginInfo=userBaseInfoMapper.queryUserLoginInfo(channelUserId);
                String pwd=userLoginInfo.getLoginPwd();
                Assert.assertEquals(targetOutPut,pwd);
                Reporter.log(PartMsg+"login_pwd值变更为："+pwd);
                break;
            case "TaoBaoAuth"://淘宝授权
                userTaobaoInfo=userBaseInfoMapper.queryUserTaobaoInfo(channelUserId);
                System.out.println(userTaobaoInfo);
                Reporter.log(AllMsg+userTaobaoInfo);
                break;
            case "TaoBaoCancel"://取消授权
                userTaobaoInfo=userBaseInfoMapper.queryUserTaobaoInfo(channelUserId);
                System.out.println(userTaobaoInfo);
                Reporter.log(AllMsg+userTaobaoInfo);
                break;
            default:
                System.out.println("没找到方法");
                break;
        }
    }
}
