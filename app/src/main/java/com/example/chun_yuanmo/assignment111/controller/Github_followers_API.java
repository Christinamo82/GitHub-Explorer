package com.example.chun_yuanmo.assignment111.controller;

import com.example.chun_yuanmo.assignment111.model.API_followers_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by chun-yuanmo on 2017/10/30.
 */

public interface Github_followers_API {
    //fetch user's followers data
    @GET("users/{user}/followers")
    Call<List<API_followers_model>> get_user_followers(@Path("user") String user);

    //fetch authorization's followers data
    @GET("/user/followers")
    Call<List<API_followers_model>> get_auth_followers(@Query("access_token") String acces_token);
}
