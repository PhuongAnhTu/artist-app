package com.example.artist.API;

import com.example.artist.Login.LoginModel;
import com.example.artist.Login.ResponseLogin;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIService {

    @Headers({"Content-Type: application/json", "Origin: https://thedarkmetal.com"})
    @POST("v1/auth/login")
    Call<APIResponse<ResponseLogin>> login(@Body LoginModel user);
}