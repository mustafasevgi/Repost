package com.msevgi.repost.application;

import android.app.Application;

import com.msevgi.repost.helper.SharedPrefHelper;

public class RepostApplication extends Application {
    public static SharedPrefHelper sharedPrefHelper;

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPrefHelper = new SharedPrefHelper(getApplicationContext());
    }
}
