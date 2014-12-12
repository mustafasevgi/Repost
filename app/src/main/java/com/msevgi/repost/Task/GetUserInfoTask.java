package com.msevgi.repost.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.msevgi.repost.Utils.Util;
import com.msevgi.repost.constant.ApplicationConstants;

import org.json.JSONObject;
import org.json.JSONTokener;

import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetUserInfoTask extends AsyncTask<Void, Void, Boolean> {
    private String token;

    public GetUserInfoTask(String token) {
        this.token = token;
    }

    @Override
    protected Boolean doInBackground(Void... params) {
        boolean result;
        try {
            URL url = new URL(ApplicationConstants.TOKENURL);
            // URL url = new URL(mTokenUrl + "&code=" + code);
            Log.i("mustaafa", "Opening Token URL " + url.toString());
            HttpURLConnection urlConnection = (HttpURLConnection) url
                    .openConnection();
            urlConnection.setRequestMethod("POST");
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);
            // urlConnection.connect();
            OutputStreamWriter writer = new OutputStreamWriter(
                    urlConnection.getOutputStream());
            writer.write("client_id=" + ApplicationConstants.CLIENT_ID + "&client_secret="
                    + ApplicationConstants.CLIENT_SECRET + "&grant_type=authorization_code"
                    + "&redirect_uri=" + ApplicationConstants.CALLBACKURL + "&code=" + token);
            writer.flush();
            String response = Util.streamToString(urlConnection
                    .getInputStream());
            Log.i("mustaafa", "response " + response);
            JSONObject jsonObj = (JSONObject) new JSONTokener(response)
                    .nextValue();

            String mAccessToken = jsonObj.getString("access_token");
            // Log.i(TAG, "Got access token: " + mAccessToken);

            String id = jsonObj.getJSONObject("user").getString("id");
            String user = jsonObj.getJSONObject("user").getString(
                    "username");
            String name = jsonObj.getJSONObject("user").getString(
                    "full_name");
            String userImage = jsonObj.getJSONObject("user").getString(
                    "profile_picture");
//                    mSession.storeAccessToken(mAccessToken, id, user, name,
//                            userImage);
            result = true;
        } catch (Exception ex) {
//                    what = WHAT_ERROR;
            result = false;
            ex.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        if (result) {

        } else {

        }
    }
}
