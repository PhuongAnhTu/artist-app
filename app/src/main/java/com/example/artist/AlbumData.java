package com.example.artist;

import java.io.Serializable;
import java.util.List;

public class AlbumData implements Serializable {
    String _id;
    String name;
    String code;
    int year;
    List<String> images;
    List<ArtistData> artist;



}
