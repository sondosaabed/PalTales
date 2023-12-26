package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.paltales.R;

public class CreateAccount extends AppCompatActivity {
    Button btnLogin;
    Button btnCreate;
    EditText password;
    EditText username;
    EditText email;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);
        initialize();
    }

    private void initialize() {
        setBtnLogin(findViewById(R.id.btnLogin));
        setBtnCreate(findViewById(R.id.btnCreate));
        setPassword(findViewById(R.id.password));
        setUsername(findViewById(R.id.username));
        setEmail(findViewById(R.id.email));

        handle_login(getBtnLogin());
        handle_create(getBtnCreate());
    }

    private void handle_create(Button btnCreate) {
        btnCreate.setOnClickListener(v->{

        });
    }

    private void handle_login(Button btnLogin) {
        btnLogin.setOnClickListener(v->{
            Intent intent = new Intent(this, Login.class);
            startActivity(intent);
        });
    }

    /*
    Getters & Setters
     */
    public Button getBtnLogin() {
        return btnLogin;
    }
    public void setBtnLogin(Button btnLogin) {
        this.btnLogin = btnLogin;
    }
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
}