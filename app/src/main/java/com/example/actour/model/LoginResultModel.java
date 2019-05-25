package com.example.actour.model;

public class LoginResultModel {

    private boolean result;
    private  String message;
    private  String id;
    private int systemId;

    public LoginResultModel(boolean result, String message, String id, int systemId) {
        this.result = result;
        this.message = message;
        this.id = id;
        this.systemId = systemId;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getSystemId() {
        return systemId;
    }

    public void setSystemId(int systemId) {
        this.systemId = systemId;
    }
}
