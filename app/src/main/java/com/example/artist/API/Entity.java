package com.example.artist.API;

import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

public class Entity {
    private static final Gson GSON = new GsonBuilder().serializeNulls().create();

    @NonNull
    @Override
    public String toString() {
        try{
            JSONObject json = new JSONObject(GSON.toJson(this));
            return json.toString(4);
        }catch (JSONException e){
            return new Gson().toJson(this);
        }
    }
}
