package com.example.chun_yuanmo.assignment111.view;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.chun_yuanmo.assignment111.R;
import com.example.chun_yuanmo.assignment111.controller.Github_following_API;
import com.example.chun_yuanmo.assignment111.model.API_following_model;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * Created by chun-yuanmo on 2017/10/30.
 */

public class FollowingFragment extends Fragment{
    View myView;
    Retrofit retrofit = null;
    public String user_following = "athrun9312";
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.following_page, container, false);

        final ArrayList<API_following_model> following_array_list = new ArrayList<>();
        final ListView following_list = (ListView)myView.findViewById(R.id.List_following);

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Github_following_API client_following = retrofit.create(Github_following_API.class);
        final Call<List<API_following_model>> following_list_view = client_following
                .get_user_following(ProfileFragment.user_profile);

        following_list_view.enqueue(new Callback<List<API_following_model>>() {
            @Override
            public void onResponse(Call<List<API_following_model>> call, Response<List<API_following_model>> response) {
                final int length = response.body().toArray().length;
                final String[] following_username = new String[length];
                final Uri[] following_image = new Uri[length];

                for(int i = 0; i < length; i++){
                    following_username[i] = response.body().get(i).getLogin().toString();
                    following_image[i] = Uri.parse(response.body().get(i).getAvatarUrl().toString());
                }
                for(int j = 0; j < length; j++){
                    following_array_list.add(new API_following_model(
                            following_username[j],
                            following_image[j].toString()
                    ));
                }


                Custom_following_list adapter = new Custom_following_list(
//                        getActivity().getApplicationContext(),
                        getContext(),
                        R.layout.list_followers_model,
                        following_array_list
                );

                following_list.setAdapter(adapter);

                following_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        android.app.FragmentManager fragmentManager = getFragmentManager();
                        String followers_user = following_username[position];
                        ProfileFragment.flag = 1;
                        ProfileFragment.other_profile = following_username[position];
                        fragmentManager.beginTransaction().replace(R.id.content_frame, new ProfileFragment())
                                .addToBackStack(null).commit();

                    }
                });
            }

            @Override
            public void onFailure(Call<List<API_following_model>> call, Throwable t) {

            }
        });

        return myView;
    }
}
