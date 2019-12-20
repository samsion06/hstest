package com.example.mapper;

import com.example.Obj.UserBaseInfo;
import com.example.Obj.User_info;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserBaseInfoMapper {
    //查询用户
    public List<UserBaseInfo> queryUserBaseInfo();

   //查询微信
    public List<UserBaseInfo> queryWeChatInfo(String channel_user_id);


}
