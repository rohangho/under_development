package com.example.rohan.myapplication;

public class MessageModel {

    String message;
    String authur;

    MessageModel()
    {

    }


    MessageModel(String message, String authur)
    {
        this.message=message;
        this.authur=authur;
    }

    public String getMessage() {
        return message;
    }

    public String getAuthur() {
        return authur;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setAuthur(String authur) {
        this.authur = authur;
    }
}
