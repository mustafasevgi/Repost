package com.msevgi.repost.activity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ProgressBar;

import com.msevgi.repost.AuthWebViewClient;
import com.msevgi.repost.R;
import com.msevgi.repost.Task.GetUserInfoTask;
import com.msevgi.repost.application.RepostApplication;
import com.msevgi.repost.event.GetTokenEvent;
import com.squareup.otto.Subscribe;

import butterknife.InjectView;

public final class LoginActivity extends BaseActionBarActivity implements View.OnClickListener {

    private String authURLString = "https://instagram.com/oauth/authorize/?client_id=7ea2e2925f6244a9a348e1dfe99d00b3&redirect_uri=instagram://connect&response_type=code&display=touch&scope=basic+likes+comments+relationships";
    private Dialog dialog;
    @InjectView(R.id.button_login)
    protected Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String token = RepostApplication.sharedPrefHelper.getToken();
//        if (!TextUtils.isEmpty(token)) {
//            leadNextPage();
//        }
        buttonLogin.setOnClickListener(this);
    }

    @NonNull
    @Override
    public int getLayoutResource() {
        return R.layout.layout_login;
    }

    @Subscribe
    public void passNextPage(GetTokenEvent getTokenEvent) {
        RepostApplication.sharedPrefHelper.saveToken(getTokenEvent.getToken());
//        getAccessToken(getTokenEvent.getToken());
        new GetUserInfoTask(getTokenEvent.getToken()).execute();
        if (dialog != null)
            dialog.dismiss();
//        leadNextPage();
    }

    private void leadNextPage() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {

        prepareDialog();

        WebView webViewRegister = (WebView) dialog.findViewById(R.id.webview_register_dialog);
        Button buttonCancel = (Button) dialog.findViewById(R.id.button_cancel_dialog);
        final ProgressBar progressBar = (ProgressBar) dialog.findViewById(R.id.progress_bar_dialog);

        webViewRegister.setWebViewClient(new AuthWebViewClient());
        webViewRegister.setWebChromeClient(new WebChromeClient() {
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
        webViewRegister.getSettings().setJavaScriptEnabled(true);
        webViewRegister.setInitialScale(100);
        webViewRegister.loadUrl(authURLString);

        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                finish();
            }
        });
        dialog.show();
    }

    private void prepareDialog() {
        dialog = new Dialog(LoginActivity.this);
        dialog.setContentView(R.layout.instagram_register_dialog);
        dialog.setTitle(getResources().getString(R.string.login));
        dialog.setCancelable(false);
    }
}
