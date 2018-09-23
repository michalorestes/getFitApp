package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout

abstract class AbstractExercisesAdapter(
    val onItemMenuClickInterface: OnItemMenuClickInterface
) : RecyclerView.Adapter<AbstractExercisesAdapter.ViewHolder>() {

    val data: ArrayList<Exercise> = arrayListOf()

    abstract override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
    abstract override fun onBindViewHolder(holder: ViewHolder, position: Int)

    override fun getItemCount(): Int {
        return this.data.size
    }

    fun updateDataSet(data: List<Exercise>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    class ViewHolder(item: View) : RecyclerView.ViewHolder(item) {
        var exerciseName: TextView = item.findViewById(R.id.exercise_name_txt)
        var imageButton: ImageButton = item.findViewById(R.id.add_to_workout_btn)
    }

    interface OnItemMenuClickInterface {
        fun getWorkoutsList(): List<Workout>?
        fun insertExerciseAssignment(exercise: Exercise, workout: Workout?)
        fun insertExerciseAssignment(exercise: Exercise)
    }
}
