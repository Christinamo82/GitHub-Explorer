package com.example.chun_yuanmo.assignment111.view;

import android.app.Fragment;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chun_yuanmo.assignment111.R;
import com.example.chun_yuanmo.assignment111.controller.Github_API;
import com.example.chun_yuanmo.assignment111.model.API_model;
import com.squareup.picasso.Picasso;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
/**
 * Created by chun-yuanmo on 2017/10/30.
 */

public class ProfileFragment extends Fragment {

    View myView;
    public static String user_profile = "athrun9312";
    public static String other_profile = "";
    public static String original_profile = "athrun9312";
    public static int flag = 0;

    /**
     *This method handling the declation of the text view, buttons, and image from the
     * corresponding layout. And also handle the fetching user's profile data from the
     * github API. After fetching the data from the user's profle, set the text view(user's username,
     * name, email and bio), set the buttons(numbers of public repos, followers, and following), and
     * set user's profile picture to the image.
     * @param inflater The layout inflator
     * @param container the input container
     * @param savedInstanceState the input saveInstance
     * @return The view of the layout and fetching data
     */
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myView = inflater.inflate(R.layout.profile_page, container, false);
        //adding the api information to profile page

        final Button repos_page = (Button)myView.findViewById(R.id.button_repos_page);
        final Button followers_page = (Button)myView.findViewById(R.id.button_followers_page);
        final Button following_page = (Button)myView.findViewById(R.id.button_followiong_page);
        final TextView user_name = (TextView)myView.findViewById(R.id.name);
        final TextView user_id = (TextView)myView.findViewById(R.id.username);
        final TextView user_email = (TextView)myView.findViewById(R.id.email);
        final TextView user_bio = (TextView)myView.findViewById(R.id.bio);
        final TextView join_date = (TextView)myView.findViewById(R.id.textView2);
        final ImageView profile_image = (ImageView)myView.findViewById(R.id.imageView2);


        if(flag != 0){
            user_profile = other_profile;
        }

        flag = 0;



        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Github_API client = retrofit.create(Github_API.class);
        final Call<API_model> repos_list = client.get_user_profile(user_profile);

        repos_list.enqueue(new Callback<API_model>() {
            /**
             * Fetching the user's data(user id, user name, email, biography, numbers of public repos, numbers
             * of followers, and numbers of following
             * After fetching data, it will display to the profile layout
             * @param call input call
             * @param response input response
             */
            @Override
            public void onResponse(Call<API_model> call, Response<API_model> response) {
                String login = response.body().getLogin().toString();
                Uri avatar_url = Uri.parse(response.body().getAvatarUrl().toString());
                String public_repos = response.body().getPublicRepos().toString();
                String followers = response.body().getFollowers().toString();
                String following = response.body().getFollowing().toString();
                String created_at = response.body().getCreatedAt().toString();
                String username = "abc";
                if((response.body().getName()) == null || (response.body().getName()) == ""){
                    username = "User's name is empty";
                }
                else{
                    username = response.body().getName().toString();
                }
                String email = "abc";
                if ((response.body().getEmail())== null || (response.body().getEmail()) == ""){
                    email = "User's eamil is empty";
                }
                else{
                    email = response.body().getEmail().toString();
                }
                String bio = "abc";
                if((response.body().getBio()) == null || (response.body().getBio()) == ""){
                    bio = "User's bio is empty";
                }
                else{
                    bio = response.body().getBio().toString();
                }
                repos_page.setText("Repositories: " + public_repos);
                followers_page.setText("Followers: " + followers);
                following_page.setText("Following: " + following);
                user_id.setText(login);
                user_name.setText(username);
                user_email.setText(email);
                user_bio.setText(bio);
                join_date.setText("Joined at: " + created_at);
                Picasso.with(getContext()).load(avatar_url).resize(150, 150).into(profile_image);
            }
            @Override
            public void onFailure(Call<API_model> repos_list, Throwable t) {

            }
        });

        //finish adding the api information to profile page

        //adding button function, when clicking the button will brings to the corresponding fragment

        Button button_repos = (Button) myView.findViewById(R.id.button_repos_page);
        Button button_followers = (Button) myView.findViewById(R.id.button_followers_page);
        Button button_following = (Button) myView.findViewById(R.id.button_followiong_page);

        button_repos.setOnClickListener(handleClick);
        button_followers.setOnClickListener(handleClick);
        button_following.setOnClickListener(handleClick);

        //finsh button function
//        flag = flag + 1;
        return myView;

    }

    /**
     * This method handle the button click. When the button clicked, it will replace the fragmenet
     * corresponding to the fragment page(public repos, followers, and following)
     */
    private View.OnClickListener handleClick = new View.OnClickListener() {
        @Override

        public void onClick(View view) {
            int id = view.getId();
            android.app.FragmentManager fragmentManager = getFragmentManager();
            if(id == R.id.button_repos_page){
                fragmentManager.beginTransaction().replace(R.id.content_frame, new ReposFragment()).commit();
            }
            else if(id == R.id.button_followers_page){
                fragmentManager.beginTransaction().replace(R.id.content_frame, new FollowersFragment()).commit();
            }
            else if(id == R.id.button_followiong_page){
                fragmentManager.beginTransaction().replace(R.id.content_frame, new FollowingFragment()).commit();
            }

            return;

        }
    };

}
