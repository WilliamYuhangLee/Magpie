package com.example.Magpie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText loginEmail;
    private EditText loginPassword;
    private Button loginBtn;
    private Button loginSignupBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.login_email);
        loginPassword = findViewById(R.id.login_password);
        loginBtn = findViewById(R.id.login_btn);
        loginSignupBtn = findViewById(R.id.login_signup_btn);

        loginSignupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent regIntent = new Intent(LoginActivity.this, SignupAcitivity.class);
                startActivity(regIntent);

            }
        });

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = loginEmail.getText().toString();
                String pass = loginPassword.getText().toString();

                if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(pass)){
                    sendToMain();
                } else{
                    Toast.makeText(LoginActivity.this, "Error : Plz enter email or password" , Toast.LENGTH_LONG).show();

                }


            }

        });

    }

    private void sendToMain() {

        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(mainIntent);
        finish();

    }
}
