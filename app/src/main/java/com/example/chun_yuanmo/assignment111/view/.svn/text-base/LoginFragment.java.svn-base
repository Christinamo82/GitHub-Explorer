package com.example.chun_yuanmo.assignment111.view;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import android.widget.TextView;
import android.widget.Toast;

import com.example.chun_yuanmo.assignment111.R;
import com.example.chun_yuanmo.assignment111.controller.Github_login_API;
import com.example.chun_yuanmo.assignment111.model.API_login_model;

/**
 * Created by chun-yuanmo on 2017/10/30.
 */

public class LoginFragment extends Fragment {
    View myView;
    final String client_id = "eeb0fd3e37de6f6df37f";
    final String client_secret = "637363ea487078658e1974bf99c83e810ce9127f";
    final String callback = "https://githubcallback";
    TextView token_view;
    //
    public static String access_token = "";
    EditText username;

//    @Nullable
//    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.login, container, false);
        username = (EditText)myView.findViewById(R.id.user_name_input);
        Button logoin_button = (Button) myView.findViewById(R.id.button_login);
        Button search_button = (Button) myView.findViewById(R.id.button_search);
        logoin_button.setOnClickListener(handleClick);
        search_button.setOnClickListener(handleSearch);
//
//
        return myView;
    }
    //
//
    private View.OnClickListener handleSearch = new View.OnClickListener() {

        @Override
        public void onClick(View v) {
//            android.app.FragmentManager fragmentManager = getFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.content_frame, new ProfileFragment()).commit();
            ProfileFragment.user_profile = username.getText().toString();
            ProfileFragment.original_profile = username.getText().toString();
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ProfileFragment()).commit();
        }
    };

    private View.OnClickListener handleClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://github.com/login/oauth/authorize"+ "?client_id=" +
                            client_id+ "&scope=user%20repo&redirect_uri=" +
                            callback));
            startActivity(intent);
        }
    };

    @Override
    public void onResume() {
        super.onResume();

        Uri uri = getActivity().getIntent().getData();

        if(uri != null && uri.toString().startsWith(callback)){
            String code = uri.getQueryParameter("code");

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://github.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            Github_login_API client_login = retrofit.create(Github_login_API.class);
            final Call<API_login_model> user_login = client_login.get_user_token(client_id, client_secret, code);
            user_login.enqueue(new Callback<API_login_model>() {
                @Override
                public void onResponse(Call<API_login_model> call, Response<API_login_model> response) {
//                    MainActivity.token_access = response.body().getAccessToken();
                    Toast.makeText(getActivity(), response.body().getAccessToken(), Toast.LENGTH_SHORT).show();
//                    Toast.makeText(getActivity(), "login sucess!", Toast.LENGTH_SHORT).show();
                    access_token = response.body().getAccessToken();
//                    android.app.FragmentManager fragmentManager = getFragmentManager();
//                    fragmentManager.beginTransaction().replace(R.id.content_frame, new LoginFragment()).commit();
                }

                @Override
                public void onFailure(Call<API_login_model> call, Throwable t) {
                    System.out.print("Cannot login");
                }
            });
        }
    }

}
