package com.example.artist.API;

import com.example.artist.detailScreen.ArtistDetailResponse;
import com.example.artist.listAll.AlbumListResponse;
import com.example.artist.listAll.ArtistListResponse;
import com.example.artist.login.LoginModel;
import com.example.artist.login.ResponseLogin;
import com.example.artist.detailScreen.AlbumDetailResponse;

import retrofit2.Call;
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

    @Headers({"Origin: https://thedarkmetal.com"})
    @GET("v1/albums")
    Call<APIResponse<AlbumListResponse>> loadAlbum(@Header("Authorization") String token, @Query("skip") int skip, @Query("limit") int limit);

    @Headers({"Origin: https://thedarkmetal.com"})
    @GET("v1/artists/{id}")
    Call<APIResponse<ArtistDetailResponse>> loadDetailArtist(@Header("Authorization") String token, @Path("id") String _id);

    @Headers({"Origin: https://thedarkmetal.com"})
    @GET("v1/albums/{id}")
    Call<APIResponse<AlbumDetailResponse>> loadDetailAlbum(@Header("Authorization") String token, @Path("id") String _id);

    @Headers({"Origin: https://thedarkmetal.com"})
    @GET("v1/p/albums/{code}")
    Call<APIResponse<AlbumDetailResponse>> loadSongOfAlbum(@Header("Authorization") String token, @Path("code") String code);



}