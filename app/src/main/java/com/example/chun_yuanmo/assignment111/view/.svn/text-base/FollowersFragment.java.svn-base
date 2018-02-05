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
import com.example.chun_yuanmo.assignment111.controller.Github_followers_API;
import com.example.chun_yuanmo.assignment111.model.API_followers_model;

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


public class FollowersFragment extends Fragment {
    View myView;
    Retrofit retrofit = null;

    /**
     * When selected the follower, will bring to the follower's profile page
     * @param inflater the input inflater
     * @param container the input container
     * @param savedInstanceState the input savedInstanceState
     * @return The view of the layout and fetching data
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.followers_page, container, false);

        final ArrayList<API_followers_model> followers_array_list = new ArrayList<>();
        final ListView followers_list = (ListView)myView.findViewById(R.id.List_followers);

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Github_followers_API client_followers = retrofit.create(Github_followers_API.class);
        final Call<List<API_followers_model>> followers_list_view = client_followers
                .get_user_followers(ProfileFragment.user_profile);

        followers_list_view.enqueue(new Callback<List<API_followers_model>>() {
            @Override
            public void onResponse(Call<List<API_followers_model>> call, Response<List<API_followers_model>> response) {
                final int length = response.body().toArray().length;
                final String[] followers_username = new String[length];
                final Uri[] followers_image = new Uri[length];

                for(int i = 0; i < length; i++){
                    followers_username[i] = response.body().get(i).getLogin().toString();
                    followers_image[i] = Uri.parse(response.body().get(i).getAvatarUrl().toString());
                }


                for(int j = 0; j < length; j++){
                    followers_array_list.add(new API_followers_model(
                            followers_username[j],
                            followers_image[j].toString()
                    ));
                }

                //Disply the followers in the costume image and text list view
                Custom_follower_list adapter = new Custom_follower_list(
//                        getActivity().getApplicationContext(),
                        getContext(),
                        R.layout.list_followers_model,
                        followers_array_list
                );

                followers_list.setAdapter(adapter);

                //If the follower is select, show the profile fragment with follwoer's profile data
                followers_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        android.app.FragmentManager fragmentManager = getFragmentManager();
                        String followers_user = followers_username[position];
                        ProfileFragment.flag = 1;
                        ProfileFragment.other_profile = followers_username[position];
                        fragmentManager.beginTransaction().replace(R.id.content_frame, new ProfileFragment())
                                .addToBackStack(null).commit();
                    }
                });
            }

            @Override
            public void onFailure(Call<List<API_followers_model>> call, Throwable t) {

            }
        });



        return myView;
    }
}
