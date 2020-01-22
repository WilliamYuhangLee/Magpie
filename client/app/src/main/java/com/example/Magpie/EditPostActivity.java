package com.example.Magpie;

import android.content.Intent;
import android.os.Bundle;
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

public class EditPostActivity extends AppCompatActivity {
    private static final String TAG = EditPostActivity.class.getName();

    private EditText postContent;
    private Button editBtn;
    private Button deleteBtn;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_post);

        postContent = findViewById(R.id.edit_content);
        editBtn = findViewById(R.id.edit_post_btn);
        deleteBtn = findViewById(R.id.edit_del_btn);

        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = postContent.getText().toString();
                Post post = new Post().setContent(content);

                long postId = getIntent().getLongExtra("postId", 1);

                PostService postService = RetrofitInstance.getRetrofitInstance().create(PostService.class);

                Call<Response<Post>> call = postService.update(postId, post);

                call.enqueue(new Callback<Response<Post>>() {
                    @Override
                    public void onResponse(Call<Response<Post>> call, retrofit2.Response<Response<Post>> response) {
                        String status = response.body().getStatus();
                        if (status.equals("OK")) {
                            Toast.makeText(EditPostActivity.this, "Modified Successfully", Toast.LENGTH_SHORT).show();
                            sendToHome();
                        } else {
                            if (response.body().getError() != null) {
                                Toast.makeText(EditPostActivity.this, response.body().getError().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EditPostActivity.this, "Fail to modify post", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Post>> call, Throwable t) {
                        Toast.makeText(EditPostActivity.this, "Modified Failed!", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long postId = getIntent().getLongExtra("postId", 1);
                Post post = new Post().setContent("delete");

                PostService postService = RetrofitInstance.getRetrofitInstance().create(PostService.class);
//                Call<Response<Post>> call = postService.delete(postId);

                Call<Response<Void>> call = postService.delete(postId);
                call.enqueue(new Callback<Response<Void>>() {
                    @Override
                    public void onResponse(Call<Response<Void>> call, retrofit2.Response<Response<Void>> response) {
                        String status = response.body().getStatus();
                        if (status.equals("NO_CONTENT")) {
                            Toast.makeText(EditPostActivity.this, "Delete Successfully", Toast.LENGTH_SHORT).show();
                            sendToHome();
                        } else {
                            if (response.body().getError() != null) {
                                Toast.makeText(EditPostActivity.this, response.body().getError().getMessage(), Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(EditPostActivity.this, "Fail to delete post", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<Response<Void>> call, Throwable t) {
                        Toast.makeText(EditPostActivity.this, "Deleted Failed!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });



    }

    public void sendToHome(){
        Intent intent = new Intent(EditPostActivity.this, HomeActivity.class);
        startActivity(intent);
        finish();
    }
}
