package com.example.artist.listAll;

import com.example.artist.API.BaseResponseData;
import com.example.artist.model.ArtistData;

import java.util.List;

public class ArtistListResponse implements BaseResponseData {
    public int total ;
    public int count;
    public int skip;
    public int limit;
    public List<ArtistData> list_data;
}
