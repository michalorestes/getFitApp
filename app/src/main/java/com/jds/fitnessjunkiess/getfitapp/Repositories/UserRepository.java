package com.jds.fitnessjunkiess.getfitapp.Repositories;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.util.Log;

import com.jds.fitnessjunkiess.getfitapp.Entities.User;
import com.jds.fitnessjunkiess.getfitapp.Services.UserData;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UserRepository {
    private UserData userData;
    private Retrofit retrofit;

    @Inject
    public UserRepository(Retrofit.Builder retrofitBuilder) {
        this.retrofit = retrofitBuilder
                .baseUrl("http://4ed7ef54.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.userData = retrofit.create(UserData.class);
    }

    public LiveData<User> getUser(String email) {
        final Call<User> service = userData.getUser(email);
        final MutableLiveData<User> user = new MutableLiveData<>();

        service.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                user.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.e("**", "Could not load user data");
            }
        });

        return user;
    }

    public LiveData<User> addUser(User user){
        Call<User> service = userData.addUser(user);
        final MutableLiveData<User> userData = new MutableLiveData<>();
        service.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d("***", "onResponse: nope ");
                userData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i("***", "Failed to add user data:: " + t.getMessage());
            }
//            @Override
//            public void onResponse(Call<Void> call, Response<User> response) {
//                Log.d("***", "onResponse: " + response.body());
//            }
//
//            @Override
//            public void onFailure(Call<Void> call, Throwable t) {
//                Log.i("***", "Failed to add user data:: " + t.getMessage());
//            }
        });

        return userData;
    }
}
