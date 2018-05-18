package com.jds.fitnessjunkiess.getfitapp.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class User implements Parcelable {
    public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };

    @Expose
    private int id;
    @Expose
    private String email;
    @Expose
    private String username;
    @Expose
    private String password;

    public User(){

    }

    public User(Parcel in) {
        this.id = in.readInt();
        this.email = in.readString();
        this.username =  in.readString();
        this.password =  in.readString();
    }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.email);
        dest.writeString(this.username);
        dest.writeString(this.password);
    }

    @Override
    public String toString(){
        return getEmail() + " " + getUsername();
    }
}