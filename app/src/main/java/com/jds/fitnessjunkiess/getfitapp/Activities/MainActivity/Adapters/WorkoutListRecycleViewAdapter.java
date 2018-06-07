package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Workouts.WorkoutsListInterface;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;

import java.util.ArrayList;
import java.util.List;

public class WorkoutListRecycleViewAdapter
        extends RecyclerView.Adapter<WorkoutListViewHolder> {

    private List<Workout> dataSet;
    private WorkoutsListInterface workoutsListInterface;

    public WorkoutListRecycleViewAdapter(WorkoutsListInterface workoutsListInterface) {
        this.dataSet = new ArrayList<>();
        this.workoutsListInterface = workoutsListInterface;
    }

    @NonNull
    @Override
    public WorkoutListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View workoutCard = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_card_layout, parent, false);

        return new WorkoutListViewHolder(workoutCard, this.workoutsListInterface);
    }

    @Override
    public void onBindViewHolder(WorkoutListViewHolder holder, int position) {
        holder.title.setText(this.dataSet.get(position).getName());
        holder.subTitle.setText(
                String.valueOf(this.dataSet.get(position).getExercises().size()) + " exercises"
        );
        holder.icon.setImageResource(R.drawable.dumbell_icon);
        holder.workoutIndex = this.dataSet.get(position).getId();
    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

    public void swapData(List<Workout> data){
        this.dataSet = data;
        notifyDataSetChanged();
    }
}
