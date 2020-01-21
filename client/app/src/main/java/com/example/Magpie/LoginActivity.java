package com.example.Magpie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Magpie.model.User;
import com.example.Magpie.service.UserService;

import retrofit2.Call;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUsername;
    private EditText loginPassword;
    private Button loginBtn;
    private Button loginSignupBtn;
    Retrofit retrofit = RetrofitInstance.getRetrofitInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.login_email);
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

                String username = loginUsername.getText().toString();
                String password = loginPassword.getText().toString();

                User user = new User().setUsername(username).setPassword(password);

                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password)){
                    UserService userService = retrofit.create(UserService.class);
                    Call<Void> call = userService.login(user);
                    // get status code
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
