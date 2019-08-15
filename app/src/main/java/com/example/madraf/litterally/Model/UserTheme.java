package com.example.madraf.litterally.Model;

public class UserTheme {
    private String email, themeku;

    public UserTheme()
    {

    }

    public UserTheme(String email, String themeku){
        this.email = email;
        this.themeku = themeku;
    }

    public String getEmail() { return email; }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getThemeku() {
        return themeku;
    }

    public void setThemeku(String themeku) {
        this.themeku = themeku;
    }

}
