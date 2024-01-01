package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.paltales.R;
import com.paltales.config.Preferences;
import com.paltales.model.Login;
import com.paltales.utils.Authenticator;
import com.paltales.utils.EncryptPassword;

import java.security.NoSuchAlgorithmException;

/*
     this is the Login activity, it will perform login functionality
 */
public class LoginActivity extends AppCompatActivity {
    /*
        Fields
    */
    private Button login;
    private Button dontHave;
    private TextView email;
    private TextView password;
    private TextView answer; // I will use it to display the answer
    private TextView emailValid;
    private TextView passValid;
    private CheckBox remeberMe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        initialize();
    }

    private void initialize() {
        /*
            Getting the variables in the scene
         */
        setLogin(findViewById(R.id.btnLogin));
        setDontHave(findViewById(R.id.dontHave));

        setEmail(findViewById(R.id.email));
        setPassword(findViewById(R.id.password));
        setAnswer(findViewById(R.id.txtResult));

        setEmailValid(findViewById(R.id.emailValidation));
        setPassValid(findViewById(R.id.passValidation));

        setRemeberMe(findViewById(R.id.remeberMe));

        handle_remeberMe(getRemeberMe());
        handle_login(getLogin());
        handle_dont_have(getDontHave());
    }

    private void handle_dont_have(Button dontHave) {
        dontHave.setOnClickListener(e->{
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivity(intent);
        });
    }

    private void handle_remeberMe(CheckBox remeberMe) {
        boolean val = remeberMe.isChecked();
        if(val==true)
            Preferences.setRememberME(this);
    }

    /* Handlers  */
    private void handle_login(Button login) {
        login.setOnClickListener(view -> {
            String enteredEmail = getEmail().getText().toString();
            String enteredPassword = getPassword().getText().toString();
            try {
                Login enteredlogin = new Login(enteredEmail, EncryptPassword.encryptPassword(enteredPassword));

                boolean authorized = Authenticator.authorize(enteredlogin ,this);
                if (authorized) {
                    Intent intent = new Intent(this, Home.class);
                    startActivity(intent);
                } else {
                    // Wrong authentication
                    getEmailValid().setVisibility(View.VISIBLE);
                    getPassValid().setVisibility(View.VISIBLE);
                    getEmailValid().setText("Wrong Email");
                    getAnswer().setText("Try Again!");
                }
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        });
    }

    /*
        Getters and Setters
     */
    public Button getLogin() {
        return login;
    }
    public void setLogin(Button login) {
        this.login = login;
    }
    public TextView getEmail() {
        return email;
    }
    public void setEmail(TextView email) {
        this.email = email;
    }
    public TextView getPassword() {
        return password;
    }
    public void setPassword(TextView password) {
        this.password = password;
    }
    public TextView getAnswer() {
        return answer;
    }
    public void setAnswer(TextView answer) {
        this.answer = answer;
    }
    public TextView getEmailValid() {
        return emailValid;
    }
    public void setEmailValid(TextView emailValid) {
        this.emailValid = emailValid;
    }
    public TextView getPassValid() {
        return passValid;
    }
    public void setPassValid(TextView passValid) {
        this.passValid = passValid;
    }
    public Button getDontHave() {
        return dontHave;
    }
    public void setDontHave(Button dontHave) {
        this.dontHave = dontHave;
    }
    public CheckBox getRemeberMe() {
        return remeberMe;
    }
    public void setRemeberMe(CheckBox remeberMe) {
        this.remeberMe = remeberMe;
    }
}