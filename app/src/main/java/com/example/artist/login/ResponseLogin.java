package com.example.artist.login;

import android.content.Context;

import com.example.artist.API.BaseResponseData;
import com.example.artist.MainActivity;
import com.example.artist.SharePref;
import com.example.artist.model.LoginSession;
import com.google.gson.Gson;

import java.io.Serializable;

public class ResponseLogin  implements BaseResponseData, Serializable {
    public String fullName;
    public LoginSession login_session;
    public String toJsonString() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    static public ResponseLogin getFromSharedPreference(Context context) {
        String json = SharePref.getLoginData(context);
        if (json.isEmpty()) {
            return null;
        }
            Gson gson = new Gson();
            return gson.fromJson(json, ResponseLogin.class);
    }
}