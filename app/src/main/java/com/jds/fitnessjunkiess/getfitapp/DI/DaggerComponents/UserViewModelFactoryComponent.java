package com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents;

import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.UserViewModelFactoryModule;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.UserViewModelFactory;
import javax.inject.Singleton;
import dagger.Component;

@Singleton
@Component(modules = {UserViewModelFactoryModule.class})
public interface UserViewModelFactoryComponent {
  UserViewModelFactory provideUserViewModelFactory();
}
