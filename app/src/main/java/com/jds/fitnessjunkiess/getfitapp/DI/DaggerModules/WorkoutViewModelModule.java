package com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules;

import com.jds.fitnessjunkiess.getfitapp.Repositories.WorkoutRepository;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class WorkoutViewModelModule {
  @Provides
  @Singleton
  WorkoutViewModel provideWorkoutViewmodel() {
    return new WorkoutViewModel(new WorkoutRepository(new Retrofit.Builder()));
  }
}
