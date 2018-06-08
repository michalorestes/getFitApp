package com.jds.fitnessjunkiess.getfitapp.Activities.Initialisation;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.jds.fitnessjunkiess.getfitapp.Activities.LoginActivity.LoginActivity;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.MainActivity;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.DaggerUserViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerComponents.UserViewModelFactoryComponent;
import com.jds.fitnessjunkiess.getfitapp.DI.DaggerModules.UserViewModelFactoryModule;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.Factories.UserViewModelFactory;
import com.jds.fitnessjunkiess.getfitapp.ViewModels.UserViewModel;

public class InitialisationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initialisation);

        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if (account != null){
            startWorkoutsActivity(account);
        } else {
            startLoginActivity();
        }
    }

    private void startLoginActivity() {
        Intent loginActivity = new Intent(this, LoginActivity.class);
        startActivity(loginActivity);
        finish();
    }

    private void startWorkoutsActivity(GoogleSignInAccount account) {
        UserViewModelFactoryComponent userViewModelFactoryComponent =
                DaggerUserViewModelFactoryComponent
                        .builder()
                        .userViewModelFactoryModule(new UserViewModelFactoryModule())
                        .build();

        UserViewModelFactory userViewModelFactory =
                userViewModelFactoryComponent
                        .provideUserViewModelFactory();

        UserViewModel userViewModel = ViewModelProviders.of(this, userViewModelFactory)
                .get(UserViewModel.class);

        userViewModel.init(account.getEmail());
        userViewModel.getUser().observe(this, u -> {
            if (u != null){
                Log.i("***", "Got the user: " + u.getEmail() + " " + u.getUsername());
                Intent workoutsActivity = new Intent(this, MainActivity.class);
                workoutsActivity.putExtra("userData", u);
                startActivity(workoutsActivity);
                finish();
            }
        });
    }
}
