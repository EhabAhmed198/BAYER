package com.ehabahmed.bayer.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Root{
    @SerializedName("user")
     User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
