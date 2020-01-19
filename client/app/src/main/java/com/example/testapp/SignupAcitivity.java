package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignupAcitivity extends AppCompatActivity {
    private EditText signupEmail;
    private EditText signupPassword;
    private EditText signupConfirmPassword;
    private Button signupBtn;
    private Button signupLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        Button signupLoginBtn = findViewById(R.id.signup_login_btn);
        signupLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToLogin();
            }
        });

        Button signupBtn = findViewById(R.id.signup_btn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                trySignup();
            }
        });
    }

    private void sendToLogin() {
        Intent mainIntent = new Intent(SignupAcitivity.this, LoginActivity.class);
        startActivity(mainIntent);
        finish();

    }

    private void trySignup(){

    }
}
