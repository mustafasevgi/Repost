package com.msevgi.repost.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.msevgi.repost.R;

import butterknife.InjectView;


public final class FragmentHome extends BaseFragment {
    // list your subscribe https://api.instagram.com/v1/subscriptions?client_secret=6d4537eca40c4f6fa8383760e05b2599&client_id=7ea2e2925f6244a9a348e1dfe99d00b3
    private String authURLString = "https://instagram.com/oauth/authorize/?client_id=7ea2e2925f6244a9a348e1dfe99d00b3&redirect_uri=instagram://connect&response_type=code";

    //tokenURLString = TOKENURL + "?client_id=" + client_id + "&client_secret=" + client_secret + "&redirect_uri=" + CALLBACKURL + "&grant_type=authorization_code";

    @InjectView(R.id.webview_registiration)
    protected WebView webViewRegister;

    @NonNull
    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        webViewRegister.setWebViewClient(new AuthWebViewClient());
        webViewRegister.getSettings().setJavaScriptEnabled(true);
        webViewRegister.loadUrl(authURLString);
    }

    public class AuthWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("")) {
                System.out.println(url);
                String parts[] = url.split("=");
                Log.d("shouldOverrideUrlLoading", "shouldOverrideUrlLoading token: " + parts[1]);
                return true;
            }
            return false;
        }

        @Override
        public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
            super.onReceivedError(view, errorCode, description, failingUrl);

            Log.d("onReceiveError", "Page error: " + description);
        }


        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);


            Log.d("onPageStarted", "Loading URL: " + url);
        }


        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            Log.d("onPageFinished", "Finished URL: " + url);

        }
    }
}
