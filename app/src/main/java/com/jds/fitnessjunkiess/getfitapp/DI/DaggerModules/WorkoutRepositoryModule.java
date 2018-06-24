package com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules;

import com.jds.fitnessjunkiess.getfitapp.Repositories.WorkoutRepository;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class WorkoutRepositoryModule {
  @Provides
  @Singleton
  WorkoutRepository provideWorkoutDataRepository() {
    return new WorkoutRepository(new Retrofit.Builder());
  }
}
