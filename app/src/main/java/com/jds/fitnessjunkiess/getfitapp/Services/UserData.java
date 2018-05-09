package com.jds.fitnessjunkiess.getfitapp.Services;

import android.arch.lifecycle.LiveData;
import com.jds.fitnessjunkiess.getfitapp.Entities.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserData {
    @GET("users/{email}")
    Call<User> getUser(@Path("email") String email);
    @POST("users")
    void addUser(User user);
}
