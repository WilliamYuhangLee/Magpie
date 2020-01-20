package com.example.Magpie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BlogRecyclerAdapter extends RecyclerView.Adapter {
    public List<BlogPost> blog_list;
    public Context context;

    public BlogRecyclerAdapter(List<BlogPost> blog_list){

        this.blog_list = blog_list;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.blog_list_item, parent, false);
        context = parent.getContext();
        return new RecyclerView.ViewHolder() {
            @Override
            public String toString() {
                return super.toString();
            }
        };
    }
}
