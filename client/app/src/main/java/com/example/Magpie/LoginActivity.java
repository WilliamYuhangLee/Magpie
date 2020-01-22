package com.example.Magpie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.Magpie.model.Response;
import com.example.Magpie.model.User;
import com.example.Magpie.service.UserService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getName();

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
                    Call<Response<Void>> call = userService.login(user);
                    call.enqueue(new Callback<Response<Void>>() {
                        @Override
                        public void onResponse(Call<Response<Void>> call, retrofit2.Response<Response<Void>> response) {
                            String status = response.body().getStatus();
                            if (status.equals("NO_CONTENT")) {
                                sendToHome();
                            } else {
                                if (response.body().getError() != null) {
                                    Toast.makeText(LoginActivity.this, response.body().getError().getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(LoginActivity.this, "Wrong credentials!", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Response<Void>> call, Throwable t) {
                            Log.d(TAG, t.getMessage());
                            Toast.makeText(LoginActivity.this, "Login failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else{
                    Toast.makeText(LoginActivity.this, "Error : Plz enter email or password" , Toast.LENGTH_LONG).show();
                }

            }

        });

    }

    private void sendToHome() {

        Intent mainIntent = new Intent(LoginActivity.this, HomeActivity.class);
        startActivity(mainIntent);
        finish();

    }
}
