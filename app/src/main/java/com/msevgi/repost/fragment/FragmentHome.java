package com.msevgi.repost.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.msevgi.repost.InstagramApp;
import com.msevgi.repost.R;
import com.msevgi.repost.constant.ApplicationConstants;


public final class FragmentHome extends BaseFragment {
    // list your subscribe https://api.instagram.com/v1/subscriptions?client_secret=6d4537eca40c4f6fa8383760e05b2599&client_id=7ea2e2925f6244a9a348e1dfe99d00b3

    String tokenURLString = ApplicationConstants.TOKENURL + "?client_id=" + ApplicationConstants.CLIENT_ID + "&client_secret=" + ApplicationConstants.CLIENT_SECRET + "&redirect_uri=" + ApplicationConstants.CALLBACKURL + "&grant_type=authorization_code";


    private InstagramApp instaObj;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Instagram Implementation

        instaObj = new InstagramApp(getActivity(), ApplicationConstants.CLIENT_ID,
                ApplicationConstants.CLIENT_SECRET, ApplicationConstants.CALLBACKURL);


        instaObj.authorize();  //add this in your button click or wherever you need to call the instagram api


        InstagramApp.OAuthAuthenticationListener listener1 = new InstagramApp.OAuthAuthenticationListener() {

            @Override
            public void onSuccess() {

                Log.e("Userid", instaObj.getId());
                Log.e("Name", instaObj.getName());
                Log.e("UserName", instaObj.getUserName());

            }

            @Override
            public void onFail(String error) {
                Toast.makeText(getActivity(), error, Toast.LENGTH_SHORT)
                        .show();
            }
        };
        instaObj.setListener(listener1);
    }

}
