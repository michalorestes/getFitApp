package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Fragments.Workouts.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListRecycleViewAdapter extends RecyclerView.Adapter<WorkoutListViewHolder> {

  private List<Workout> dataSet;
  private WorkoutListViewHolderInterface workoutListViewHolderInterface;

  public WorkoutListRecycleViewAdapter(
      WorkoutListViewHolderInterface workoutListViewHolderInterface) {
    this.dataSet = new ArrayList<>();
    this.workoutListViewHolderInterface = workoutListViewHolderInterface;
  }

  @NonNull
  @Override
  public WorkoutListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View workoutCard =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.workout_card_layout, parent, false);

    return new WorkoutListViewHolder(workoutCard, this.workoutListViewHolderInterface);
  }

  @Override
  public void onBindViewHolder(WorkoutListViewHolder holder, int position) {
    holder.title.setText(this.dataSet.get(position).getName());
    holder.subTitle.setText(
        String.valueOf(this.dataSet.get(position).getExercises().size()) + " exercises");
    holder.icon.setImageResource(this.getWorkoutIcon(this.dataSet.get(position).getType()));
    holder.workoutIndex = this.dataSet.get(position);
  }

  @Override
  public int getItemCount() {
    return this.dataSet.size();
  }

  public void updateDataSet(List<Workout> data) {
    this.dataSet = data;
    notifyDataSetChanged();
  }


  private int getWorkoutIcon(String workoutType) {
    switch (workoutType) {
      case "Interval":
        return R.drawable.inverval_workout_icon;
            case "Cardio":
        return R.drawable.cardio_workout_icon;
      case "Weights":
        return R.drawable.dumbell_icon;
    }

    return 0;
  }
}
