package com.paltales.model;
/*
This login Object class will be used in logins
 */
public class Login {
    private String userName;
    private String password;

    public Login(String userName, String password){

    }
    /*
        Getters & Setters
     */
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
}