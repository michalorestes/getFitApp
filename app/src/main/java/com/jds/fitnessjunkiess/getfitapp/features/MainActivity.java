package com.jds.fitnessjunkiess.getfitapp.features;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.ExercisesHomeScreenFragment;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.User;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.features.profile.ProfileFragment;
import com.jds.fitnessjunkiess.getfitapp.features.workout.WorkoutsListFragment;

public class MainActivity extends AppCompatActivity {

  public static User user;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_main);

    Toolbar myToolbar = findViewById(R.id.my_toolbar);
    setSupportActionBar(myToolbar);

    BottomNavigationView navigationItemView = findViewById(R.id.navigation);

    WorkoutsListFragment workoutsListFragment = WorkoutsListFragment.getInstance();
    getSupportFragmentManager()
        .beginTransaction()
        .add(R.id.main_layout, workoutsListFragment)
        .commit();

    navigationItemView.setOnNavigationItemSelectedListener(
        item -> {
          Fragment selectedFragment = null;
          switch (item.getItemId()) {
            case R.id.action_workouts:
              selectedFragment = WorkoutsListFragment.getInstance();
              break;

            case R.id.action_exercises:
              selectedFragment = ExercisesHomeScreenFragment.Companion.getInstance();
              break;

            case R.id.action_profile:
              selectedFragment = ProfileFragment.getInstance();
              break;
          }

          FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
          fragmentTransaction.replace(R.id.main_layout, selectedFragment);
          fragmentTransaction.commit();

          return true;
        });

    this.user = new User();
    Intent intent = getIntent();
    User u = intent.getParcelableExtra("userData");
    u.setId(7);
    MainActivity.user = u;
  }

  @Override
  public void onBackPressed() {
    super.onBackPressed();
    getSupportFragmentManager().popBackStackImmediate();
  }
}
