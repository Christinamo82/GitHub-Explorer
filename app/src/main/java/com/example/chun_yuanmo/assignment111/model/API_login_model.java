package com.example.chun_yuanmo.assignment111.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by chun-yuanmo on 2017/10/30.
 */

/**
 * The Login model for getting correct access_token
 */
public class API_login_model {
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("toekn_type")
    private String tokenType;

    public String getAccessToken() {
        return accessToken;
    }
    public String getToeknType(){
        return tokenType;
    }
}
