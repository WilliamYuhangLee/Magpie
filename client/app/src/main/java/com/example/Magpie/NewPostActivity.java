package com.example.Magpie;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.Magpie.model.Post;
import com.example.Magpie.model.Response;
import com.example.Magpie.service.PostService;

import retrofit2.Call;
import retrofit2.Callback;

public class NewPostActivity extends AppCompatActivity {
    private static final String TAG = NewPostActivity.class.getName();

    private EditText newPostContent;
    private Button addPostBtn;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acitivity_new_post);

        Log.d(TAG, "Good here");

        newPostContent = findViewById(R.id.new_post_desc);
        addPostBtn = findViewById(R.id.new_post_btn);


        addPostBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = newPostContent.getText().toString();
                String username = new Session(NewPostActivity.this).getCurrentUsername();

                Post post = new Post().setContent(content);

                PostService postService = RetrofitInstance.getRetrofitInstance().create(PostService.class);

                Call<Response<Post>> call = postService.create(username, post);

                call.enqueue(new Callback<Response<Post>>() {
                    @Override
                    public void onResponse(Call<Response<Post>> call, retrofit2.Response<Response<Post>> response) {
                        String status = response.body().getStatus();
                        if (status.equals("CREATED")) {
                            Toast.makeText(NewPostActivity.this, "Add new post successfully", Toast.LENGTH_SHORT).show();
                            sendToHome();
                        } else {
                            if (response.body().getError() != null) {
                                Toast.makeText(NewPostActivity.this, response.body().getError().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(NewPostActivity.this, "Fail to add new post", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Post>> call, Throwable t) {
                        Toast.makeText(NewPostActivity.this, "Add new post failed", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

    }

    public void sendToHome(){
        Intent intent = new Intent(NewPostActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}