package com.example.artist.detailScreen;

import com.example.artist.API.BaseResponseData;
import com.example.artist.model.MemberModel;

import java.util.List;

public class ArtistDetailResponse extends BaseDetailResponse implements BaseResponseData {
    List<MemberModel> members;
}
