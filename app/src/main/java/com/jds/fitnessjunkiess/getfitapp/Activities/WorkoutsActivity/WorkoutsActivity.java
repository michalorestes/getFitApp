package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.jds.fitnessjunkiess.getfitapp.Activities.LoginActivity.LoginActivity;
import com.jds.fitnessjunkiess.getfitapp.Entities.User;
import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);

        //TODO: simply fetch user's data based on email here instead
        Intent intent = getIntent();
        User u = intent.getParcelableExtra("userData");
        if (u != null){
            u.toString();
        }
    }
}
