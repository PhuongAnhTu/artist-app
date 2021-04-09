package com.example.artist;

import android.app.Application;
import android.content.Context;

public class ArtistApp extends Application {
    private static Context context;

    public void onCreate() {
        super.onCreate();
        ArtistApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return ArtistApp.context;
    }
}

