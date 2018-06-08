package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Adapters.PagerAdapter;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.WorkoutsListInterface;
import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity.WorkoutViewActivity;
import com.jds.fitnessjunkiess.getfitapp.Entities.User;
import com.jds.fitnessjunkiess.getfitapp.R;

public class MainActivity extends AppCompatActivity implements WorkoutsListInterface {

    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workouts);
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

        TabLayout tabLayout = findViewById(R.id.tabs);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.pager);
        viewPager.setAdapter(pagerAdapter);
//        tabLayout.setTabsFromPagerAdapter();
        tabLayout.setupWithViewPager(viewPager, true);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        getSupportFragmentManager().popBackStackImmediate();
    }

    @Override
    public void onWorkoutSelected(int workoutId) {
        Intent workoutView = new Intent(getApplicationContext(), WorkoutViewActivity.class);
        workoutView.putExtra("workoutId", workoutId);
        startActivity(workoutView);
    }
}
