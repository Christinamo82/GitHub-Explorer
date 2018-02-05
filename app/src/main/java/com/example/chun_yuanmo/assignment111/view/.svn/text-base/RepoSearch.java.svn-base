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
import android.widget.Toast;

import com.example.chun_yuanmo.assignment111.R;
import com.example.chun_yuanmo.assignment111.controller.Github_search_API;
import com.example.chun_yuanmo.assignment111.model.API_repos_search_API;

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


public class RepoSearch extends Fragment {
    View myView;
    public static String repo_search = "";

    Retrofit retrofit = null;

    /**
     *
     * @param inflater input inflater
     * @param container input container
     * @param savedInstanceState input savedInstanceState
     * @return the repos_search_list layout
     */
    @Nullable
    @Override

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.repos_search_list, container, false);
        final ListView repo_search_list = (ListView)myView.findViewById(R.id.list_repos_search);

        if(retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl("https://api.github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        Github_search_API client_repos = retrofit.create(Github_search_API.class);
        final Call<API_repos_search_API> search_repo_list = client_repos.get_search_repos(repo_search);

        search_repo_list.enqueue(new Callback<API_repos_search_API>() {
            /**
             * This function fetch the search the input repo's data
             * After fetching data, it will display to the list view of the layout, and the list
             * is sorted by the number of forks.
             * @param call input call
             * @param response input response
             */
            @Override
            public void onResponse(Call<API_repos_search_API> call, Response<API_repos_search_API> response) {
                int length = response.body().getRepositories().size();
                final String[] repos_name = new String[length];
                final String[] repos_owner = new String[length];
                final String[] repos_des = new String[length];
                final String[] repos_fork = new String[length];
                final Uri[] repos_link = new Uri[length];

                for(int i = 0; i < length; i++){
                    repos_name[i] = response.body().getRepositories().get(i).getName().toString();
                    repos_owner[i] = response.body().getRepositories().get(i).getOwner().toString();
                    String des = "";
                    if(response.body().getRepositories().get(i).getDescription() == null ||
                            response.body().getRepositories().get(i).getDescription() == ""){
                        des = "Repository's descirption is empty";
                    }
                    else{
                        des = response.body().getRepositories().get(i).getDescription().toString();
                    }
                    repos_des[i] = des;
                    repos_fork[i] = response.body().getRepositories().get(i).getForks().toString();
                    repos_link[i] = Uri.parse(response.body().getRepositories().get(i).getUrl().toString());
                }


                ArrayList<HashMap<String, String>> data = new ArrayList<HashMap<String, String>>();
                HashMap<String, String> map = new HashMap<String, String>();

                for(int j = 0; j < length; j++){
                    map = new HashMap<String, String>();
                    map.put("name", "repositories name: " + repos_name[j] + "\n" +
                            "repositories owner: " + repos_owner[j] + "\n" +
                    "description: " + repos_des[j]);
                    map.put("fork", "fork: " + repos_fork[j]);
                    data.add(map);
                }
                String[] key = {"name", "fork"};
                int[] ids = {android.R.id.text1, android.R.id.text2};

                SimpleAdapter adapter = new SimpleAdapter(
                        getActivity(),
                        data,
                        android.R.layout.simple_list_item_2,
                        key,
                        ids
                );

                repo_search_list.setAdapter(adapter);

                repo_search_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Object item = repo_search_list.getItemAtPosition(position);
                        Intent intent = new Intent(Intent.ACTION_VIEW,repos_link[position]);
                        startActivity(intent);
                    }
                });
            }

            /**
             * If the call back response it will shows "Repos search response fail!"
             * @param call input call
             * @param t input t
             */
            @Override
            public void onFailure(Call<API_repos_search_API> call, Throwable t) {
                Toast.makeText(getActivity(), "Repos search response fail!", Toast.LENGTH_SHORT).show();

            }
        });
        return myView;
    }
}
