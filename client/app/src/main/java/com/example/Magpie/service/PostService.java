package com.example.Magpie.service;

import com.example.Magpie.model.Post;
import com.example.Magpie.model.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PostService {

    @POST("post/new")
    Call<Response<Post>> create(@Query("username") String username, @Body Post post);

    @GET("post/{postId}")
    Call<Response<Post>> read(@Path(value = "postId") long postId);

    @GET("post/")
    Call<Response<List<Post>>> getAllFrom(@Query("username") String username);

    @PUT("post/{postId}")
    Call<Response<Post>> update(@Path(value = "postId") long postId, @Body Post post);

    @DELETE("post/{postId}")
    Call<Response<Void>> delete(@Path(value = "postId") long postId);

}
