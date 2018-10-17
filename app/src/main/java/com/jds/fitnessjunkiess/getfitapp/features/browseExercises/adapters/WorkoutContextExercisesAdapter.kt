package com.jds.fitnessjunkiess.getfitapp.features.browseExercises.adapters

import android.view.LayoutInflater
import android.view.ViewGroup

import com.jds.fitnessjunkiess.getfitapp.R

class WorkoutContextExercisesAdapter(onItemMenuClickInterface: OnItemMenuClickInterface) :
    AbstractExercisesAdapter(onItemMenuClickInterface) {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AbstractExercisesAdapter.ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_holder_exercise_workout_context, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: AbstractExercisesAdapter.ViewHolder, position: Int) {
        holder.exerciseName.text = this.data[position].name
        holder.imageButton.setOnClickListener { _ ->
            this.onItemMenuClickInterface
                .insertExerciseAssignment(this.data[position])
        }
    }
}
