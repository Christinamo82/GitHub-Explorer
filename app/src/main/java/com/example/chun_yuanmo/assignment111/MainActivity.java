package com.example.chun_yuanmo.assignment111;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.chun_yuanmo.assignment111.view.FollowersFragment;
import com.example.chun_yuanmo.assignment111.view.FollowingFragment;
import com.example.chun_yuanmo.assignment111.view.Graph;
import com.example.chun_yuanmo.assignment111.view.GraphAddition;
import com.example.chun_yuanmo.assignment111.view.LoginFragment;
import com.example.chun_yuanmo.assignment111.view.Notification;
import com.example.chun_yuanmo.assignment111.view.ProfileFragment;
import com.example.chun_yuanmo.assignment111.view.ReposFragment;
import com.example.chun_yuanmo.assignment111.view.Search;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * This handles the view of the navigation bar
     * @param savedInstanceState the input savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * This function handles the back button in the emulator. When the back button was clicked,
     * will bring back to the "first" searched github user profile page
     */
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ProfileFragment.user_profile = ProfileFragment.original_profile;
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {

            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }


    /**
     * The navigation drawer, whenever the page is selected from the drawer, it will bring
     * to the selectd page
     * @param item the input item
     * @return the fragment page of selected item
     */
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.app.FragmentManager fragmentManager = getFragmentManager();

        if (id == R.id.nav_login_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new LoginFragment()).commit();
        }
        else if(id == R.id.nav_search_layout){
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Search()).commit();
        }
        else if (id == R.id.nav_profile_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ProfileFragment()).commit();
        }
        else if (id == R.id.nav_repos_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new ReposFragment()).commit();
        }
        else if (id == R.id.nav_followers_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new FollowersFragment()).commit();
        }
        else if (id == R.id.nav_following_layout) {
            fragmentManager.beginTransaction().replace(R.id.content_frame, new FollowingFragment()).commit();
        }
        else if(id == R.id.nav_notification_layout){
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Notification()).commit();
        }
        else if(id == R.id.nav_graph_layout){
            fragmentManager.beginTransaction().replace(R.id.content_frame, new Graph()).commit();

        }
        else if(id == R.id.nav_graph_addtion_layout){
            fragmentManager.beginTransaction().replace(R.id.content_frame, new GraphAddition()).commit();
        }
//        else if (id == R.id.nav_store_profile) {
//            fragmentManager.beginTransaction().replace(R.id.content_frame, new auth_display()).commit();
//        }
//        else if(id == R.id.nav_store_repos){
//            fragmentManager.beginTransaction().replace(R.id.content_frame, new auth_repos_display()).commit();
//        }
//        else if(id == R.id.nav_store_followers){
//            fragmentManager.beginTransaction().replace(R.id.content_frame, new auth_followers_display()).commit();
//        }
//        else if(id == R.id.nav_store_followings){
//            fragmentManager.beginTransaction().replace(R.id.content_frame, new auth_following_display()).commit();
//        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}


