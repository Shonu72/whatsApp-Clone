package com.coolblogger.tech.whatsapp.Models;

public class msgModels {
String uId, message;
Long timeStamp;

    public msgModels(String uId, String message, Long timeStamp) {
        this.uId = uId;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public msgModels(String uId, String message) {
        this.uId = uId;
        this.message = message;
    }

    public msgModels(){}

    public String getuId() {
        return uId;
    }

    public void setuId(String uId) {
        this.uId = uId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Long timeStamp) {
        this.timeStamp = timeStamp;
    }
}
