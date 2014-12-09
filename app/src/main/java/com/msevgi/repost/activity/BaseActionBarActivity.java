package com.msevgi.repost.activity;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;

import com.msevgi.repost.provider.BusProvider;
import com.squareup.otto.Bus;

import butterknife.ButterKnife;

public abstract class BaseActionBarActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());


        ButterKnife.inject(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        getBus().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();

        getBus().unregister(this);
    }

    private Bus getBus() {
        return BusProvider.getInstance();
    }

    public abstract
    @NonNull
    @LayoutRes
    int getLayoutResource();
}
