package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity.WorkoutsListFragment;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.R;
import java.util.List;

public class WorkoutListRecycleViewAdapter
        extends RecyclerView.Adapter<WorkoutListViewHolder> {

    private List<Workout> dataSet;
    private WorkoutsListFragment.onWorkoutInteractionInterface onWorkoutInteractionInterface;

    public WorkoutListRecycleViewAdapter(
            List<Workout> dataSet,
            WorkoutsListFragment.onWorkoutInteractionInterface onWorkoutInteractionInterface) {
        this.dataSet = dataSet;
        this.onWorkoutInteractionInterface = onWorkoutInteractionInterface;
    }

    @NonNull
    @Override
    public WorkoutListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View workoutCard = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.workout_card_layout, parent, false);

        return new WorkoutListViewHolder(workoutCard, this.onWorkoutInteractionInterface);
    }

    @Override
    public void onBindViewHolder(WorkoutListViewHolder holder, int position) {
        holder.title.setText(this.dataSet.get(position).getName());
        holder.subTitle.setText(
                String.valueOf(this.dataSet.get(position).getExercises().size()) + " exercises"
        );
        holder.icon.setImageResource(R.drawable.dumbell_icon);
        holder.workoutIndex = position;
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
