package com.msevgi.repost.event;

public class NavigationItemSelect {
    private int position;


    public NavigationItemSelect(int position) {
        this.position = position;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
