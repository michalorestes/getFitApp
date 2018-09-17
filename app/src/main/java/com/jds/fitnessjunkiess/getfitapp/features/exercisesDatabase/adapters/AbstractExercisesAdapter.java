package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractExercisesAdapter extends RecyclerView.Adapter<AbstractExercisesAdapter.ExerciseViewHolder> {
  public interface OnItemMenuClickInterface {
    Context getAppContext();
    List<Workout> getWorkoutsList();
    void insertExerciseAssignment(Exercise exercise, Workout workout);
    void insertExerciseAssignment(Exercise exercise);
  }

  protected List<Exercise> data;
  protected ExercisesAdapter.OnItemMenuClickInterface onDropDownClickInterface;

  public AbstractExercisesAdapter(ExercisesAdapter.OnItemMenuClickInterface onDropDownClickInterface) {
    this.data = new ArrayList<>();
    this.onDropDownClickInterface = onDropDownClickInterface;
  }

  @NonNull
  @Override
  public abstract ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType);

  @Override
  public abstract void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position);

  @Override
  public int getItemCount() {
    return this.data.size();
  }

  public void updateDataSet(List<Exercise> data) {
    this.data = data;
    notifyDataSetChanged();
  }

  class ExerciseViewHolder extends RecyclerView.ViewHolder {

    private TextView exerciseName;
    private ImageButton imageButton;

    ExerciseViewHolder(View itemView) {
      super(itemView);

      this.exerciseName = itemView.findViewById(R.id.exercise_name_txt);
      this.imageButton = itemView.findViewById(R.id.add_to_workout_btn);
    }

    ImageButton getImageButton() {
      return  this.imageButton;
    }

    void setExerciseName(String exerciseName) {
      this.exerciseName.setText(exerciseName);
    }
  }
}
