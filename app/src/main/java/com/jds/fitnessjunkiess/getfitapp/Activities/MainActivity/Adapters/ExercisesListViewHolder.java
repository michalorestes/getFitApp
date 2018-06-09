package com.jds.fitnessjunkiess.getfitapp.Activities.MainActivity.Adapters;

import android.content.Context;
import android.support.annotation.DrawableRes;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.R;

public class ExercisesListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private TextView exerciseName;
    private ImageView icon;
    private Button button;
    private Context context;
    private int exerciseIndex;

    public ExercisesListViewHolder(View itemView, Context context) {
        super(itemView);
        this.exerciseName = itemView.findViewById(R.id.exercise_name);
        this.icon = itemView.findViewById(R.id.exercise_list_icon);
        this.button = itemView.findViewById(R.id.exercise_list_options_button);
        this.context = context;
        itemView.setOnClickListener(this);
        this.button.setOnClickListener(this);
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName.setText(exerciseName);
    }

    public void setExerciseIcon(int drawableId) {
        this.icon.setImageResource(drawableId);
    }

    public void setExerciseIndex(int index) {
        this.exerciseIndex = index;
    }

    @Override
    public void onClick(View v) {
        PopupMenu popupMenu = new PopupMenu(this.context, this.button);
        popupMenu.inflate(R.menu.menu_add_exercise_to_workout);
        popupMenu.show();
    }
}
