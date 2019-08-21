package com.example.madraf.litterally.Model;

public class UTheme {

    private String username, themeku;

    public UTheme()
    {

    }

    public UTheme(String themeku,String username){
        this.themeku = themeku;
        this.username = username;
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
}
