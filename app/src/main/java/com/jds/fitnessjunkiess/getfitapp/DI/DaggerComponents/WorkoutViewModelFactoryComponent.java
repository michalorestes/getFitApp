package com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents;

import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.WorkoutViewModelFactoryModule;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.WorkoutViewModelFactory;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {WorkoutViewModelFactoryModule.class})
public interface WorkoutViewModelFactoryComponent {
    WorkoutViewModelFactory provideWorkoutViewModelFactory();
}
