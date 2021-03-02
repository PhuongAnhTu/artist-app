package com.example.artist.detailScreen;

import com.example.artist.API.BaseResponseData;
import com.example.artist.model.AlbumData;
import com.example.artist.model.ArtistData;

public class AlbumDetailResponse extends BaseDetailResponse {
    public String artistId;
    public int year;
    public String length;
    public String released;
    public ArtistData artist;

}
