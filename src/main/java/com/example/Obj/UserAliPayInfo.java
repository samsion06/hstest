package com.example.Obj;

public class UserAliPayInfo {

    private Long id;
    private Long user_id;
    private Long channel_id;
    private String  channel_user_id;
    private String alipay_user_id;
    private String alipay_realname;
    private String alipay_account;
    private String nick_name;
    private String head_img;
    private String identity_card;
    private Integer sex;
    private String province;
    private String city;
    private Integer status;
    private Integer is_certified;
    private Integer is_delete;
    private String is_student_certified;
    private String user_type;
    private String user_status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Long channel_id) {
        this.channel_id = channel_id;
    }

    public String getChannel_user_id() {
        return channel_user_id;
    }

    public void setChannel_user_id(String channel_user_id) {
        this.channel_user_id = channel_user_id;
    }

    public String getAlipay_user_id() {
        return alipay_user_id;
    }

    public void setAlipay_user_id(String alipay_user_id) {
        this.alipay_user_id = alipay_user_id;
    }

    public String getAlipay_realname() {
        return alipay_realname;
    }

    public void setAlipay_realname(String alipay_realname) {
        this.alipay_realname = alipay_realname;
    }

    public String getAlipay_account() {
        return alipay_account;
    }

    public void setAlipay_account(String alipay_account) {
        this.alipay_account = alipay_account;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public String getIdentity_card() {
        return identity_card;
    }

    public void setIdentity_card(String identity_card) {
        this.identity_card = identity_card;
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

    public Integer getIs_certified() {
        return is_certified;
    }

    public void setIs_certified(Integer is_certified) {
        this.is_certified = is_certified;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    public String getIs_student_certified() {
        return is_student_certified;
    }

    public void setIs_student_certified(String is_student_certified) {
        this.is_student_certified = is_student_certified;
    }

    public String getUser_type() {
        return user_type;
    }

    public void setUser_type(String user_type) {
        this.user_type = user_type;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    @Override
    public String toString() {
        return "UserAliPayInfo{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", channel_id=" + channel_id +
                ", channel_user_id='" + channel_user_id + '\'' +
                ", alipay_user_id='" + alipay_user_id + '\'' +
                ", alipay_realname='" + alipay_realname + '\'' +
                ", alipay_account='" + alipay_account + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", head_img='" + head_img + '\'' +
                ", identity_card='" + identity_card + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", status=" + status +
                ", is_certified=" + is_certified +
                ", is_delete=" + is_delete +
                ", is_student_certified='" + is_student_certified + '\'' +
                ", user_type='" + user_type + '\'' +
                ", user_status='" + user_status + '\'' +
                '}';
    }
}
