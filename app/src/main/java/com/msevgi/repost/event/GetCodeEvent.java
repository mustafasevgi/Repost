package com.msevgi.repost.event;

public class GetCodeEvent {
    private String code;

    public GetCodeEvent(String code) {
        this.code = code;
    }

    public String getToken() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
