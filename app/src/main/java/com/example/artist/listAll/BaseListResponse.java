package com.example.artist.listAll;

import com.example.artist.API.BaseResponseData;

public class BaseListResponse implements BaseResponseData {
    public int total ;
    public int count;
    public int skip;
    public int limit;
}
