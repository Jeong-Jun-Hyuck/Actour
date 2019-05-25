package com.example.actour.model;

public class LoginSession {
    static private int loginId;

    public static int getLoginId() {
        return loginId;
    }

    public static void setLoginId(int id) {
        loginId = id;
    }
}
