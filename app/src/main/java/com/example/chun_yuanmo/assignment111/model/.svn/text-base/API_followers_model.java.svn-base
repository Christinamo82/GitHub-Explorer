package com.example.chun_yuanmo.assignment111.model;

import android.app.Fragment;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by chun-yuanmo on 2017/10/30.
 */

/**
 * The followers model for getting correct user's followers data
 */
public class API_followers_model {
    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    public API_followers_model(String user_name, String img_link) {
        this.login = user_name;
        this.avatarUrl = img_link;
    }


    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }


    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }
}
