package com.example.artist.homeScreen;

import com.example.artist.API.BaseResponseData;
import com.example.artist.ArtistData;

import java.util.List;

public class ArtistListResponse implements BaseResponseData {
    public int total ;
    public int count;
    public int skip;
    public int limit;
    public List<ArtistData> list_data;
}
