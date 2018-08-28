package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.Adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutListViewHolder extends RecyclerView.ViewHolder {

  public interface OnSelectedInterface {
    void onWorkoutSelected(Workout workoutId);
  }

  public TextView title;
  public ImageView icon;
  public Workout workoutIndex;

  WorkoutListViewHolder(View card, WorkoutListViewHolder.OnSelectedInterface onSelectedInterface) {
    super(card);
    this.title = card.findViewById(R.id.workout_card_title);
    this.icon = card.findViewById(R.id.icon);

    card.setOnClickListener(
        (View v) -> {
          Log.i("view", "" + this.workoutIndex);
          if (workoutIndex != null) {
            onSelectedInterface.onWorkoutSelected(this.workoutIndex);
          }
        });
  }

}
