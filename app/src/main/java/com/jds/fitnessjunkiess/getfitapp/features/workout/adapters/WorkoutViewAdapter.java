package com.jds.fitnessjunkiess.getfitapp.features.workout.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseRelationship;
import com.jds.fitnessjunkiess.getfitapp.features.workout.helpers.ItemTouchCallback;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkoutViewAdapter
    extends RecyclerView.Adapter<WorkoutViewAdapter.WorkoutViewViewHolder>
    implements ItemTouchCallback.ItemTouchHelperAdapter
{
  public interface OnAdapterInteractionInterface {
    void onEditExercise(ExerciseRelationship workoutExercise);
    void onRemoveExercise(ExerciseRelationship exerciseRelationship);
    void onTrackExercise(View view, ExerciseRelationship exerciseRelationship);
  }

  private List<ExerciseRelationship> dataSet;
  private OnAdapterInteractionInterface onAdapterInteractionInterface;
  private Context context;

  public WorkoutViewAdapter(Context context, OnAdapterInteractionInterface onAdapterInteractionInterface) {
    this.context = context;
    this.dataSet = new ArrayList<>();
    this.onAdapterInteractionInterface = onAdapterInteractionInterface;
  }

  public List<ExerciseRelationship> getDataSet() {
    return this.dataSet;
  }

  @NonNull
  @Override
  public WorkoutViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.view_holder_workout_view_exercise_item, parent, false);

    return new WorkoutViewViewHolder(this.context, view, this.onAdapterInteractionInterface);
  }

  @Override
  public void onBindViewHolder(@NotNull WorkoutViewViewHolder holder, int position) {
    holder.setName(this.dataSet.get(position).getExercise().getName());
    holder.setSets(this.dataSet.get(position).getRelationship().getSets());
    holder.setReps(this.dataSet.get(position).getRelationship().getReps());
  }

  @Override
  public int getItemCount() {
    return this.dataSet.size();
  }

  public void updateDataSet(List<ExerciseRelationship> data) {
    this.dataSet = data;
    notifyDataSetChanged();
  }

  @Override
  public void onItemMove(int fromPosition, int toPosition) {
    this.dataSet.get(fromPosition).getRelationship().setPosition(toPosition);
    this.dataSet.get(toPosition).getRelationship().setPosition(fromPosition);

    if (fromPosition < toPosition) { //item moved down
      for (int i = fromPosition; i < toPosition; i++) {
        Collections.swap(this.dataSet, i, i + 1);
      }
    } else { //item moved up
      for (int i = fromPosition; i > toPosition; i--) {
        Collections.swap(this.dataSet, i, i - 1);
      }
    }
    notifyItemMoved(fromPosition, toPosition);
  }

  @Override
  public void onItemDismiss(int position) {
    this.dataSet.remove(position);
    notifyItemRemoved(position);
  }

  class WorkoutViewViewHolder extends RecyclerView.ViewHolder implements PopupMenu.OnMenuItemClickListener {

    private TextView name;
    private TextView sets;
    private TextView reps;
    private ImageView icon;

    private OnAdapterInteractionInterface onAdapterInteractionInterface;

    WorkoutViewViewHolder(Context context, View view, OnAdapterInteractionInterface onAdapterInteractionInterface) {
      super(view);
      this.onAdapterInteractionInterface = onAdapterInteractionInterface;
      this.name = view.findViewById(R.id.exercise_name);
      this.sets = view.findViewById(R.id.sets);
      this.reps = view.findViewById(R.id.reps);
      TextView optionsMenu = view.findViewById(R.id.options_menu);
      optionsMenu.setOnClickListener(
          v -> {
            PopupMenu popupMenu = new PopupMenu(context, optionsMenu);
            popupMenu.inflate(R.menu.popup_menu_workout_view_exercise_menu);
            popupMenu.setOnMenuItemClickListener(this);
            popupMenu.show();
          }
      );
      view.setOnClickListener(
          v -> onAdapterInteractionInterface.onTrackExercise(v, dataSet.get(getAdapterPosition()))
      );
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

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
      switch (menuItem.getItemId()) {
        case R.id.workout_view_popup_menu_edit:
          this.onAdapterInteractionInterface.onEditExercise(dataSet.get(getAdapterPosition()));
          break;
        case R.id.workout_view_popup_menu_delete:
          Log.d("-->", "onMenuItemClick: delete");
          this.onAdapterInteractionInterface.onRemoveExercise(dataSet.get(getAdapterPosition()));
          break;
        case R.id.workout_view_popup_menu_track:
          break;
      }

      return true;
    }
  }
}
