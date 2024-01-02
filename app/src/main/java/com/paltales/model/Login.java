package com.paltales.model;

/*
This login Object class will be used in logins
 */
public class Login {
    private String userName;
    private String password;
    private boolean rememberMe;

    public Login(String userName, String password){
        // must pass it encrypted to this class EncryptPassword.encryptPassword(password
        setUserName(userName);
        setPassword(password);
        setRememberMe(false);
    }

    /* Getters & Setters */
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
    public boolean isRememberMe() {
        return rememberMe;
    }
    public void setRememberMe(boolean rememberMe) {
        this.rememberMe = rememberMe;
    }
}