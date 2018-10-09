package com.jds.fitnessjunkiess.getfitapp.features.workout.adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.jds.fitnessjunkiess.getfitapp.R;
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseRelationship;
import com.jds.fitnessjunkiess.getfitapp.features.workout.helpers.ItemTouchCallback;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class WorkoutViewAdapter extends RecyclerView.Adapter<WorkoutViewAdapter.WorkoutViewViewHolder> implements ItemTouchCallback.ItemTouchHelperAdapter {

  @Override
  public void onItemMove(int fromPosition, int toPosition) {
    if (fromPosition < toPosition) {
      for (int i = fromPosition; i < toPosition; i++) {
        Collections.swap(this.dataSet, i, i + 1);
      }
    } else {
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

  public interface OnItemClickListener {
    void onItemClick(View view, ExerciseRelationship workoutExercise);
  }

  private List<ExerciseRelationship> dataSet;
  private OnItemClickListener onItemClickListener;

  public WorkoutViewAdapter(OnItemClickListener onItemClickListener) {
    this.dataSet = new ArrayList<>();
    this.onItemClickListener = onItemClickListener;
  }

  @NonNull
  @Override
  public WorkoutViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view =
        LayoutInflater.from(parent.getContext())
            .inflate(R.layout.view_holder_workout_exercise, parent, false);

    return new WorkoutViewViewHolder(view, this.onItemClickListener);
  }

  @Override
  public void onBindViewHolder(WorkoutViewViewHolder holder, int position) {
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

  class WorkoutViewViewHolder extends RecyclerView.ViewHolder {

    private TextView name;
    private TextView sets;
    private TextView reps;
    private ImageView icon;
    private ImageView reorderHandler;

    WorkoutViewViewHolder(View view, OnItemClickListener onItemClickListener) {
      super(view);
      this.name = view.findViewById(R.id.exercise_name);
      this.sets = view.findViewById(R.id.sets);
      this.reps = view.findViewById(R.id.reps);
      view.setOnClickListener(
          v -> onItemClickListener.onItemClick(v, dataSet.get(getAdapterPosition()))
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
  }
}
