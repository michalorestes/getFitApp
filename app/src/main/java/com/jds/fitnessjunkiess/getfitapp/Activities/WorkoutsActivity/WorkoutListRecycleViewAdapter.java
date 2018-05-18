package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.Entities.WorkoutExercise;
import com.jds.fitnessjunkiess.getfitapp.R;
import java.util.List;

public class WorkoutListRecycleViewAdapter
        extends RecyclerView.Adapter<WorkoutListViewHolder> {

    private List<Workout> dataSet;

    WorkoutListRecycleViewAdapter(List<Workout> dataSet) {
        this.dataSet = dataSet;
    }

    @NonNull
    @Override
    public WorkoutListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View workoutCard = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_card_layout, parent, false);

        return new WorkoutListViewHolder(workoutCard);
    }

    @Override
    public void onBindViewHolder(WorkoutListViewHolder holder, int position) {
        holder.title.setText(this.dataSet.get(position).getName());
        holder.subTitle.setText(String.valueOf(this.dataSet.get(position).getExercises().size()));

    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

    public void swapData(List<Workout> dataSet){
        if (this.dataSet != null) {
            this.dataSet.clear();
            this.dataSet.addAll(dataSet);
        }
        else {
            this.dataSet = dataSet;
        }
        notifyDataSetChanged();
    }
}
