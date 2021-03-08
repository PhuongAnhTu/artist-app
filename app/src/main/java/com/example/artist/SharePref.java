package com.example.artist;

import android.content.Context;
import android.preference.PreferenceManager;

import com.example.artist.detailScreen.AlbumDetailResponse;
import com.example.artist.login.ResponseLogin;

public class SharePref {
    private static final String LOGIN_DATA = "LOGIN_DATA";
    private static final String ID_THUMBNAIL = "ID_THUMBNAIL";


    public static String getLoginData(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(LOGIN_DATA, "");
    }

    public static void setLoginData(Context context, ResponseLogin loginData) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(LOGIN_DATA, loginData.toJsonString())
                .apply();
    }

//    public static void clearLoginData(Context context) {
//        PreferenceManager.getDefaultSharedPreferences(context)
//                .edit().remove(LOGIN_DATA)
//    }
}
