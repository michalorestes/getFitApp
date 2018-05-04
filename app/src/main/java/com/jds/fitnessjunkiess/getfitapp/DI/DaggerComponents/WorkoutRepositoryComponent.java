package com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents;

import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.WorkoutRepositoryModule;
import com.jds.fitnessjunkiess.getfitapp.Repositories.WorkoutDataRepository;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {WorkoutRepositoryModule.class})
public interface WorkoutRepositoryComponent {
    WorkoutDataRepository provideWorkoutDataRepository();
}
