package com.msevgi.repost.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.msevgi.repost.AuthWebViewClient;
import com.msevgi.repost.R;
import com.msevgi.repost.task.GetUserInfoTask;
import com.msevgi.repost.application.RepostApplication;
import com.msevgi.repost.constant.ApplicationConstants;
import com.msevgi.repost.event.GetCodeEvent;
import com.msevgi.repost.event.GetMyProfileEvent;
import com.squareup.otto.Subscribe;

import butterknife.InjectView;

public final class LoginActivity extends BaseActionBarActivity implements View.OnClickListener {

    @InjectView(R.id.button_login)
    protected Button buttonLogin;

    @InjectView(R.id.webview_registiration)
    protected WebView webViewRegistiration;

    @InjectView(R.id.progressbar)
    protected ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String userId = RepostApplication.sharedPrefHelper.getUserId();
        if (!TextUtils.isEmpty(userId)) {
            Log.i("","access_token: "+RepostApplication.sharedPrefHelper.getAccesToken());
            leadNextPage();
        }
        buttonLogin.setOnClickListener(this);
    }

    @NonNull
    @Override
    public int getLayoutResource() {
        return R.layout.layout_login;
    }

    @Subscribe
    public void getCode(GetCodeEvent getCodeEvent) {
        RepostApplication.sharedPrefHelper.saveToken(getCodeEvent.getToken());
        new GetUserInfoTask(getCodeEvent.getToken()).execute();
    }

    @Subscribe
    public void getUserInfo(GetMyProfileEvent getMyProfileEvent) {
        RepostApplication.sharedPrefHelper.saveUserInfo(
                getMyProfileEvent.getBean(),
                getMyProfileEvent.getAccess_token());
        leadNextPage();
    }

    private void leadNextPage() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        buttonLogin.setClickable(false);
        progressBar.setVisibility(View.VISIBLE);

        webViewRegistiration.setVisibility(View.VISIBLE);
        webViewRegistiration.setWebViewClient(new AuthWebViewClient());
        webViewRegistiration.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                if (progress < 100 && progressBar.getVisibility() == ProgressBar.GONE) {
                    progressBar.setVisibility(ProgressBar.VISIBLE);
                }
                progressBar.setProgress(progress);
                if (progress == 100) {
                    progressBar.setVisibility(ProgressBar.GONE);
                }
            }
        });

        webViewRegistiration.getSettings().setJavaScriptEnabled(true);
        webViewRegistiration.setInitialScale(100);
        webViewRegistiration.loadUrl(ApplicationConstants.getAuthurl());
    }
}
