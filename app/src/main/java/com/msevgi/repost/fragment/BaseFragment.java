package com.msevgi.repost.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.msevgi.repost.provider.BusProvider;
import com.squareup.otto.Bus;

import butterknife.ButterKnife;


public abstract class BaseFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(getLayoutResource(), container, false);

        ButterKnife.inject(this, view);
        return view;
    }

    protected abstract
    @LayoutRes
    @NonNull
    int getLayoutResource();

    @Override
    public void onResume() {
        super.onResume();
        getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        getBus().unregister(this);
    }

    private Bus getBus() {
        return BusProvider.getInstance();
    }
}
