package com.paltales.model;

import com.paltales.utils.EncryptPassword;

import java.security.NoSuchAlgorithmException;

/*
This login Object class will be used in logins
 */
public class Login {
    private String userName;
    private String password;

    public Login(String userName, String password){
        // must pass it encrypted to this class EncryptPassword.encryptPassword(password
        setUserName(userName);
        setPassword(password);
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
}