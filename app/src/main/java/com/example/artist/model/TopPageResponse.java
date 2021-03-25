package com.example.artist.model;

import com.example.artist.API.BaseResponseData;

import java.util.List;

public class TopPageResponse implements BaseResponseData {
    public List<ArtistData> artists;
    public List<AlbumData> albums;
    public List<SongData> songs;
}
