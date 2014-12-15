package com.msevgi.repost.event;


import com.msevgi.repost.model.User;

import java.util.HashMap;

public class GetFollowerListEvent {
    private HashMap<String, User> followerList;

    public GetFollowerListEvent(HashMap<String, User> followerList) {
        this.followerList = followerList;
    }

    public HashMap<String, User> getFollowerList() {
        return followerList;
    }

    public void setFriendList(HashMap<String, User> friendList) {
        this.followerList = friendList;
    }
}
