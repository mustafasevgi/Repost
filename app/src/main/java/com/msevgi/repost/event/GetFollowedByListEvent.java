package com.msevgi.repost.event;


import com.msevgi.repost.model.User;

import java.util.ArrayList;

public class GetFollowedByListEvent {
    private ArrayList<User> followedByList;


    public GetFollowedByListEvent(ArrayList<User> followedByList) {
        this.followedByList = followedByList;
    }

    public ArrayList<User> getFollowedByList() {
        return followedByList;
    }

    public void setFollowedByList(ArrayList<User> followedByList) {
        this.followedByList = followedByList;
    }
}
