package com.msevgi.repost.constant;

import com.msevgi.repost.application.RepostApplication;

public class ApplicationConstants {

    public final static String AUTHURL = "https://api.instagram.com/oauth/authorize/";
    public final static String TOKENURL = "https://api.instagram.com/oauth/access_token";
    public final static String APIURL = "https://api.instagram.com/v1";
    public static String CALLBACKURL = "instagram://connect";

    public static String USER_DETAIL = "https://api.instagram.com/v1/users/";

    public final static String CLIENT_ID = "7ea2e2925f6244a9a348e1dfe99d00b3";
    public final static String CLIENT_SECRET = "6d4537eca40c4f6fa8383760e05b2599";
    public final static String MEDIA_LIKED_URL = "";

    public static String getFollowersUrl() {
        StringBuilder builder = new StringBuilder();
        String accessToken = RepostApplication.sharedPrefHelper.getAccesToken();
        String userId = RepostApplication.sharedPrefHelper.getUserId();
        builder.append(USER_DETAIL).append(userId).append("/follows?access_token=").append(accessToken);
        return builder.toString();
    }

    public static String getFollowedByUrl() {
        StringBuilder builder = new StringBuilder();
        String accessToken = RepostApplication.sharedPrefHelper.getAccesToken();
        String userId = RepostApplication.sharedPrefHelper.getUserId();
        builder.append(USER_DETAIL).append(userId).append("/followed-by?access_token=").append(accessToken);
        return builder.toString();
    }

    public static String getAuthurl() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(AUTHURL);
        stringBuilder.append("?client_id=");
        stringBuilder.append(CLIENT_ID);
        stringBuilder.append("&redirect_uri=");
        stringBuilder.append(CALLBACKURL);
        stringBuilder.append("&response_type=code&display=touch&scope=basic+likes+comments+relationships");
        return stringBuilder.toString();
    }

    public static String getRelationShipUrl() {
        StringBuilder builder = new StringBuilder();
        String accessToken = RepostApplication.sharedPrefHelper.getAccesToken();
        String userId = RepostApplication.sharedPrefHelper.getUserId();
        builder.append(USER_DETAIL).append(userId).append("/relationship?access_token=").append(accessToken);
        return builder.toString();
    }

    public static String getLikedPostsUrl() {
//        https:
//api.instagram.com/v1/users/self/media/liked?access_token=ACCESS-TOKEN
        StringBuilder builder = new StringBuilder();
        String accessToken = RepostApplication.sharedPrefHelper.getAccesToken();
        String userId = RepostApplication.sharedPrefHelper.getUserId();
        builder.append(USER_DETAIL).append(userId).append("/followed-by?access_token=").append(accessToken);
        return builder.toString();
    }
}
