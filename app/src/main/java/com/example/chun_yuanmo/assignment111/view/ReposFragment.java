package com.example.chun_yuanmo.assignment111.view;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.chun_yuanmo.assignment111.R;
import com.example.chun_yuanmo.assignment111.controller.Github_repos_API;
import com.example.chun_yuanmo.assignment111.model.API_repos_model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by chun-yuanmo on 2017/10/30.
 */

public class ReposFragment extends Fragment {
    View myView;
    Retrofit retrofit = null;
    /**
     * This function fetching the user's public repos page (repos name, owner, and description) and display
     * the data on the fragment
     * @param inflater The layout inflator
     * @param container the input container
     * @param savedInstanceState the input saveInstance
     * @return The view of the layout and fetching data
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.public_repos_page, container, false);

        final ListView repos_list = (ListView) myView.findViewById(R.id.List_repos);

        //fetching public repo's data
        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Github_repos_API client_repos = retrofit.create(Github_repos_API.class);
        final Call<List<API_repos_model>> repos_list_view = client_repos.get_user_repos(ProfileFragment.user_profile);

        repos_list_view.enqueue(new Callback<List<API_repos_model>>() {
            @Override
            public void onResponse(Call<List<API_repos_model>> call, Response<List<API_repos_model>> response) {

                final int length = response.body().toArray().length;
                final String[] repos_name = new String[length];
                final String[] owner = new String[length];
                final String[] description = new String[length];
                final Uri[] repos_link = new Uri[length];

                //Getting each repo's name, owner, and description to the array
                //If the description is null, set it to the stirng "Description is empty".
                for(int i = 0; i < length; i++){
                    repos_name[i] = response.body().get(i).getName().toString();
                    String current_owner = response.body().get(i).getFullName().toString();
                    String[] current_owner_split = current_owner.split("/");
                    owner[i] = current_owner_split[0];
                    if(response.body().get(i).getDescription() == null ||
                            response.body().get(i).getDescription() == ""){
                        description[i] = "Description is empty";
                    }
                    else {
                        description[i] = response.body().get(i).getDescription().toString();
                    }
                    repos_link[i] = Uri.parse(response.body().get(i).getHtmlUrl().toString());

                }



                //https://www.youtube.com/watch?v=FvxXGzunujo
                //To fill the list with the text
                ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> map = new HashMap<String, String>();

                for(int j = 0; j < length; j++){
                    map = new HashMap<String, String>();
                    map.put("repos name", "Repositories name: " + repos_name[j].toString() + "\n" +
                            "owner: " + owner[j].toString());
//                    map.put("owner", owner[j]);
                    map.put("description", "Description: " + description[j].toString());
                    map.put("link", (repos_link).toString());

                    data.add(map);
                }

                String[] key = {"repos name", "description"};
                int[] ids = {android.R.id.text1, android.R.id.text2};

                SimpleAdapter adapter = new SimpleAdapter(
                        getActivity(),
                        data,
                        android.R.layout.simple_list_item_2,
                        key,
                        ids
                );

                repos_list.setAdapter(adapter);

                repos_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object item = repos_list.getItemAtPosition(position);
                        Intent intent = new Intent(Intent.ACTION_VIEW,repos_link[position]);
                        startActivity(intent);
                    }
                });

            }

            @Override
            public void onFailure(Call<List<API_repos_model>> call, Throwable t) {

            }
        });

//        Button button1 = (Button) myView.findViewById(R.id.button_cs225);
//        Button button2 = (Button) myView.findViewById(R.id.button_cs241);
//
//        button1.setOnClickListener(handleClick);
//        button2.setOnClickListener(handleClick);
        return myView;
    }
}
