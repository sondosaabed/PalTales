package com.paltales.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.paltales.R;
import com.paltales.model.Account;
import com.paltales.model.Login;
import com.paltales.utils.Auth;
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
    private Button create;
    private TextView email;
    private TextView password;
    private TextView answer; // I will use it to display the answer
    private TextView emailValid;
    private TextView passValid;

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
        setCreate(findViewById(R.id.btnCreate));
        setEmail(findViewById(R.id.email));
        setPassword(findViewById(R.id.password));
        setAnswer(findViewById(R.id.txtResult));

        setEmailValid(findViewById(R.id.emailValidation));
        setPassValid(findViewById(R.id.passValidation));

        handle_login(getLogin());
        handle_create(getCreate());
    }

    /*
        Handlers
     */
    private void handle_login(Button login) {
        login.setOnClickListener(view -> {
            String enteredEmail = getEmail().getText().toString();
            String enteredPassword = getPassword().getText().toString();
            Login enteredlogin = new Login(enteredEmail, enteredPassword);

            boolean autherized = Auth.authorize(enteredlogin ,this);

            if (autherized) {
                // Authentication successful
                getEmailValid().setVisibility(View.GONE);
                getPassValid().setVisibility(View.GONE);

                Intent intent = new Intent(this, Home.class);
                startActivity(intent);
            } else {
                // Wrong authentication
                getEmailValid().setVisibility(View.VISIBLE);
                getPassValid().setVisibility(View.VISIBLE);
                getEmailValid().setText("Wrong Email");
                getAnswer().setText("Try Again!");
            }
        });
    }
    private void handle_create(Button create) {
        create.setOnClickListener(v->{
            Intent intent = new Intent(this, CreateAccountActivity.class);
            startActivity(intent);
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
    public Button getCreate() {
        return create;
    }
    public void setCreate(Button create) {
        this.create = create;
    }
}