package com.example.mapper;

import com.example.Obj.UserAlipayAuthInfo;
import com.example.Obj.UserBaseInfo;
import com.example.Obj.User_info;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserBaseInfoMapper {
    //所有接口方法都写在这个接口上

    //查询用户
    public List<UserBaseInfo> queryUserBaseInfo();

   //查询微信 返回集合
   //public List<UserAlipayAuthInfo> queryWeChatInfos(String channel_user_id);

    //返回单条对象
    public UserAlipayAuthInfo queryWeChatInfo(String channel_user_id);

}
