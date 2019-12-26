package com.example.domain;

import java.util.Date;

public class UserAliPayInfo {

    private Long id;
    private Long userId;
    private Long channelId;
    private String channelUserId;
    private String alipayUserId;
    private String alipayRealName;
    private String alipayAccount;
    private String nickName;
    private String headImg;
    private String identityCard;
    private Integer sex;
    private String province;
    private String city;
    private Integer status;
    private Integer isCertified;
    private Integer isDelete;
    private String isStudentCertified;
    private String userType;
    private String userStatus;
    private Date creatTime;
    private Date updateTime;

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

    public String getAlipayUserId() {
        return alipayUserId;
    }

    public void setAlipayUserId(String alipayUserId) {
        this.alipayUserId = alipayUserId;
    }

    public String getAlipayRealName() {
        return alipayRealName;
    }

    public void setAlipayRealName(String alipayRealName) {
        this.alipayRealName = alipayRealName;
    }

    public String getAlipayAccount() {
        return alipayAccount;
    }

    public void setAlipayAccount(String alipayAccount) {
        this.alipayAccount = alipayAccount;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsCertified() {
        return isCertified;
    }

    public void setIsCertified(Integer isCertified) {
        this.isCertified = isCertified;
    }

    public Integer getIsDelete() {
        return isDelete;
    }

    public void setIsDelete(Integer isDelete) {
        this.isDelete = isDelete;
    }

    public String getIsStudentCertified() {
        return isStudentCertified;
    }

    public void setIsStudentCertified(String isStudentCertified) {
        this.isStudentCertified = isStudentCertified;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @Override
    public String toString() {
        return "UserAliPayInfo{" +
                "id=" + id +
                ", userId=" + userId +
                ", channelId=" + channelId +
                ", channelUserId='" + channelUserId + '\'' +
                ", alipayUserId='" + alipayUserId + '\'' +
                ", alipayRealName='" + alipayRealName + '\'' +
                ", alipayAccount='" + alipayAccount + '\'' +
                ", nickName='" + nickName + '\'' +
                ", headImg='" + headImg + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", status=" + status +
                ", isCertified=" + isCertified +
                ", isDelete=" + isDelete +
                ", isStudentCertified='" + isStudentCertified + '\'' +
                ", userType='" + userType + '\'' +
                ", userStatus='" + userStatus + '\'' +
                ", creatTime=" + creatTime +
                ", updateTime=" + updateTime +
                '}';
    }



}
