package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity;

import android.animation.Animator;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jds.fitnessjunkiess.getfitapp.R;

public class ViewWorkoutFragment extends Fragment {

    public ViewWorkoutFragment(){

    }

    @Override
    public Animator onCreateAnimator(int transit, boolean enter, int nextAnim) {
        return super.onCreateAnimator(transit, enter, nextAnim);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_workout, container, false);
    }


}
