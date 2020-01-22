package com.example.Magpie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Magpie.model.Post;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.MyViewHolder> {

    private List<Post> postList;

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //TODO
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull List<Object> payloads) {
        //TODO
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //TODO
    }

    @Override
    public int getItemCount() {
        //TODO
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView username, date, content;

        public MyViewHolder(View view) {
            super(view);
            username = (TextView) view.findViewById(R.id.blog_username);
            date = (TextView) view.findViewById(R.id.blog_date);
            content = (TextView) view.findViewById(R.id.blog_content);
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.blog_list_item, parent, false);

            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            Post post = postList.get(position);
            holder.username.setText(post.getUsername());
            holder.date.setText(post.getTimeModified().toString());
            holder.content.setText(post.getContent());
        }

        @Override
        public int getItemCount() {
            return postList.size();
        }
    }
}
