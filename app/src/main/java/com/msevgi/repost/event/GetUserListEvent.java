package com.msevgi.repost.event;


import com.msevgi.repost.bean.response.UserResponseBean;

import java.util.ArrayList;

public class GetUserListEvent {
    private ArrayList<UserResponseBean> friendList;

    public GetUserListEvent(ArrayList<UserResponseBean> friendList) {
        this.friendList = friendList;
    }

    public ArrayList<UserResponseBean> getFriendList() {
        return friendList;
    }

    public void setFriendList(ArrayList<UserResponseBean> friendList) {
        this.friendList = friendList;
    }
}
