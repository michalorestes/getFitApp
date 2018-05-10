package com.jds.fitnessjunkiess.getfitapp.Entities;

import com.google.gson.annotations.Expose;

public class User {

    @Expose
    private int id;
    @Expose
    private String email;
    @Expose
    private String username;
    @Expose
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}