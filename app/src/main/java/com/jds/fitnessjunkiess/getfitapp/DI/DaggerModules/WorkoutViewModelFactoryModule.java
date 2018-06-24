package com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules;

import com.jds.fitnessjunkiess.getfitapp.Repositories.WorkoutRepository;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.WorkoutViewModelFactory;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutViewModel;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class WorkoutViewModelFactoryModule {
  @Provides
  @Singleton
  WorkoutViewModelFactory provideWorkoutViewModelFactory() {
    return new WorkoutViewModelFactory(new WorkoutRepository(new Retrofit.Builder()));
  }
}
