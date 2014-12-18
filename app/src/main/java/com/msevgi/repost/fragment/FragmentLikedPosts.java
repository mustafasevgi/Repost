package com.msevgi.repost.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.msevgi.repost.R;
import com.msevgi.repost.constant.ApplicationConstants;
import com.msevgi.repost.task.GetLikedPosts;

public class FragmentLikedPosts extends BaseFragment {
    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_liked_posts;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        new GetLikedPosts(ApplicationConstants.getLikedPostsUrl()).execute();
    }
}
