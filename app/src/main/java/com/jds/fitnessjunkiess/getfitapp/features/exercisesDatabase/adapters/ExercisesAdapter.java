package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;
import java.util.List;

public class ExercisesAdapter extends AbstractExercisesAdapter {

  public ExercisesAdapter(OnItemMenuClickInterface onDropDownClickInterface) {
    super(onDropDownClickInterface);
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
    holder.getImageButton().setOnClickListener(v -> {
      PopupMenu popupMenu = new PopupMenu(this.onDropDownClickInterface.getAppContext(), v);
      List<Workout> workouts = this.onDropDownClickInterface.getWorkoutsList();
      for (int i = 0; i < workouts.size(); i++){
        popupMenu.getMenu().add(0, i, i,  workouts.get(i).getName());
      }

      popupMenu.setOnMenuItemClickListener(popupItem -> {
        this.onDropDownClickInterface.insertExerciseAssignment(
            this.data.get(position), workouts.get(popupItem.getItemId())
        );

        return true;
      });

      popupMenu.show();
    });
  }
}
