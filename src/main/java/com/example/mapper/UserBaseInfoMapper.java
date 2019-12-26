package com.example.mapper;

import com.example.domain.UserAddressInfo;
import com.example.domain.UserAliPayInfo;
import com.example.domain.UserWeChatInfo;
import com.example.domain.UserBaseInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserBaseInfoMapper {
    //所有接口方法都写在这个接口上
    //查询用户
    public UserBaseInfo queryUserBaseInfo();

   //查询微信
   //public List<UserAlipayAuthInfo> queryWeChatInfos(String channel_user_id);
    public UserWeChatInfo queryWeChatInfo(String channel_user_id);

    //查询支付宝
    public UserAliPayInfo queryAliPayInfo(String channel_user_id);

    //查询收货地址
    public UserAddressInfo queryUserAddressInfo(String channel_user_id);

}
