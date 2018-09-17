package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.jds.fitnessjunkiess.getfitapp.R;

public class WorkoutContextExercisesAdapter extends AbstractExercisesAdapter {
  public WorkoutContextExercisesAdapter(OnItemMenuClickInterface onDropDownClickInterface) {
    super(onDropDownClickInterface);
  }

  @NonNull
  @Override
  public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater
            .from(parent.getContext())
            .inflate(R.layout.view_holder_exercise_workout_context, parent, false);

    return new ExerciseViewHolder(view);
  }

  @Override
  public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
    holder.setExerciseName(this.data.get(position).getName());
    holder.getImageButton().setOnClickListener(v -> {
      this.onDropDownClickInterface.insertExerciseAssignment(this.data.get(position));
    });
  }
}
