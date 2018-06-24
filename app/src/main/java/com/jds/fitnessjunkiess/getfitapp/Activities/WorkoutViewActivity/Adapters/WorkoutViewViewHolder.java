package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.R;

class WorkoutViewViewHolder extends RecyclerView.ViewHolder {

  public TextView exerciseName;
  public TextView sets;
  public TextView reps;
  public ImageView icon;

  WorkoutViewViewHolder(View card) {
    super(card);
    this.exerciseName = card.findViewById(R.id.exercise_name);
    this.sets = card.findViewById(R.id.exercise_sets);
    this.reps = card.findViewById(R.id.exercise_reps);
    this.icon = card.findViewById(R.id.exercise_icon);
  }
}
