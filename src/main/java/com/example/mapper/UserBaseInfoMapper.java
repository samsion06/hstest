package com.example.mapper;

import com.example.domain.*;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//所有接口方法都写在这个接口上
@Mapper
public interface UserBaseInfoMapper {

    //查询用户
    public UserBaseInfo queryUserBaseInfo(String channelUserId);

   //查询微信  public List<UserAlipayAuthInfo> queryWeChatInfos(String channel_user_id);
    public UserWeChatInfo queryWeChatInfo(String channelUserId);

    //查询支付宝
    public UserAliPayInfo queryAliPayInfo(String channelUserId);

    //查询收货地址
    public UserAddressInfo queryUserAddressInfo(String channelUserId);

    //查询登录信息
    public UserLoginInfo queryUserLoginInfo(String channelUserId);

    //查询淘宝信息
    public UserTaobaoInfo queryUserTaobaoInfo(String channelUserId);

}
