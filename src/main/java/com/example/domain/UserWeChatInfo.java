package com.example.domain;

public class UserWeChatInfo {

    private Long id;
    private Long user_id;
    private Long channel_id;
    private String  channel_user_id;
    private String open_id;
    private String union_id;
    private String wx_no;
    private String nick_name;
    private Integer sex;
    private String province;
    private String city;
    private String country;
    private String head_img;
    private Integer is_delete;


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

    public String getOpen_id() {
        return open_id;
    }

    public void setOpen_id(String open_id) {
        this.open_id = open_id;
    }

    public String getUnion_id() {
        return union_id;
    }

    public void setUnion_id(String union_id) {
        this.union_id = union_id;
    }

    public String getWx_no() {
        return wx_no;
    }

    public void setWx_no(String wx_no) {
        this.wx_no = wx_no;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHead_img() {
        return head_img;
    }

    public void setHead_img(String head_img) {
        this.head_img = head_img;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    @Override
    public String toString() {
        return "UserAlipayAuthInfo{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", channel_id=" + channel_id +
                ", channel_user_id='" + channel_user_id + '\'' +
                ", open_id='" + open_id + '\'' +
                ", union_id='" + union_id + '\'' +
                ", wx_no='" + wx_no + '\'' +
                ", nick_name='" + nick_name + '\'' +
                ", sex=" + sex +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                ", head_img='" + head_img + '\'' +
                ", is_delete='" + is_delete + '\'' +
                '}';
    }
}
