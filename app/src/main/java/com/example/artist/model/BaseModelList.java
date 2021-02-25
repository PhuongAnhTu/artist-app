package com.example.artist.model;

import com.example.artist.CountryData;

import java.io.Serializable;
import java.util.List;

public class BaseModelList implements Serializable  {

    public String _id;
    public String name;
    public String code;
    public List<String> images;
    public String genres;

}
