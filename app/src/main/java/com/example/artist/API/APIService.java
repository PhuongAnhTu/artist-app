package com.example.artist.API;

import com.example.artist.listAll.AlbumListResponse;
import com.example.artist.listAll.ArtistListResponse;
import com.example.artist.login.LoginModel;
import com.example.artist.login.ResponseLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIService {

    @Headers({"Content-Type: application/json", "Origin: https://thedarkmetal.com"})
    @POST("v1/auth/login")
    Call<APIResponse<ResponseLogin>> login(@Body LoginModel user);

    @Headers({"Origin: https://thedarkmetal.com", "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZmUzMjFjMmRjMzBkMzIxMDY3OGUyNDkiLCJkdExvZ2luIjoiMjAyMS0wMi0wNVQwNTowMjoxMS43MTZaIiwiaWF0IjoxNjEyNTAxMzMxLCJleHAiOjE2MTI1ODc3MzF9.GbOAqm4Aty2NKn_SV8mEN_6uZwVxZQqnPDuCHbRgFBg"})
    @GET("v1/artists?skip=10&limit=10")
    Call<APIResponse<ArtistListResponse>> loadArtist();

    @Headers({"Origin: https://thedarkmetal.com", "Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJfaWQiOiI1ZmUzMjFjMmRjMzBkMzIxMDY3OGUyNDkiLCJkdExvZ2luIjoiMjAyMS0wMi0wNVQwNTowMjoxMS43MTZaIiwiaWF0IjoxNjEyNTAxMzMxLCJleHAiOjE2MTI1ODc3MzF9.GbOAqm4Aty2NKn_SV8mEN_6uZwVxZQqnPDuCHbRgFBg"})
    @GET("v1/albums")
    Call<APIResponse<AlbumListResponse>> loadAlbum();

}