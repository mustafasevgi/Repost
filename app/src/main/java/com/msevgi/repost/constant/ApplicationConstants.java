package com.msevgi.repost.constant;

public class ApplicationConstants {

    public final static String AUTHURL = "https://api.instagram.com/oauth/authorize/";
    public final static String TOKENURL = "https://api.instagram.com/oauth/access_token";
    public final static String APIURL = "https://api.instagram.com/v1";
    public static String CALLBACKURL = "instagram://connect";

    public static String USER_DETAIL = "https://api.instagram.com/v1/users/";

    public final static String CLIENT_ID = "7ea2e2925f6244a9a348e1dfe99d00b3";
    public final static String CLIENT_SECRET = "6d4537eca40c4f6fa8383760e05b2599";

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
}