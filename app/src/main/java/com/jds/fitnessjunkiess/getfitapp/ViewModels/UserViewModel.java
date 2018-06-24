package com.jds.fitnessjunkiess.getfitapp.ViewModels;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.jds.fitnessjunkiess.getfitapp.Entities.User;
import com.jds.fitnessjunkiess.getfitapp.Repositories.UserRepository;

import javax.inject.Inject;

public class UserViewModel extends ViewModel {
  private LiveData<User> user;
  private UserRepository userRepository;

  @Inject
  public UserViewModel(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public void init(String email) {
    if (this.user != null) {
      return;
    }

    this.user = this.userRepository.getUser(email);
  }

  public LiveData<User> getUser() {
    return this.user;
  }

  public LiveData<User> addUser(User user) {
    return this.userRepository.addUser(user);
  }
}
