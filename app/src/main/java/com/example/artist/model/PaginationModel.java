package com.example.artist.model;

import java.util.List;

public class PaginationModel<T> {
    public int total;
    public int count;
    public int skip;
    public int limit;
    public List<T> list_data;
}
