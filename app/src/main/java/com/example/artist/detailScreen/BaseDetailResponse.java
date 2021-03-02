package com.example.artist.detailScreen;

import com.example.artist.API.BaseResponseData;
import com.example.artist.model.AlbumData;
import com.example.artist.model.CountryData;

import java.util.List;

public class BaseDetailResponse implements BaseResponseData {
    public String _id;
    public String name;
    public String code;
    public String genres;
    public String labels;
    public List<String> images;
    public CountryData country;

}
