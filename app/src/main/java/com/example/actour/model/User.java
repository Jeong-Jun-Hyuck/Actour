package com.example.actour.model;

public class User {
    private String name;
    private String id;
    private String password;
    private String email;
    private String nation;
    private String nickname;
    private int age;
    private boolean sex;


    public User(String name, String id, String password, String email, String nation, int age,
                String nickname, boolean sex) {
        super();
        this.name = name;
        this.id = id;
        this.password = password;
        this.email = email;
        this.nation = nation;
        this.age = age;
        this.nickname = nickname;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getNation() {
        return nation;
    }
    public void setNation(String nation) {
        this.nation = nation;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public boolean isSex() {
        return sex;
    }
    public void setSex(boolean sex) {
        this.sex = sex;
    }
}
