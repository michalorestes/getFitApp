package com.jds.fitnessjunkiess.getfitapp.Activities.ExercisesView.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Exercise;
import com.jds.fitnessjunkiess.getfitapp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder> {

  private List<Exercise> data;

  public ExercisesAdapter() {
    this.data = new ArrayList<>();
  }

  @NonNull
  @Override
  public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater
          .from(parent.getContext())
          .inflate(R.layout.view_holder_exercise, parent, false);

    return new ExerciseViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
    holder.setExerciseName(this.data.get(position).getName());
  }

  @Override
  public int getItemCount() {
    return this.data.size();
  }

  public void updateDataset(List<Exercise> data) {
    this.data = data;
    notifyDataSetChanged();
  }

  class ExerciseViewHolder extends RecyclerView.ViewHolder {

    private TextView exerciseName;

    public ExerciseViewHolder(View itemView) {
      super(itemView);

      this.exerciseName = itemView.findViewById(R.id.exercise_name_txt);
    }

    public void setExerciseName(String exerciseName) {
      this.exerciseName.setText(exerciseName);
    }
  }
}
