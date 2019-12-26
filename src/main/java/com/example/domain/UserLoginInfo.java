package com.example.domain;

public class UserLoginInfo {

    private Long id;
    private Long user_id;
    private Long channel_id;
    private String  channel_user_id;
    private String login_name;
    private String login_pwd;
    private String login_salt;
    private String is_delete;

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

    public String getLogin_name() {
        return login_name;
    }

    public void setLogin_name(String login_name) {
        this.login_name = login_name;
    }

    public String getLogin_pwd() {
        return login_pwd;
    }

    public void setLogin_pwd(String login_pwd) {
        this.login_pwd = login_pwd;
    }

    public String getLogin_salt() {
        return login_salt;
    }

    public void setLogin_salt(String login_salt) {
        this.login_salt = login_salt;
    }

    public String getIs_delete() {
        return is_delete;
    }

    public void setIs_delete(String is_delete) {
        this.is_delete = is_delete;
    }

    @Override
    public String toString() {
        return "UserLoginInfo{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", channel_id=" + channel_id +
                ", channel_user_id='" + channel_user_id + '\'' +
                ", login_name='" + login_name + '\'' +
                ", login_pwd='" + login_pwd + '\'' +
                ", login_salt='" + login_salt + '\'' +
                ", is_delete='" + is_delete + '\'' +
                '}';
    }
}
