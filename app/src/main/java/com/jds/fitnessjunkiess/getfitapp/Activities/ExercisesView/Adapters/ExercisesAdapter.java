package com.jds.fitnessjunkiess.getfitapp.Activities.ExercisesView.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.MainActivity;
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.Exercise;
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.ArrayList;
import java.util.List;

public class ExercisesAdapter extends RecyclerView.Adapter<ExercisesAdapter.ExerciseViewHolder> {

  private OnDropDownClickInterface onDropDownClickInterface;

  public interface OnDropDownClickInterface {
    Context getAppContext();
    List<Workout> getWorkoutsList();
  }

  private List<Exercise> data;

  public ExercisesAdapter(OnDropDownClickInterface onDropDownClickInterface) {
    this.data = new ArrayList<>();
    this.onDropDownClickInterface = onDropDownClickInterface;
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
        popupMenu.getMenu().add(0, workouts.get(i).getId(), i,  workouts.get(i).getName());
      }

      popupMenu.setOnMenuItemClickListener(popupItem -> {
        Log.d("------>", "onBindViewHolder: " + popupItem.getItemId() + " " + popupItem.getTitle());
        return true;
      });

      popupMenu.show();

    });
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
    private ImageButton imageButton;

    public ExerciseViewHolder(View itemView) {
      super(itemView);

      this.exerciseName = itemView.findViewById(R.id.exercise_name_txt);
      this.imageButton = itemView.findViewById(R.id.add_to_workout_btn);
    }

    public ImageButton getImageButton() {
      return  this.imageButton;
    }

    public void setExerciseName(String exerciseName) {
      this.exerciseName.setText(exerciseName);
    }
  }
}
