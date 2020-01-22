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

public class SignupAcitivity extends AppCompatActivity {
    private EditText signupUsername;
    private EditText signupPassword;
    private EditText signupConfirmPassword;
    private Button signupBtn;
    private Button signupLoginBtn;

    private static final String TAG = SignupAcitivity.class.getName();
    Retrofit retrofit = RetrofitInstance.getRetrofitInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupUsername = findViewById(R.id.signup_email);
        signupPassword = findViewById(R.id.signup_password);
        signupConfirmPassword = findViewById(R.id.confirm_password);
        signupBtn = findViewById(R.id.signup_login_btn);
        signupLoginBtn = findViewById(R.id.signup_btn);

        signupLoginBtn = findViewById(R.id.signup_login_btn);
        signupLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendToLogin();
            }
        });

        signupBtn = findViewById(R.id.signup_btn);
        signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String username = signupUsername.getText().toString();
                String password = signupPassword.getText().toString();
                String confirmPassword = signupConfirmPassword.getText().toString();

                User user = new User().setUsername(username).setPassword(password);

                if(!TextUtils.isEmpty(username) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(confirmPassword) && password.equals(confirmPassword)) {
                    UserService userService = retrofit.create(UserService.class);
                    Call<Response<Void>> call = userService.signUp(user);
                    call.enqueue(new Callback<Response<Void>>() {
                        @Override
                        public void onResponse(Call<Response<Void>> call, retrofit2.Response<Response<Void>> response) {
                            String status = response.body().getStatus();
                            if (status.equals("NO_CONTENT")) {
                                Toast.makeText(SignupAcitivity.this, "Sign up successfully", Toast.LENGTH_SHORT).show();
                                new Session(SignupAcitivity.this).login(username);
                                sendToHome();
                            } else {
                                if (response.body().getError() != null) {
                                    Toast.makeText(SignupAcitivity.this, response.body().getError().getMessage(), Toast.LENGTH_SHORT).show();
                                } else {
                                    Toast.makeText(SignupAcitivity.this, "Unknown error", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<Response<Void>> call, Throwable t) {
                            Log.d(TAG, t.getMessage());
                            Toast.makeText(SignupAcitivity.this, "Register failed!", Toast.LENGTH_SHORT).show();
                        }
                    });
                } else{
                    Toast.makeText(SignupAcitivity.this, "Please check your entries and try again", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void sendToHome() {
        Intent mainIntent = new Intent(SignupAcitivity.this, HomeActivity.class);
        startActivity(mainIntent);
        finish();

    }

    private void sendToLogin() {
        Intent mainIntent = new Intent(SignupAcitivity.this, LoginActivity.class);
        startActivity(mainIntent);
        finish();

    }

}
