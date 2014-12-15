package com.msevgi.repost.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.msevgi.repost.model.User;
import com.msevgi.repost.constant.SharedPrefConstants;


public final class SharedPrefHelper {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefHelper(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveUserInfo(User bean,
                             String accessToken) {
        editor = sharedPreferences.edit();
        editor.putString(SharedPrefConstants.USERNAME, bean.getUsername());
        editor.putString(SharedPrefConstants.FULL_NAME, bean.getFull_name());
        editor.putString(SharedPrefConstants.PROFILE_IMAGE_LINK, bean.getProfile_picture());
        editor.putString(SharedPrefConstants.ACCESS_TOKEN, accessToken);
        editor.putString(SharedPrefConstants.USER_ID, bean.getId());
        editor.putString(SharedPrefConstants.BIO, bean.getBio());
        editor.putString(SharedPrefConstants.WEBSITE, bean.getWebsite());
        editor.commit();
    }

    public void saveToken(String token) {
        editor = sharedPreferences.edit();
        editor.putString(SharedPrefConstants.TOKEN, token);
        editor.commit();
    }

    public String getUsername() {
        return sharedPreferences.getString(SharedPrefConstants.USERNAME, "");
    }

    public String getFullName() {
        return sharedPreferences.getString(SharedPrefConstants.FULL_NAME, "");
    }

    public String getProfileImageLink() {
        return sharedPreferences.getString(SharedPrefConstants.PROFILE_IMAGE_LINK, "");
    }

    public String getUserId() {
        return sharedPreferences.getString(SharedPrefConstants.USER_ID, "");
    }

    public String getAccesToken() {
        return sharedPreferences.getString(SharedPrefConstants.ACCESS_TOKEN, "");
    }

    public String getToken() {
        return sharedPreferences.getString(SharedPrefConstants.TOKEN, "");
    }
}