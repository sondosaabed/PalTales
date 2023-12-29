package com.paltales.model;

import android.provider.ContactsContract.CommonDataKinds.Email;

public class Account {
    private Email email;
    private Login login;

    public Account(Email email, Login login){
        setEmail(email);
        setLogin(login);
    }

    /*
    Getters & Setters
     */
    public Email getEmail() {
        return email;
    }
    public void setEmail(Email email) {
        this.email = email;
    }
    public Login getLogin() {
        return login;
    }
    public void setLogin(Login login) {
        this.login = login;
    }
}