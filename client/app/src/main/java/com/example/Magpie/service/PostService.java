package com.example.Magpie.service;

import com.example.Magpie.model.Response;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface PostService {

    @GET("post/{postId}")
    Call<Response<POST>> get(@Path(value = "postId") long postId);
}
