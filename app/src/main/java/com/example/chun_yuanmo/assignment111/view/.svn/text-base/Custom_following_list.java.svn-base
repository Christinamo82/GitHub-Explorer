package com.example.chun_yuanmo.assignment111.view;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chun_yuanmo.assignment111.R;
import com.example.chun_yuanmo.assignment111.model.API_following_model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by chun-yuanmo on 2017/10/30.
 */

/**
 * Custom image and text list view for diplay user's following page
 */
public class Custom_following_list extends ArrayAdapter<API_following_model>{
    ArrayList<API_following_model> following;
    Context context;
    int resource;

    /**
     * the constructor function
     * @param context the input context
     * @param resource the input resource
     * @param objects the input objects
     */
    public Custom_following_list(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<API_following_model> objects) {
        super(context, resource, objects);
        this.following = objects;
        this.context = context;
        this.resource = resource;
    }


    /**
     * The function of diplay correct image and text to the correct position
     * @param position the input position
     * @param convertView the input convertView
     * @param parent the input parent
     * @return the layout of the custom listview
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if(convertView == null){
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.list_followers_model, null, true);
        }

        API_following_model follow = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.img);
        Picasso.with(context).load(follow.getAvatarUrl()).into(imageView);

        TextView txtName = (TextView) convertView.findViewById(R.id.txt);
        txtName.setText(follow.getLogin());

        return convertView;
    }

}
