package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;

import com.paltales.R;
import com.paltales.config.Preferences;
import com.paltales.model.Account;
import com.paltales.model.Login;
import com.paltales.utils.EncryptPassword;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

/*
    I created this activity to let the user create a new account and save it locally
 */
public class CreateAccountActivity extends AppCompatActivity {
    private Button btnCreate;
    private Button already;
    private EditText password;
    private EditText username;
    private EditText email;
    private TextView txtResult;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        initialize();
    }

    private void initialize() {
        setBtnCreate(findViewById(R.id.btnCreate));
        setAlready(findViewById(R.id.alreadyHave));
        setPassword(findViewById(R.id.password));
        setUsername(findViewById(R.id.username));
        setEmail(findViewById(R.id.email));
        setTxtResult(findViewById(R.id.txtResult));

        handle_create(getBtnCreate());
        handle_already(getAlready());
    }

    private void handle_already(Button already) {
        already.setOnClickListener(e->{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void handle_create(Button btnCreate) {
        btnCreate.setOnClickListener(v->{
            try {
                /*
                Create account with encrypted password
                but make sure that the username is not in the accounts already
                 */
                    if(!create_account(getUsername().getText().toString(),
                            EncryptPassword.encryptPassword(getPassword().getText().toString()),
                            getEmail().getText().toString().trim()))
                        getTxtResult().setText(R.string.wronguse);
                    else {
                        Intent intent = new Intent(this, LoginActivity.class);
                        startActivity(intent);
                    }
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
        });
    }

    private boolean create_account(String username, String password, String email) throws NoSuchAlgorithmException {
        Login login = new Login(username, password);
        Account account = new Account(email,login);

        ArrayList<Account> accounts = Preferences.loadAccounts(this);
        for(Account acc: accounts){
            if(acc.getLogin().getUserName().equals(username))
                return false;
        }
        Preferences.addAccount(account, this);
        return true;
    }

    /*
    Getters & Setters
     */
    public Button getBtnCreate() {
        return btnCreate;
    }
    public void setBtnCreate(Button btnCreate) {
        this.btnCreate = btnCreate;
    }
    public EditText getPassword() {
        return password;
    }
    public void setPassword(EditText password) {
        this.password = password;
    }
    public EditText getUsername() {
        return username;
    }
    public void setUsername(EditText username) {
        this.username = username;
    }
    public EditText getEmail() {
        return email;
    }
    public void setEmail(EditText email) {
        this.email = email;
    }
    public Button getAlready() {
        return already;
    }
    public void setAlready(Button already) {
        this.already = already;
    }
    public TextView getTxtResult() {
        return txtResult;
    }
    public void setTxtResult(TextView txtResult) {
        this.txtResult = txtResult;
    }
}