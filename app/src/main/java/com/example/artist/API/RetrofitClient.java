package com.example.artist.API;

import com.example.artist.API.APIService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitClient {
    private static APIService mAPIService;

    public synchronized static APIService createClient() {
        if (null == mAPIService) {
            mAPIService = new Retrofit.Builder()
                    .baseUrl("https://thedarkmetal.com:2208/") // API base url
                    .addConverterFactory(GsonConverterFactory.create()) // Factory phụ thuộc vào format trả về
                    .build()
                    .create(APIService.class);
        }
        return mAPIService;
    }


}
