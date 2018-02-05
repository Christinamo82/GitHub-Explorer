package com.example.chun_yuanmo.assignment111.controller;

import com.example.chun_yuanmo.assignment111.model.API_repos_search_API;
import com.example.chun_yuanmo.assignment111.model.API_users_search_API;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by chun-yuanmo on 2017/11/5.
 */

public interface Github_search_API {

    //fetch search user
    @GET("legacy/user/search/{user}?sort=followers")
    Call<API_users_search_API> get_search_user(@Path("user") String user);

//    //fetch search user
//    @GET("legacy/user/search/{user}")
//    Call<List<API_users_search_API>> get_search_user(@Path("user") String user);

    //fetch search repos
    @GET("legacy/repos/search/{repos}?sort=forks")
    Call<API_repos_search_API> get_search_repos(@Path("repos") String user);
}
