package com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents;

import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.WorkoutViewModelModule;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.WorkoutViewModel;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {WorkoutViewModelModule.class})
public interface WorkoutViewModelComponent {
    WorkoutViewModel provideWorkoutViewModel();
}
