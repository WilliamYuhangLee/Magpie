package com.example.Magpie.service;

import com.example.Magpie.model.PasswordChange;
import com.example.Magpie.model.Response;
import com.example.Magpie.model.User;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService {

    @POST("account/login")
    Call<Response<Void>> login(@Body User user);

    @POST("account/signUp")
    Call<Response<Void>> signUp(@Body User user);

    @PUT("account/changePassword")
    Call<Response<Void>> changePassword(@Body PasswordChange passwordChange);

}
