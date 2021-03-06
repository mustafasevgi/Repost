package com.msevgi.repost.model;

public class User {
    private String username;
    private String bio;
    private String website;
    private String profile_picture;
    private String full_name;
    private String id;
    private int type;

    public User(String username, String bio, String website, String profile_picture, String full_name, String id) {
        this.username = username;
        this.bio = bio;
        this.website = website;
        this.profile_picture = profile_picture;
        this.full_name = full_name;
        this.id = id;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GetFollowersResponseBean{" +
                "username='" + username + '\'' +
                ", bio='" + bio + '\'' +
                ", website='" + website + '\'' +
                ", profile_picture='" + profile_picture + '\'' +
                ", full_name='" + full_name + '\'' +
                ", id='" + id + '\'' +
                ", type='" + type + '\'' +
                '}';
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
