package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.R;

public class ExercisesListViewHolder extends RecyclerView.ViewHolder {

    private TextView exerciseName;

    public ExercisesListViewHolder(View itemView) {
        super(itemView);
        this.exerciseName = itemView.findViewById(R.id.exercise_name);
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName.setText(exerciseName);
    }


}
