package com.example.Magpie.service;

import com.example.Magpie.model.User;

import retrofit2.Call;
import retrofit2.http.POST;

public interface UserService {

    @POST("account/login")
    Call<Void> login(User user);
}
