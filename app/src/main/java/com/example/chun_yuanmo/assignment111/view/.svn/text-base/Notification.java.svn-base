package com.example.chun_yuanmo.assignment111.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.chun_yuanmo.assignment111.R;
import com.example.chun_yuanmo.assignment111.controller.Github_notification_API;
import com.example.chun_yuanmo.assignment111.model.API_notification_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chun-yuanmo on 2017/11/7.
 */

/**
 * This fragment will shows the authorization's github notification
 */

public class Notification extends Fragment {
    View myView;
    Retrofit retrofit = null;
    String notification_accesToken = "b9dd1e01752ee55861d0832cf9e5a74a483d9324";

    /**
     *
     * @param inflater The input inlfater
     * @param container The input container
     * @param savedInstanceState The input saveInStancesState
     * @return the noification_layout
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.notification_layout, container, false);
        final ListView notification_list = (ListView) myView.findViewById(R.id.list_notification);

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Github_notification_API client_repos = retrofit.create(Github_notification_API.class);
        final Call<List<API_notification_model>> list = client_repos.get_notification(notification_accesToken, "true");

        list.enqueue(new Callback<List<API_notification_model>>() {
            /**
             * This function will fetch the authorization's notification data(title and type)
             * and it will display the data intot the list view of the layout
             * @param call input call
             * @param response input response
             */
            @Override
            public void onResponse(Call<List<API_notification_model>> call, Response<List<API_notification_model>> response) {
                int length = response.body().size();
                String[] title = new String[length];
                String[] type = new String[length];

                for(int i = 0; i < length; i++){
                    title[i] = response.body().get(i).getSubject().getTitle().toString();
                    type[i] = response.body().get(i).getSubject().getType().toString();
                }


                ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> map = new HashMap<String, String>();

                for(int j = 0; j < length; j++){
                    map = new HashMap<String, String>();
                    map.put("title", "Title: " + title[j]);
                    map.put("type", "Type: " + type[j]);
                    data.add(map);
                }
                String[] key = {"title", "type"};
                int[] ids = {android.R.id.text1, android.R.id.text2};

                SimpleAdapter adapter = new SimpleAdapter(
                        getActivity(),
                        data,
                        android.R.layout.simple_list_item_2,
                        key,
                        ids
                );

                notification_list.setAdapter(adapter);
            }

            /**
             * If the call back response it will shows "Notification respose fail!"
             * @param call input call
             * @param t input t
             */
            @Override
            public void onFailure(Call<List<API_notification_model>> call, Throwable t) {
                Toast.makeText(getActivity(), "Notification response fail!", Toast.LENGTH_SHORT).show();

            }
        });

        return myView;
    }
}
