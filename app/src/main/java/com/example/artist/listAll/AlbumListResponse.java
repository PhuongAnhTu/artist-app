package com.example.artist.listAll;

import com.example.artist.API.BaseResponseData;
import com.example.artist.model.AlbumData;
import java.util.List;

public class AlbumListResponse implements BaseResponseData {
    public int total ;
    public int count;
    public int skip;
    public int limit;
    public List<AlbumData> list_data;
}
