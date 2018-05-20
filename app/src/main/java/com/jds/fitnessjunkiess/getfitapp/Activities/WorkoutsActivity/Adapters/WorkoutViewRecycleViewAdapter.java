package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.Entities.WorkoutExercise;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.List;

public class WorkoutViewRecycleViewAdapter
        extends RecyclerView.Adapter<WorkoutViewViewHolder> {

    private List<WorkoutExercise> exercises;

    public WorkoutViewRecycleViewAdapter(List<WorkoutExercise> exercises) {
        this.exercises = exercises;
    }

    @NonNull
    @Override
    public WorkoutViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View workoutCard = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.exercise_card_layout, parent, false);

        return new WorkoutViewViewHolder(workoutCard);
    }

    @Override
    public void onBindViewHolder(WorkoutViewViewHolder holder, int position) {
        holder.exerciseName.setText(this.exercises.get(position).getName());
        holder.sets.setText(String.valueOf(this.exercises.get(position).getSets() + " sets"));
        holder.reps.setText(String.valueOf(this.exercises.get(position).getReps() + " reps"));
        holder.icon.setImageResource(R.drawable.body_weight_exercise_type);

    }

    @Override
    public int getItemCount() {
        return this.exercises.size();
    }

    public void swapData(List<WorkoutExercise> exercises){
        this.exercises = exercises;
        notifyDataSetChanged();
    }
}
