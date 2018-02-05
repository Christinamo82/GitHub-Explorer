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
import com.example.chun_yuanmo.assignment111.model.API_followers_model;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by chun-yuanmo on 2017/10/30.
 */

/**
 * Custom image and text list view for diplay user's followers page
 */
public class Custom_follower_list extends ArrayAdapter<API_followers_model> {
    ArrayList<API_followers_model> followers;
    Context context;
    int resource;

    /**
     * The constructor class
     * @param context the input context
     * @param resource the input resource
     * @param objects the input objects
     */
    public Custom_follower_list(@NonNull Context context, @LayoutRes int resource, @NonNull ArrayList<API_followers_model> objects) {
        super(context, resource, objects);
        this.followers = objects;
        this.context = context;
        this.resource = resource;
    }

    /**
     * The function of diplay correct image and text to the correct position
     * @param position the input position
     * @param convertView the input converView
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

        API_followers_model follower = getItem(position);

        ImageView imageView = (ImageView) convertView.findViewById(R.id.img);
        Picasso.with(context).load(follower.getAvatarUrl()).into(imageView);

        TextView txtName = (TextView) convertView.findViewById(R.id.txt);
        txtName.setText(follower.getLogin());

        return convertView;
    }
}
