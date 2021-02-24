package com.example.artist;

import java.io.Serializable;
import java.util.List;

public class ArtistData implements Serializable {

    public List<String> images;
    public String name;
    public String genres;
    public String _id;
    public CountryData country;

}
