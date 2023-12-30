package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;

import com.paltales.R;
import com.paltales.config.Preferences;
import com.paltales.model.Account;
import com.paltales.model.Login;
import com.paltales.utils.EncryptPassword;

import java.security.NoSuchAlgorithmException;

/*
    I created this activity to let the user create a new account and save it locally
 */
public class CreateAccountActivity extends AppCompatActivity {
    Button btnCreate;
    EditText password;
    EditText username;
    EditText email;
    CheckBox remmeber;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        initialize();
    }

    private void initialize() {
        setBtnCreate(findViewById(R.id.btnCreate));
        setPassword(findViewById(R.id.password));
        setUsername(findViewById(R.id.username));
        setEmail(findViewById(R.id.email));

        handle_create(getBtnCreate());
    }

    private void handle_create(Button btnCreate) {
        btnCreate.setOnClickListener(v->{
            try {
                /*
                Create password with encrypted password
                 */
                create_account(getUsername().getText().toString(),
                        EncryptPassword.encryptPassword(getPassword().getText().toString()),
                        getEmail().getText().toString().trim(),
                        getRemmeber().isChecked());

                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
    }

    private void create_account(String username, String password, String email, boolean remeber){
        Login login = new Login(username, password);
        Account account = new Account(getEmail().getText().toString(),login);

        Preferences.addAccount(account, this);

        if(remeber == true)
            Preferences.setRememberME(this);
    }

    private void handle_login(Button btnLogin) {
        btnLogin.setOnClickListener(v->{
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        });
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
    public CheckBox getRemmeber() {
        return remmeber;
    }
    public void setRemmeber(CheckBox remmeber) {
        this.remmeber = remmeber;
    }
}