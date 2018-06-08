package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.jds.fitnessjunkiess.getfitapp.Entities.Exercise;
import com.jds.fitnessjunkiess.getfitapp.R;
import java.util.ArrayList;
import java.util.List;

public class ExercisesListAdapter extends RecyclerView.Adapter<ExercisesListViewHolder> {

    private List<Exercise> dataSet;

    public ExercisesListAdapter() {
        this.dataSet = new ArrayList<>();
    }

    @NonNull
    @Override
    public ExercisesListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View exerciseItem = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.view_holder_exercise_list, parent, false);

        return new ExercisesListViewHolder(exerciseItem);
    }

    @Override
    public void onBindViewHolder(@NonNull ExercisesListViewHolder holder, int position) {
        holder.setExerciseName(this.dataSet.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return this.dataSet.size();
    }

    public void swapData(List<Exercise> data){
        this.dataSet = data;
        notifyDataSetChanged();
    }
}
