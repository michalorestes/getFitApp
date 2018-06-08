package com.jds.fitnessjunkiess.getfitapp.Repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.Services.WorkoutData;
import java.util.List;
import javax.inject.Inject;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class WorkoutRepository {

    private WorkoutData workoutData;
    private Retrofit retrofit;

    @Inject
    public WorkoutRepository(Retrofit.Builder retrofitBuilder) {
        this.retrofit = retrofitBuilder
                .baseUrl(EndPoint.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.workoutData = retrofit.create(WorkoutData.class);
    }

    public LiveData<List<Workout>> getWorkouts(int userId) {
        final Call<List<Workout>> service = workoutData.getWorkouts(userId);
        final MutableLiveData<List<Workout>> workouts = new MutableLiveData<>();

        service.enqueue(new Callback<List<Workout>>() {
            @Override
            public void onResponse(@NonNull Call<List<Workout>> call, @NonNull Response<List<Workout>> response) {
                workouts.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<List<Workout>> call, @NonNull Throwable t) {
                Log.e("**", "Something went wrong hahaqhah");
            }
        });

        return workouts;
    }

    public LiveData<Workout> addWorkout(Workout workout){
        final Call<Workout> service = workoutData.addWorkout(workout);
        final MutableLiveData<Workout> workoutMutableLiveData = new MutableLiveData<>();

        service.enqueue(new Callback<Workout>() {
            @Override
            public void onResponse(@NonNull Call<Workout> call, @NonNull Response<Workout> response) {
                workoutMutableLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(@NonNull Call<Workout> call, @NonNull Throwable t) {
                Log.i("Workouts Repository: ", t.getMessage());
            }
        });

        return workoutMutableLiveData;
    }
}
