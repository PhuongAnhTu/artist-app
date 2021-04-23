package com.example.artist.detailScreen;

import com.example.artist.API.BaseResponseData;
import com.example.artist.model.AlbumData;
import com.example.artist.model.MemberModel;
import com.example.artist.model.PaginationModel;
import com.example.artist.model.SongData;

import java.util.List;

public class ArtistDetailResponse extends BaseDetailResponse implements BaseResponseData {
    public List<MemberModel> members;
    public String formed;
    public PaginationModel<SongData> songs;
    public List<AlbumData> albums;
}
