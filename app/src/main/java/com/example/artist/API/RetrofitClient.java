package com.example.artist.API;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public final class RetrofitClient {
    private static APIService mAPIService;

    public synchronized static APIService createClient() {
        if (null == mAPIService) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
                    .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                    .build();

            mAPIService = new Retrofit.Builder()
                    .baseUrl("https://thedarkmetal.com:2208/") // API base url
                    .addConverterFactory(GsonConverterFactory.create()) // Factory phụ thuộc vào format trả về
                    .client(client)
                    .build()
                    .create(APIService.class);
        }
        return mAPIService;
    }
}
