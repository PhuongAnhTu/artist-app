package com.example.artist.util;

public class MyUtil {
    public static String getStreamingUrl(String songId) {
        return "https://thedarkmetal.com:2208/streaming/" + songId;
    }
}
