package com.jds.fitnessjunkiess.getfitapp.features.workout.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListRecycleViewAdapter extends RecyclerView.Adapter<WorkoutListViewHolder> {

  private List<Workout> dataSet;
  private WorkoutListViewHolder.OnSelectedInterface onSelectedInterface;

  public WorkoutListRecycleViewAdapter(
      WorkoutListViewHolder.OnSelectedInterface onSelectedInterface) {
    this.dataSet = new ArrayList<>();
    this.onSelectedInterface = onSelectedInterface;
  }

  @NonNull
  @Override
  public WorkoutListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View workoutCard =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.workout_card_layout, parent, false);

    return new WorkoutListViewHolder(workoutCard, this.onSelectedInterface);
  }

  @Override
  public void onBindViewHolder(WorkoutListViewHolder holder, int position) {
    holder.title.setText(this.dataSet.get(position).getName());
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
