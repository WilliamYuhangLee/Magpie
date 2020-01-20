package com.example.Magpie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void onClickSignUp(View view) {
        Intent intent = new Intent(WelcomeActivity.this, SignupAcitivity.class);
        startActivity(intent);
    }

    public void onClickSignIn(View view) {
        Intent intent = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(intent);
    }

}
