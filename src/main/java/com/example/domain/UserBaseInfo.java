package com.example.domain;

import java.util.Date;

public class UserBaseInfo {

    private Long id;
    private Long userId;
    private Long channelId;
    private String channelUserId;
    private String nickName;
    private String realName;
    private String mobileAreaCode;
    private String mobile;
    private Integer sex;
    private String  birthday;
    private String headImg;
    private String identityCard;
    private Integer userRole;
    private Integer source;
    private String registerRecommendUserId;
    private Integer userStatus;
    private Integer isDelete;
    private Date creatTime;
    private Date updateTime;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getChannelId() {
        return channelId;
    }

    public void setChannelId(Long channelId) {
        this.channelId = channelId;
    }

    public String getChannelUserId() {
        return channelUserId;
    }

    public void setChannelUserId(String channelUserId) {
        this.channelUserId = channelUserId;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getMobileAreaCode() {
        return mobileAreaCode;
    }

    public void setMobileAreaCode(String mobileAreaCode) {
        this.mobileAreaCode = mobileAreaCode;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public Integer getUserRole() {
        return userRole;
    }

    public void setUserRole(Integer userRole) {
        this.userRole = userRole;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getRegisterRecommendUserId() {
        return registerRecommendUserId;
    }

    public void setRegisterRecommendUserId(String registerRecommendUserId) {
        this.registerRecommendUserId = registerRecommendUserId;
    }

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        return "UserBaseInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", channelId=" + channelId +
                ", channelUserId='" + channelUserId + '\'' +
                ", nickName='" + nickName + '\'' +
                ", realName='" + realName + '\'' +
                ", mobileAreaCode='" + mobileAreaCode + '\'' +
                ", mobile='" + mobile + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", headImg='" + headImg + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", userRole=" + userRole +
                ", source=" + source +
                ", registerRecommendUserId='" + registerRecommendUserId + '\'' +
                ", userStatus=" + userStatus +
                ", isDelete=" + isDelete +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                '}';
    }
}
