package com.msevgi.repost;

import android.graphics.Bitmap;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.msevgi.repost.constant.ApplicationConstants;
import com.msevgi.repost.event.GetCodeEvent;
import com.msevgi.repost.provider.BusProvider;

public class AuthWebViewClient extends WebViewClient {

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        if (url.startsWith(ApplicationConstants.CALLBACKURL)) {
            String parts[] = url.split("=");
            BusProvider.getInstance().post(new GetCodeEvent(parts[1]));
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
