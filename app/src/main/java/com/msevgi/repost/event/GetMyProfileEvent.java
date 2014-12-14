package com.msevgi.repost.event;


import com.msevgi.repost.bean.response.UserResponseBean;

public class GetMyProfileEvent {

    private String access_token;
    private UserResponseBean bean;


    public GetMyProfileEvent(String access_token, UserResponseBean bean) {
        this.access_token = access_token;
        this.bean = bean;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public UserResponseBean getBean() {
        return bean;
    }

    public void setBean(UserResponseBean bean) {
        this.bean = bean;
    }

    @Override
    public String toString() {
        return "GetUserInfoEvent{" +
                "access_token='" + access_token + '\'' +
                ", bean=" + bean.toString() +
                '}';
    }
}
