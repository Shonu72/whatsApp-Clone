package com.coolblogger.tech.whatsapp.Models;

public class users {
    String profileimg, userName, email, password, userId, lastmsg;

    public users(String profileimg, String userName, String email, String password, String userId, String lastmsg) {
        this.profileimg = profileimg;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.userId = userId;
        this.lastmsg = lastmsg;
    }

    public users(){}

    // Sign Up constructor
    public users(String userName, String email, String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProfileimg() {
        return profileimg;
    }

    public void setProfileimg(String profileimg) {
        this.profileimg = profileimg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }



    public String getLastmsg() {
        return lastmsg;
    }

    public void setLastmsg(String lastmsg) {
        this.lastmsg = lastmsg;
    }


}
