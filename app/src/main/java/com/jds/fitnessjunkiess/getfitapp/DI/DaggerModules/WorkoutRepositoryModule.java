package com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules;

import com.jds.fitnessjunkiess.getfitapp.Repositories.WorkoutDataRepository;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class WorkoutRepositoryModule {


    @Provides @Singleton
    WorkoutDataRepository provideWorkoutDataRepository(){
        return new WorkoutDataRepository(new Retrofit.Builder());
    }
}
