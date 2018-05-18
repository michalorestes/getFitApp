package com.jds.fitnessjunkiess.getfitapp.Activities.WorkoutsActivity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jds.fitnessjunkiess.getfitapp.R;

class WorkoutListViewHolder extends RecyclerView.ViewHolder {

    public TextView title;
    public TextView subTitle;
    public ImageView icon;
    private View card;

    WorkoutListViewHolder(View card) {
        super(card);
        this.title = card.findViewById(R.id.workout_card_title);
        this.subTitle = card.findViewById(R.id.workout_card_sub_title);
        this.icon = card.findViewById(R.id.icon);
        this.card = card;
        this.card.setOnClickListener((View v) -> {

        });

    }
}

