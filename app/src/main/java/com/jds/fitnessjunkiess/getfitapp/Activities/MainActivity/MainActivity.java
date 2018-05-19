package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.jds.fitnessjunkiess.getfitapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}

//Project Global TODOs
//TODO: Inject DI in to fragments and activities
//TODO: Use resources where needed
//TODO: Read the code, identify places where error checking needs to be done and implement it
//TODO: Read about performance optimisation, reduce number of times API is being called, data caching (eg. cache login data to remove necessity of making api calls