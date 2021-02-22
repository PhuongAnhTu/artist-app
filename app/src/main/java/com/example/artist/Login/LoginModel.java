package com.example.artist.Login;

import com.example.artist.API.Entity;

import java.io.Serializable;

public class LoginModel extends Entity implements Serializable {
    public String pw;
    public String email;
    public String data = "HCX337R0Bx5LgoAuYzLpbCNW8m5hOs96Ss+0EncT6ZDNa+LJHdnWMHYD5gBMgkK7";

    public LoginModel(String email, String password) {
        this.email = email;
        this.pw = password;
    }
}