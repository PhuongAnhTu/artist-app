package com.example.artist;

import android.content.Context;
import android.preference.PreferenceManager;

import com.example.artist.login.ResponseLogin;

public class SharePref {
    private static final String LOGIN_DATA = "LOGIN_DATA";
    private static final String FIRST_TIME_USE = "FIRST_TIME_USE";
    private static final String USER = "USER";

    public static boolean isFirstTimeUse(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(FIRST_TIME_USE, false);
    }

    public static void setFirstTimeUse(Context context){
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putBoolean(FIRST_TIME_USE, true)
                .apply();
    }

    public static String getUser(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(USER, "");
    }

    public static void setUser(Context context, String user) {
        PreferenceManager.getDefaultSharedPreferences(context)
                .edit()
                .putString(USER, user)
                .apply();
    }

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


}
