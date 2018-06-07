package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Adapters;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Workouts.WorkoutsListFragment;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;

import java.util.List;

public class PagerAdapter extends FragmentPagerAdapter {
    WorkoutsListFragment workoutsListFragment;
    WorkoutsListFragment workoutsListFragment2;
    Context context;

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.workoutsListFragment = new WorkoutsListFragment();
        this.workoutsListFragment2 = new WorkoutsListFragment();
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 1){
            return this.workoutsListFragment;
        }
        return this.workoutsListFragment2;
    }

    public void updateWorkoutList(List<Workout> workouts, boolean newWorkout) {
//        this.workoutsListFragment.updateWorkoutsList(workouts, newWorkout);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
