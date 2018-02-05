package com.example.chun_yuanmo.assignment111.controller;

import com.example.chun_yuanmo.assignment111.model.API_following_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by chun-yuanmo on 2017/10/30.
 */

public interface Github_following_API {
    //fetch user's following data
    @GET("users/{user}/following")
    Call<List<API_following_model>> get_user_following (@Path("user") String user);

    //fetch authorization's following data
    @GET("/user/following")
    Call<List<API_following_model>> get_auth_following(@Query("access_token") String acces_token);
}
