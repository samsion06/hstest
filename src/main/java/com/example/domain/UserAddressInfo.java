package com.example.domain;

public class UserAddressInfo {

    private Long Id;
    private Long User_Id;
    private Long Channel_Id;
    private String Channel_User_Id;
    private String Address_Id;
    private String Name;
    private String Mobile;
    private String Province;
    private String City;
    private String District;
    private String Street;
    private String Address;
    private Integer Address_Tag;
    private Integer Is_Default;
    private Integer Is_Delete;

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getUser_Id() {
        return User_Id;
    }

    public void setUser_Id(Long user_Id) {
        User_Id = user_Id;
    }

    public Long getChannel_Id() {
        return Channel_Id;
    }

    public void setChannel_Id(Long channel_Id) {
        Channel_Id = channel_Id;
    }

    public String getChannel_User_Id() {
        return Channel_User_Id;
    }

    public void setChannel_User_Id(String channel_User_Id) {
        Channel_User_Id = channel_User_Id;
    }

    public String getAddress_Id() {
        return Address_Id;
    }

    public void setAddress_Id(String address_Id) {
        Address_Id = address_Id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMobile() {
        return Mobile;
    }

    public void setMobile(String mobile) {
        Mobile = mobile;
    }

    public String getProvince() {
        return Province;
    }

    public void setProvince(String province) {
        Province = province;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getDistrict() {
        return District;
    }

    public void setDistrict(String district) {
        District = district;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public Integer getAddress_Tag() {
        return Address_Tag;
    }

    public void setAddress_Tag(Integer address_Tag) {
        Address_Tag = address_Tag;
    }

    public Integer getIs_Default() {
        return Is_Default;
    }

    public void setIs_Default(Integer is_Default) {
        Is_Default = is_Default;
    }

    public Integer getIs_Delete() {
        return Is_Delete;
    }

    public void setIs_Delete(Integer is_Delete) {
        Is_Delete = is_Delete;
    }

    @Override
    public String toString() {
        return "UserAddressInfo{" +
                "Id=" + Id +
                ", User_Id=" + User_Id +
                ", Channel_Id=" + Channel_Id +
                ", Channel_User_Id='" + Channel_User_Id + '\'' +
                ", Address_Id='" + Address_Id + '\'' +
                ", Name='" + Name + '\'' +
                ", Mobile='" + Mobile + '\'' +
                ", Province='" + Province + '\'' +
                ", City='" + City + '\'' +
                ", District='" + District + '\'' +
                ", Street='" + Street + '\'' +
                ", Address='" + Address + '\'' +
                ", Address_Tag=" + Address_Tag +
                ", Is_Default=" + Is_Default +
                ", Is_Delete=" + Is_Delete +
                '}';
    }
}
