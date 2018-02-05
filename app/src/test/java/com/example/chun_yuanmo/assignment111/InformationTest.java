package com.example.chun_yuanmo.assignment111;

import com.example.chun_yuanmo.assignment111.controller.Github_search_API;
import com.example.chun_yuanmo.assignment111.model.API_users_search_API;

import org.junit.Test;

import static org.junit.Assert.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chun-yuanmo on 2017/11/6.
 */

public class InformationTest {


    @Test
    public void user_id_isCorrect() throws Exception{
        Retrofit retrofit = null;
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Github_search_API client_repos = retrofit.create(Github_search_API.class);
        final Call<API_users_search_API> search_user = client_repos.get_search_user("Jojo Xu");
        System.out.print("Before response" + "\n");
        search_user.enqueue(new Callback<API_users_search_API>() {
            @Override
            public void onResponse(Call<API_users_search_API> call, Response<API_users_search_API> response) {
                String search_user_id = response.body().getUsers().get(0).getLogin().toString();
                System.out.print(search_user_id);
                assertEquals(search_user_id, "Jojo Xu");
            }

            @Override
            public void onFailure(Call<API_users_search_API> call, Throwable t) {
            }
        });
    }
}
