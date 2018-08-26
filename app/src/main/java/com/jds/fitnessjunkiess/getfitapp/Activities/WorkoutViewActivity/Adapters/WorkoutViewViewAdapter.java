package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutViewActivity.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.WorkoutExercise;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.ArrayList;
import java.util.List;

public class WorkoutViewViewAdapter extends RecyclerView.Adapter<WorkoutViewViewAdapter.WorkoutViewViewHolder> {

  private List<WorkoutExercise> dataSet;

  public WorkoutViewViewAdapter() {
    this.dataSet = new ArrayList<>();
  }

  @NonNull
  @Override
  public WorkoutViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.view_holder_workout_exercise, parent, false);

    return new WorkoutViewViewHolder(view);
  }

  @Override
  public void onBindViewHolder(WorkoutViewViewHolder holder, int position) {
    holder.setName(this.dataSet.get(position).getExerciseName());
    holder.setSets(this.dataSet.get(position).getSets());
    holder.setReps(this.dataSet.get(position).getReps());
  }

  @Override
  public int getItemCount() {
    return this.dataSet.size();
  }

  public void updateDataSet(List<WorkoutExercise> data) {
    this.dataSet = data;
    notifyDataSetChanged();
  }

  class WorkoutViewViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView sets;
    private TextView reps;
    private ImageView icon;
    private ImageView reorderHandler;

    WorkoutViewViewHolder(View view) {
      super(view);
      this.name = view.findViewById(R.id.exercise_name);
      this.sets = view.findViewById(R.id.sets);
      this.reps = view.findViewById(R.id.reps);
    }

    public void setSets(int sets) {
      this.sets.setText("Sets: " + sets);
    }

    public void setReps(int reps) {
      this.reps.setText("Reps: " + reps);
    }

    public void setName(String name) {
      this.name.setText(name);
    }
  }
}
