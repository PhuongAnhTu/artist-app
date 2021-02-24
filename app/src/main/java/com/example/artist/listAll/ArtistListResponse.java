package com.example.artist.listAll;

import com.example.artist.API.BaseResponseData;
import com.example.artist.AlbumData;
import com.example.artist.ArtistData;

import java.util.ArrayList;
import java.util.List;

public class ArtistListResponse implements BaseResponseData {
    public int total ;
    public int count;
    public int skip;
    public int limit;
    public ArrayList<ArtistData> list_data;
}
