package com.msevgi.repost.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.msevgi.repost.bean.response.UserResponseBean;
import com.msevgi.repost.constant.ApplicationConstants;
import com.msevgi.repost.event.GetMyProfileEvent;
import com.msevgi.repost.provider.BusProvider;
import com.msevgi.repost.rest.Connect;

import org.json.JSONException;
import org.json.JSONObject;

public class GetUserInfoTask extends AsyncTask<Void, Void, JSONObject> {
    private final String DEBUG_TAG = GetUserInfoTask.class.getSimpleName();
    private String token;

    public GetUserInfoTask(String token) {
        this.token = token;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        JSONObject jsonObject = null;
        try {
            Connect connect = new Connect(ApplicationConstants.TOKENURL, "POST");
            connect.setDoInput(true);
            connect.setDoOutput(true);
            jsonObject = connect.getJson("client_id=" + ApplicationConstants.CLIENT_ID +
                    "&client_secret="
                    + ApplicationConstants.CLIENT_SECRET + "&grant_type=authorization_code"
                    + "&redirect_uri=" + ApplicationConstants.CALLBACKURL + "&code=" + token);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    protected void onPostExecute(JSONObject result) {
        super.onPostExecute(result);
        if (result != null) {
            try {
                JSONObject tempObj = result.getJSONObject("user");
                String accessToken = result.getString("access_token");
                UserResponseBean bean = new Gson().fromJson(tempObj.toString(),
                        UserResponseBean.class);
                Log.i(DEBUG_TAG, bean.toString());
                BusProvider.getInstance().post(new GetMyProfileEvent(accessToken, bean));
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }
}
