package com.example.samuel.gitproject;

/**
 * Created by Samuel on 12/09/2017.
 */

public class Users {
    private String username,profie_image,profile_url;

    public Users() {
    }

    public Users(String username, String profie_image, String profile_url) {
        this.username = username;
        this.profie_image = profie_image;
        this.profile_url = profile_url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfie_image() {
        return profie_image;
    }

    public void setProfie_image(String profie_image) {
        this.profie_image = profie_image;
    }

    public String getProfile_url() {
        return profile_url;
    }

    public void setProfile_url(String profile_url) {
        this.profile_url = profile_url;
    }
}
