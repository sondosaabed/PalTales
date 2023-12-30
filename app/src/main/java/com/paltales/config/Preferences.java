package com.paltales.config;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.preference.PreferenceManager;
import com.google.gson.Gson;
import com.paltales.model.Account;
import com.paltales.model.Login;
import com.paltales.utils.EncryptPassword;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;

/*
    I have created this class to ne used as a Prefrences opearions manager
    I use it to get shared prefrences and save and load login information
    will also check if they wish to be remebered when they login again
 */
public class Preferences {
    /*
        Attriutes
     */
    private static final String DATA = "ACCOUNTS";
    private static final String FIRST_TIME = "IS_FIRST_TIME";
    private static final String REMEMBER = "REMEMBER_ME";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;

    /*
        I created these two methods in order to show diffrent activities when the user starts or gets back
     */
    public static boolean isFirstTime(Context context) {
        preferences = getPreferences(context);
        return preferences.getBoolean(FIRST_TIME, true);
    }

    public static void setNotFirstTime(Context context) {
        preferences = getPreferences(context);
        editor = preferences.edit();
        editor.putBoolean(FIRST_TIME, false);
        editor.apply();
    }

    public static boolean isRememberMe(Context context) {
        /*
            Setting the default value of rememebr me as false
         */
        preferences = getPreferences(context);
        return preferences.getBoolean(REMEMBER, false);
    }

    public static void setRememberME(Context context) {
        preferences = getPreferences(context);
        editor = preferences.edit();
        editor.putBoolean(REMEMBER, true);
        editor.apply();
    }

    public static ArrayList<Account> loadAccounts(Context context) throws NoSuchAlgorithmException {
        preferences = getPreferences(context);
        Gson gson = new Gson();
        String str = preferences.getString(DATA, "");
        Account[] logins = gson.fromJson(str, Account[].class);
        if(logins != null){
            return new ArrayList<>(Arrays.asList(logins));
        }else {
            Login ADMIN = new Login("Sondos",
                    EncryptPassword.encryptPassword("#$^*0"));
            Account account = new Account("s@duck.com", ADMIN);
            ArrayList<Account> accs =  new ArrayList<>();
            accs.add(account);
            return accs;
        }
    }

    /*
        I created this method to save the logins list which I called after each modification on the list
     */
    private static void saveAccounts(ArrayList<Account> accounts) {
        Gson gson = new Gson();
        String str = gson.toJson(accounts);
        editor.putString(DATA, str);
        editor.apply();
    }

    public static void addAccount(Account account, Context context) throws NoSuchAlgorithmException {
        /*
        Add account to DATA and save it
         */
        ArrayList<Account> accounts = loadAccounts(context);
        accounts.add(account);
        saveAccounts(accounts);
    }

    public static SharedPreferences getPreferences(Context context) {
        if (preferences == null) {
            preferences = PreferenceManager.getDefaultSharedPreferences(context);
            editor = preferences.edit();
        }
        return preferences;
    }
}