package com.example.artist.detailScreen;

import com.example.artist.API.BaseResponseData;
import com.example.artist.model.ArtistData;
import com.example.artist.model.SongData;

import java.util.List;

public class AlbumDetailResponse extends BaseDetailResponse implements BaseResponseData {
    public String artistId;
    public int year;
    public String length;
    public String released;
    public ArtistData artist;
    public List<SongData> songs;
}
