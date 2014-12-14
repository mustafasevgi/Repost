package com.msevgi.repost.Task;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.msevgi.repost.bean.response.UserResponseBean;
import com.msevgi.repost.event.GetUserListEvent;
import com.msevgi.repost.provider.BusProvider;
import com.msevgi.repost.rest.Connect;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GetFollowersTask extends AsyncTask<Void, Void, JSONObject> {
    private String url;
    private final String DEBUG_TAG = GetFollowersTask.class.getSimpleName();

    public GetFollowersTask(String url) {
        this.url = url;
    }

    @Override
    protected JSONObject doInBackground(Void... params) {
        JSONObject jsonObject = null;
        try {
            Connect connect = new Connect(url, "GET");
            jsonObject = connect.getJson();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return jsonObject;

    }

    @Override
    protected void onPostExecute(JSONObject jsonObject) {
        super.onPostExecute(jsonObject);
        if (jsonObject != null) {
            try {
                int resultCode = jsonObject.getJSONObject("meta").getInt("code");
                if (resultCode == 200) {
                    String nextUrl = jsonObject.getJSONObject("pagination").getString("next_url");
                    JSONArray jsonArray = jsonObject.getJSONArray("data");
                    Gson gson = new Gson();
                    ArrayList<UserResponseBean> list = new ArrayList<UserResponseBean>();
                    for (int j = 0; j < jsonArray.length(); j++) {
                        UserResponseBean bean = gson.fromJson(jsonArray.get(j).toString(),
                                UserResponseBean.class);
                        Log.i(DEBUG_TAG, bean.toString());
                        list.add(bean);
                    }
                    BusProvider.getInstance().post(new GetUserListEvent(list));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
