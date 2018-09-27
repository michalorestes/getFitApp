package com.jds.fitnessjunkiess.getfitapp.features.workout.adapters;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.ArrayList;
import java.util.List;

import androidx.navigation.Navigation;

public class WorkoutListRecycleViewAdapter extends RecyclerView.Adapter<WorkoutListRecycleViewAdapter.WorkoutListViewHolder> {

  private List<Workout> dataSet;

  public WorkoutListRecycleViewAdapter() {
    this.dataSet = new ArrayList<>();
  }

  @NonNull
  @Override
  public WorkoutListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View workoutCard =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.workout_card_layout, parent, false);

    return new WorkoutListViewHolder(workoutCard);
  }

  @Override
  public void onBindViewHolder(WorkoutListViewHolder holder, int position) {
    holder.title.setText(this.dataSet.get(position).getName());
    holder.icon.setImageResource(this.getWorkoutIcon(this.dataSet.get(position).getType()));
    holder.workout = this.dataSet.get(position);
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

  class WorkoutListViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public ImageView icon;
    public Workout workout;

    WorkoutListViewHolder(View card) {
      super(card);
      this.title = card.findViewById(R.id.workout_card_title);
      this.icon = card.findViewById(R.id.icon);

      card.setOnClickListener(
          (View v) -> {
            Log.i("view", "" + this.workout);
            if (workout != null) {
              Bundle bundle = new Bundle();
              bundle.putParcelable("workoutData", workout);
              Navigation.findNavController(v).navigate(R.id.workoutViewFragment, bundle);
            }
          });
    }
  }

}
