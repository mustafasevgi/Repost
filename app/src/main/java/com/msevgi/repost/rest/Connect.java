package com.msevgi.repost.rest;

import android.text.TextUtils;

import com.msevgi.repost.Utils.STS;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class Connect {

    private String url;
    private String requestMethod;
    private boolean doInput;
    private boolean doOutput;
    private OutputStreamWriter writer;
    private HttpURLConnection urlConnection;

    public Connect(String url, String requestMethod) {
        this.url = url;
        this.requestMethod = requestMethod;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(String requestMethod) {
        this.requestMethod = requestMethod;
    }

    public boolean isDoInput() {
        return doInput;
    }

    public void setDoInput(boolean doInput) {
        this.doInput = doInput;
    }

    public boolean isDoOutput() {
        return doOutput;
    }

    public void setDoOutput(boolean doOutput) {
        this.doOutput = doOutput;
    }

    private void write(String url) throws IOException {
        if (urlConnection != null) {
            writer = new OutputStreamWriter(urlConnection.getOutputStream());
            writer.write(url);
            writer.flush();
        }
    }

    public JSONObject getJson() throws Exception {
        URL url1 = new URL(url);
        urlConnection = (HttpURLConnection) url1.openConnection();
        urlConnection.setRequestMethod(requestMethod);
        String response = STS.streamToString(urlConnection
                .getInputStream());

        JSONObject jsonObject = (JSONObject) new JSONTokener(response)
                .nextValue();

        return jsonObject;
    }

    public JSONObject getJson(String specifiedUrl) throws Exception {
        URL url1 = new URL(url);
        urlConnection = (HttpURLConnection) url1.openConnection();
        urlConnection.setRequestMethod(requestMethod);
        if (!TextUtils.isEmpty(specifiedUrl))
            write(specifiedUrl);
        String response = STS.streamToString(urlConnection
                .getInputStream());

        JSONObject jsonObject = (JSONObject) new JSONTokener(response)
                .nextValue();

        return jsonObject;
    }
}
