package com.jds.fitnessjunkiess.getfitapp.Services;

import com.jds.fitnessjunkiess.getfitapp.Entities.User;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface WorkoutData {
    @GET("workouts/{id}")
    Call<List<Workout>> getWorkouts(@Path("id") int id);
    @POST("workouts")
    Call<Workout> addWorkout(@Body Workout workout);
}
