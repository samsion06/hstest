package com.example.Obj;

public class User_info {
    private  Integer id;
    private  String username;
    private  String password;
    private  Integer is_delete;

    public Integer getIs_delete() { return is_delete; }

    public void setIs_delete(Integer is_delete) { this.is_delete = is_delete; }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User_info{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", is_delete=" + is_delete +
                '}';
    }

}
