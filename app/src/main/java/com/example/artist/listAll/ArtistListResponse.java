package com.example.artist.listAll;

import com.example.artist.API.BaseResponseData;
import com.example.artist.model.ArtistData;

import java.util.List;

public class ArtistListResponse extends  BaseListResponse implements BaseResponseData {
    public List<ArtistData> list_data;
}
