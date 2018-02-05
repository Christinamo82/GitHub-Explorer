package com.example.chun_yuanmo.assignment111.model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
/**
 * Created by chun-yuanmo on 2017/11/6.
 */

public class API_notification_model {
    @SerializedName("subject")
    @Expose
    private Subject subject;

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

}
