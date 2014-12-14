package com.msevgi.repost.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.msevgi.repost.R;
import com.msevgi.repost.Task.GetFollowersTask;


public final class FragmentHome extends BaseFragment {

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

}
