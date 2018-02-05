package com.example.chun_yuanmo.assignment111.controller;

import com.example.chun_yuanmo.assignment111.model.API_following_model;
import com.example.chun_yuanmo.assignment111.model.API_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by chun-yuanmo on 2017/10/30.
 */

/**
 * Interface for fetching user's profile data
 */
public interface Github_API {
    //fetch normal
    @GET("users/{user}")
    Call<API_model> get_user_profile (@Path("user") String user);

    //fetch with access_token
    @GET("/user")
    Call<API_model> get_auth_profile(@Query("access_token") String acces_token);

    //fetch authorize user's following list
    @GET("users/{user}/following")
    Call<List<API_following_model>> get_auth_following (@Path("user") String user);
}
