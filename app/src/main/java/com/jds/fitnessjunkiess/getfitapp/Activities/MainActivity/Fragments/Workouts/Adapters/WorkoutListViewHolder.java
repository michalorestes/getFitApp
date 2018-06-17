package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.R;

class WorkoutListViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView subTitle;
    public ImageView icon;
    public int workoutIndex;

    WorkoutListViewHolder(View card, WorkoutListViewHolderInterface workoutListViewHolderInterface) {
        super(card);
        this.title = card.findViewById(R.id.workout_card_title);
        this.subTitle = card.findViewById(R.id.workout_card_sub_title);
        this.icon = card.findViewById(R.id.icon);
        this.workoutIndex = 0;

        card.setOnClickListener((View v) -> {
            Log.i("view", ""+ this.workoutIndex);
            workoutListViewHolderInterface.onWorkoutSelected(this.workoutIndex);
        });
    }
}

