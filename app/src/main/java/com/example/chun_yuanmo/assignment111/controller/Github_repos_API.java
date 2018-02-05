package com.example.chun_yuanmo.assignment111.controller;

import com.example.chun_yuanmo.assignment111.model.API_repos_model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by chun-yuanmo on 2017/10/30.
 */

public interface Github_repos_API {
    //fetch user's repos data
    @GET("users/{user}/repos")
        Call<List<API_repos_model>> get_user_repos (@Path("user") String user);

    //fetch authroization user's repos data
    @GET("/user/repos")
    Call<List<API_repos_model>> get_auth_repos(@Query("access_token") String acces_token);
}
