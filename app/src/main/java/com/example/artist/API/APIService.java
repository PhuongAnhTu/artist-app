package com.example.artist.API;

import com.example.artist.listAll.AlbumListResponse;
import com.example.artist.listAll.ArtistListResponse;
import com.example.artist.login.LoginModel;
import com.example.artist.login.ResponseLogin;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @Headers({"Content-Type: application/json", "Origin: https://thedarkmetal.com"})
    @POST("v1/auth/login")
    Call<APIResponse<ResponseLogin>> login(@Body LoginModel user);



    @Headers({"Origin: https://thedarkmetal.com"})
    @GET("v1/artists")
    Call<APIResponse<ArtistListResponse>> loadArtist(@Header("Authorization") String token, @Query("skip") int skip, @Query("limit") int limit);

    //    @GET("v1/artists?skip=10&limit=10")

    @Headers({"Origin: https://thedarkmetal.com"})
    @GET("v1/albums")
    Call<APIResponse<AlbumListResponse>> loadAlbum(@Header("Authorization") String token, @Query("skip") int skip, @Query("limit") int limit);

}