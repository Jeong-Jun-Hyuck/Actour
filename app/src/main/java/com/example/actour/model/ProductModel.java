package com.example.actour.model;

public class ProductModel {

    private int id;
    private String title;
    private String date;
    private String nation;
    private double totlaTime;
    private int money;
    private int ownerId;

    public ProductModel(int id,String title, String date, String nation, double totlaTime, int money, int ownerId) {
        this.id =id;
        this.title = title;
        this.date = date;
        this.nation = nation;
        this.totlaTime = totlaTime;
        this.money = money;
        this.ownerId = ownerId;
    }

    public int getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(int ownerId) {
        this.ownerId = ownerId;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public double getTotlaTime() {
        return totlaTime;
    }

    public void setTotlaTime(double totlaTime) {
        this.totlaTime = totlaTime;
    }
}
