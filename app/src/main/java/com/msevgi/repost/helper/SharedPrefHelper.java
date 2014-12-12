package com.msevgi.repost.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.msevgi.repost.constant.SharedPrefConstants;


public final class SharedPrefHelper {
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    public SharedPrefHelper(Context context) {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(context);
    }

    public void saveUserInfo(String userName, String fullName) {
        editor = sharedPreferences.edit();
        editor.putString(SharedPrefConstants.USERNAME, userName);
        editor.putString(SharedPrefConstants.FULL_NAME, fullName);
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

    public String getToken() {
        return sharedPreferences.getString(SharedPrefConstants.TOKEN, "");
    }
}