package com.example.Magpie;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Magpie.model.Post;
import com.example.Magpie.model.Response;
import com.example.Magpie.service.PostService;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;

public class HomeFragment extends Fragment {

    static final String TAG = HomeFragment.class.getSimpleName();

    private static HomeFragment fragment;

    public static HomeFragment getInstance() {
        if (fragment == null) {
            fragment = new HomeFragment();
        }
        return fragment;
    }

    private List<Post> posts = new ArrayList<>();

    private RecyclerView recyclerView;
    private RecyclerViewAdapter recyclerViewAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        recyclerView = getView().findViewById(R.id.blog_list_view);
        recyclerViewAdapter = new RecyclerViewAdapter();
        recyclerView.setAdapter(recyclerViewAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        PostService postService = RetrofitInstance.getRetrofitInstance().create(PostService.class);
        String username = new Session(getActivity()).getCurrentUsername();
        Call<Response<List<Post>>> call = postService.getAllFrom(username);
        call.enqueue(new Callback<Response<List<Post>>>() {
            @Override
            public void onResponse(Call<Response<List<Post>>> call, retrofit2.Response<Response<List<Post>>> response) {
                if (response.body() != null && response.body().getPayload() != null) {
                    posts.clear();
                    posts.addAll(response.body().getPayload());
                    Log.d(TAG, posts.toString());
                    recyclerViewAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onFailure(Call<Response<List<Post>>> call, Throwable t) {
                Toast.makeText(getActivity(), "Unable to retrieve posts!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_post, parent, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            Post post = posts.get(position);
            holder.username.setText("Created by " + post.getUsername());
            holder.timeModified.setText("Last Modified: " + post.getTimeModified().toString());
            holder.content.setText(post.getContent());
        }

        @Override
        public int getItemCount() {
            return posts.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            TextView username, timeModified, content;

            ViewHolder(View itemView) {
                super(itemView);
                username = (TextView) itemView.findViewById(R.id.post_username);
                timeModified = (TextView) itemView.findViewById(R.id.post_time_modified);
                content = (TextView) itemView.findViewById(R.id.post_content);
            }
        }
    }
}
