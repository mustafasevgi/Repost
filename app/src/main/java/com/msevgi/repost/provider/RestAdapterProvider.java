package com.msevgi.repost.provider;


import com.msevgi.repost.constant.ApplicationConstants;

import retrofit.RestAdapter;

public final class RestAdapterProvider {
    private static RestAdapter adapter;

    public static RestAdapter getInstance() {
        if (adapter == null)
            adapter = new RestAdapter
                    .Builder()
                    .setEndpoint(ApplicationConstants.API_URL)
                    .build();

        return adapter;
    }
}
