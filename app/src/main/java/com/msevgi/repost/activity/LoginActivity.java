package com.msevgi.repost.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.Window;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.msevgi.repost.AuthWebViewClient;
import com.msevgi.repost.R;
import com.msevgi.repost.event.GetTokenEvent;
import com.squareup.otto.Subscribe;

import butterknife.InjectView;

public final class LoginActivity extends BaseActionBarActivity implements View.OnClickListener {

    private String authURLString = "https://instagram.com/oauth/authorize/?client_id=7ea2e2925f6244a9a348e1dfe99d00b3&redirect_uri=instagram://connect&response_type=code";

    @InjectView(R.id.button_login)
    protected Button buttonLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        buttonLogin.setOnClickListener(this);
    }

    @NonNull
    @Override
    public int getLayoutResource() {
        return R.layout.layout_login;
    }

    @Subscribe
    public void passNextPage(GetTokenEvent getTokenEvent) {
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
    }

    @Override
    public void onClick(View v) {

        final Dialog dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.instagram_register_dialog);
        dialog.setTitle(getResources().getString(R.string.login));
        WebView webViewRegister = (WebView) dialog.findViewById(R.id.webview_register_dialog);
        Button buttonCancel = (Button) dialog.findViewById(R.id.button_cancel);
        ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progress_bar);
//        progressBar.setVisibility(View.VISIBLE);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        webViewRegister.setWebViewClient(new AuthWebViewClient());
        webViewRegister.getSettings().setJavaScriptEnabled(true);
        webViewRegister.setInitialScale(100);
        webViewRegister.loadUrl(authURLString);
        dialog.show();
    }
}
