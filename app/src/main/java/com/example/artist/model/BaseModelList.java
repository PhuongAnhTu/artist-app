package com.example.artist.model;

import java.io.Serializable;
import java.util.List;

public class BaseModelList implements Serializable  {
    public String _id;
    public String name;
    public String code;
    public List<String> images;
    public String genres;

    public boolean isSameId(BaseModelList other) {
        if (other == null) {
            return false;
        }
        return _id.equalsIgnoreCase(other._id);
    }
}
