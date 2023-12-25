package com.paltales.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.paltales.R;

/*
     this is the Login activity, it will perform login functionality
 */
public class Login extends AppCompatActivity {
    /*
        Fields
    */
    private Button login;
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
        setEmail(findViewById(R.id.email));
        setPassword(findViewById(R.id.password));
        setAnswer(findViewById(R.id.txtResult));

        setEmailValid(findViewById(R.id.emailValidation));
        setPassValid(findViewById(R.id.passValidation));

        handle_login(getLogin());
    }

    /*
        Handlers
     */
    private void handle_login(Button login) {
        login.setOnClickListener(view -> {
            String enteredEmail = getEmail().getText().toString();
            String enteredPassword = getPassword().getText().toString();

            if (true) {
                // Authentication successful
                getEmailValid().setVisibility(View.GONE);
                getPassValid().setVisibility(View.GONE);
                getAnswer().setText("Successful!");
            } else {
                // Wrong authentication
                getEmailValid().setVisibility(View.VISIBLE);
                getPassValid().setVisibility(View.VISIBLE);
                getEmailValid().setText("Wrong Email");
                getAnswer().setText("Try Again!");
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
}