package com.example.chun_yuanmo.assignment111.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;

import com.example.chun_yuanmo.assignment111.R;

import retrofit2.Retrofit;


/**
 * Created by chun-yuanmo on 2017/11/5.
 */

public class Search extends Fragment {
    View myView;
    Retrofit retrofit = null;
    EditText search_user;
    EditText search_repo;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.repos_search, container, false);

        search_user = (EditText) myView.findViewById(R.id.user_search_input);
        search_repo = (EditText) myView.findViewById(R.id.repos_search_input);

        Button user_button = (Button) myView.findViewById(R.id.button_user_search);
        Button repos_button = (Button) myView.findViewById(R.id.button_search_repos);

        user_button.setOnClickListener(handleusers);
        repos_button.setOnClickListener(handlerepos);

        return myView;
    }

    private View.OnClickListener handleusers = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            UserSearch.user_search = search_user.getText().toString();
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new UserSearch()).commit();

        }
    };

    private View.OnClickListener handlerepos = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RepoSearch.repo_search = search_repo.getText().toString();
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new RepoSearch()).commit();
        }
    };
}
