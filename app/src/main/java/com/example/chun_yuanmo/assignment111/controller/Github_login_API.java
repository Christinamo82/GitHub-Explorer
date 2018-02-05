package com.example.chun_yuanmo.assignment111.controller;

import com.example.chun_yuanmo.assignment111.model.API_login_model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by chun-yuanmo on 2017/10/30.
 */

public interface Github_login_API {
    @Headers("Accept: application/json")
    @POST("login/oauth/access_token")
    @FormUrlEncoded
    Call<API_login_model> get_user_token (@Field("client_id") String client_id,
                                          @Field("client_secret") String client_secret,
                                          @Field("code") String code);

}
