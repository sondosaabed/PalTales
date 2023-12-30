package com.paltales.utils;

import android.content.Context;

import com.paltales.config.Preferences;
import com.paltales.model.Account;
import com.paltales.model.Login;

import java.util.ArrayList;

public class Authenticator {
    /*
        This util class goes through list of saved accounts
        in prefrences and if the passed login equals one of the acc
        returns true and proceed to home activity.
     */
    public static boolean authorize(Login login, Context context){
        ArrayList<Account> accounts = Preferences.loadAccounts(context);
        for(Account acc: accounts){
            if(acc.getLogin().getPassword().equals(login.getPassword())
            && acc.getLogin().getUserName().equals(login.getUserName()))
                return true;
        }
        return false;
    }
}
