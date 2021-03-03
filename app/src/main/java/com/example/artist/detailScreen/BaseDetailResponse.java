package com.example.artist.detailScreen;

import android.content.Context;

import com.example.artist.API.BaseResponseData;
import com.example.artist.SharePref;
import com.example.artist.login.ResponseLogin;
import com.example.artist.model.AlbumData;
import com.example.artist.model.CountryData;
import com.google.gson.Gson;

import java.util.List;

public class BaseDetailResponse implements BaseResponseData {
    public String _id;
    public String name;
    public String code;
    public String genres;
    public String labels;
    public List<String> images;
    public CountryData country;

    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }
}
