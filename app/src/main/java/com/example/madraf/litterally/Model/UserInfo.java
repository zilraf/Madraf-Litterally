package com.example.madraf.litterally.Model;

public class UserInfo {

    private String phone, username, themeku;

    public UserInfo()
    {

    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getThemeku() {
        return themeku;
    }

    public void setThemeku(String themeku) {
        this.themeku = themeku;
    }

    public UserInfo(String phone, String username, String themeku){
        this.phone = phone;
        this.username = username;
        this.themeku = themeku;
    }

}
