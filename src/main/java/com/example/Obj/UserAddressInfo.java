package com.example.Obj;

public class UserAddressInfo {

    private Long id;
    private Long user_id;
    private Long channel_id;
    private String  channel_user_id;
    private String address_id;
    private String name;
    private String mobile;
    private String province;
    private String city;
    private String district;
    private String street;
    private String address;
    private Integer address_tag;
    private Integer is_default;
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

    public String getAddress_id() {
        return address_id;
    }

    public void setAddress_id(String address_id) {
        this.address_id = address_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getAddress_tag() {
        return address_tag;
    }

    public void setAddress_tag(Integer address_tag) {
        this.address_tag = address_tag;
    }

    public Integer getIs_default() {
        return is_default;
    }

    public void setIs_default(Integer is_default) {
        this.is_default = is_default;
    }

    public Integer getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(Integer is_delete) {
        this.is_delete = is_delete;
    }

    @Override
    public String toString() {
        return "UserAddressInfo{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", channel_id=" + channel_id +
                ", channel_user_id='" + channel_user_id + '\'' +
                ", address_id='" + address_id + '\'' +
                ", name='" + name + '\'' +
                ", mobile='" + mobile + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", street='" + street + '\'' +
                ", address='" + address + '\'' +
                ", address_tag=" + address_tag +
                ", is_default=" + is_default +
                ", is_delete=" + is_delete +
                '}';
    }
}
