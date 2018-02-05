package com.example.chun_yuanmo.assignment111.controller;

import com.example.chun_yuanmo.assignment111.model.API_notification_model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import java.util.List;

/**
 * Created by chun-yuanmo on 2017/11/6.
 */

public interface Github_notification_API {

    @GET("notifications")
    Call<List<API_notification_model>> get_notification(@Query("access_token") String acces_token,
                                                        @Query("all") String all);
}
