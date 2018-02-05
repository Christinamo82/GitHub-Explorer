package com.example.chun_yuanmo.assignment111.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.chun_yuanmo.assignment111.R;
import com.example.chun_yuanmo.assignment111.controller.Github_search_API;
import com.example.chun_yuanmo.assignment111.model.API_users_search_API;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chun-yuanmo on 2017/11/5.
 */

public class UserSearch extends Fragment {
    View myView;
    public static String user_search = "";

    Retrofit retrofit = null;

    /**
     *
     * @param inflater input inflater
     * @param container input container
     * @param savedInstanceState input saveInstanceState
     * @return the user_search_list layout
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.user_search_list, container, false);
        final ListView user_search_list = (ListView) myView.findViewById(R.id.list_user_search);

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Github_search_API client_repos = retrofit.create(Github_search_API.class);
        final Call<API_users_search_API> search_user_list = client_repos.get_search_user(user_search);

        search_user_list.enqueue(new Callback<API_users_search_API>() {
            /**
             * This function fetch the searching the input user's id or user's name's data
             * the data is going have user id, user name, numbers of the following, and numbers of following
             * After fetching data, will display data into list view of the layout, and the list is sorted
             * by the number of followers
             * @param call input call
             * @param response input response
             */
            @Override
            public void onResponse(Call<API_users_search_API> call, Response<API_users_search_API> response) {

//                Toast.makeText(getActivity(), response.body().getUsers().get(0).getLogin(), Toast.LENGTH_SHORT).show();
                int length = response.body().getUsers().size();
                final String[] user_id = new String[length];
                String[] user_name = new String[length];
                String[] user_followers = new String[length];
                for(int i = 0; i < length; i++){
                    user_id[i] = response.body().getUsers().get(i).getLogin().toString();
                    String name = "";
                    if(response.body().getUsers().get(i).getName() == null || response.body().getUsers().get(i).getName() == ""){
                        name = "User's name is empty";
                    }
                    else{
                        name = response.body().getUsers().get(i).getName().toString();
                    }
                    user_name[i] = name;

                    user_followers[i] = response.body().getUsers().get(i).getFollowers().toString();
                }



                ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> map = new HashMap<String, String>();

                for(int j = 0; j < length; j++){
                    map = new HashMap<String, String>();
                    map.put("user", "id: " + user_id[j] + "\n" +
                            "user name: " + user_name[j]);
                    map.put("follower", "Followers: " + user_followers[j]);
                    data.add(map);
                }
                String[] key = {"user", "follower"};
                int[] ids = {android.R.id.text1, android.R.id.text2};

                SimpleAdapter adapter = new SimpleAdapter(
                        getActivity(),
                        data,
                        android.R.layout.simple_list_item_2,
                        key,
                        ids
                );

                user_search_list.setAdapter(adapter);


                /**
                 * If one of the list is selected, it will bring to the ProfileFragment
                 */
                user_search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object item = user_search_list.getItemAtPosition(position);
                        ProfileFragment.user_profile = user_id[position];
                        android.app.FragmentManager fragmentManager = getFragmentManager();
                        fragmentManager.beginTransaction().replace(R.id.content_frame, new ProfileFragment()).commit();
                    }
                });

            }

            /**
             * If the call back response it will shows "User search response fail!"
             * @param call input call
             * @param t input t
             */
            @Override
            public void onFailure(Call<API_users_search_API> call, Throwable t) {
                Toast.makeText(getActivity(), "User search response fail!", Toast.LENGTH_SHORT).show();
            }
        });

        return myView;
    }
}
