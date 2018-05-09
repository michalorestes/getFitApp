package com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.jds.fitnessjunkiess.getfitapp.Repositories.UserRepository;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.UserViewModel;

import javax.inject.Inject;

public class UserViewModelFactory implements ViewModelProvider.Factory {
    private UserRepository userRepository;

    @Inject
    public UserViewModelFactory(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new UserViewModel(this.userRepository);
    }
}
