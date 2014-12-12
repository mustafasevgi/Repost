package com.msevgi.repost.event;

/**
 * Created by mustafasevgi on 10/12/14.
 */
public class GetTokenEvent {
    private String token;

    public GetTokenEvent(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
