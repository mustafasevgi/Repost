package com.msevgi.repost.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

import com.msevgi.repost.R;

import butterknife.InjectView;

public final class RegisterDialog extends Dialog implements View.OnClickListener {

    @InjectView(R.id.webview_registiration)
    protected WebView webViewRegister;

    @InjectView(R.id.button_cancel)
    protected Button buttonCancel;

    public RegisterDialog(Context context) {
        super(context);
    }

    public RegisterDialog(Context context, int theme) {
        super(context, theme);
    }

    protected RegisterDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.instagram_register_dialog);
        buttonCancel.setOnClickListener(this);
    }

    public void loadUrl(String url){
        webViewRegister.getSettings().setJavaScriptEnabled(true);
        webViewRegister.loadUrl(url);
    }

    @Override
    public void onClick(View v) {
        dismiss();
    }
}
