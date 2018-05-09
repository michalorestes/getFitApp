package com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules;

import com.jds.fitnessjunkiess.getfitapp.Repositories.UserRepository;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.UserViewModelFactory;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class UserViewModelFactoryModule {
    @Provides @Singleton
    UserViewModelFactory provideUserViewModelFactory(){
        return new UserViewModelFactory(
                new UserRepository(
                        new Retrofit.Builder()
                )
        );
    }
}
