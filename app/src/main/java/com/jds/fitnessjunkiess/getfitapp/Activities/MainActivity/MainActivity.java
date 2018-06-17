package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.WorkoutsListFragment;
import com.jds.fitnessjunkiess.getfitapp.Entities.User;
import com.jds.fitnessjunkiess.getfitapp.R;

public class MainActivity extends AppCompatActivity {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.user = new User();

        Intent intent = getIntent();
        User u = intent.getParcelableExtra("userData");
        //TODO: Sort this part out once login is done
        int userId;
        if (u != null) {
            this.user.setId(u.getId());
        } else {
            //TODO: Ideally stop app execution if no user ID is available
            userId = 0;
        }

        WorkoutsListFragment workoutsListFragment = new WorkoutsListFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.main_layout, workoutsListFragment).commit();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStackImmediate();
    }
}
