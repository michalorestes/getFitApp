package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Adapters;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Exercises.ExercisesListFragment;
import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.WorkoutsListFragment;

public class PagerAdapter extends FragmentPagerAdapter {
    private WorkoutsListFragment workoutsListFragment;
    private ExercisesListFragment exerciseList;

    public PagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
        this.workoutsListFragment = new WorkoutsListFragment();
        this.exerciseList = new ExercisesListFragment();
    }

    @Override
    public Fragment getItem(int position) {

        if (position == 0){
            return this.workoutsListFragment;
        }
        return this.exerciseList;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0){
            return "Workouts";
        }
        return"Exercises";
    }
}
