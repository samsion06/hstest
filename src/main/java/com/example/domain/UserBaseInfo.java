package com.example.domain;

public class UserBaseInfo {

    private Long id;
    private Long user_id;
    private Long channel_id;
    private String  channel_user_id;
    private String nick_name;
    private String real_name;
    private String mobile_area_code;
    private Integer sex;
    private String  birthday;
    private String head_img;
    private String identity_card;
    private Integer user_role;
    private Integer source;
    private String register_recommend_user_id;
    private Integer user_status;
    private Integer is_delete;

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

    public Integer getUser_role() {
        return user_role;
    }

    public void setUser_role(Integer user_role) {
        this.user_role = user_role;
    }

    public Integer getSource() {
        return source;
    }

    public void setSource(Integer source) {
        this.source = source;
    }

    public String getRegister_recommend_user_id() {
        return register_recommend_user_id;
    }

    public void setRegister_recommend_user_id(String register_recommend_user_id) {
        this.register_recommend_user_id = register_recommend_user_id;
    }

    public Integer getUser_status() {
        return user_status;
    }

    public void setUser_status(Integer user_status) {
        this.user_status = user_status;
    }

    public Integer getIs_delete() { return is_delete; }

    public void setIs_delete(Integer is_delete) { this.is_delete = is_delete; }

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

    public String getChannel_user_id() {
        return channel_user_id;
    }

    public void setChannel_user_id(String channel_user_id) {
        this.channel_user_id = channel_user_id;
    }

    public Long getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(Long channel_id) {
        this.channel_id = channel_id;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getReal_name() {
        return real_name;
    }

    public void setReal_name(String real_name) {
        this.real_name = real_name;
    }

    public String getMobile_area_code() {
        return mobile_area_code;
    }

    public void setMobile_area_code(String mobile_area_code) {
        this.mobile_area_code = mobile_area_code;
    }

    @Override
    public String toString() {
        return "UserBaseInfo{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", channel_id=" + channel_id +
                ", channel_user_id='" + channel_user_id + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", real_name='" + real_name + '\'' +
                ", mobile_area_code='" + mobile_area_code + '\'' +
                ", sex=" + sex +
                ", birthday='" + birthday + '\'' +
                ", head_img='" + head_img + '\'' +
                ", identity_card='" + identity_card + '\'' +
                ", user_role=" + user_role +
                ", source=" + source +
                ", register_recommend_user_id='" + register_recommend_user_id + '\'' +
                ", user_status=" + user_status +
                ", is_delete=" + is_delete +
                '}';
    }
}
