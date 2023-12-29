package com.paltales.model;

import android.provider.ContactsContract.CommonDataKinds.Email;

public class Account {
    private String email;// string type but validted as email
    private Login login;

    public Account(String email, Login login){
        setEmail(email);
        setLogin(login);
    }

    /*
    Getters & Setters
     */
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public Login getLogin() {
        return login;
    }
    public void setLogin(Login login) {
        this.login = login;
    }
}