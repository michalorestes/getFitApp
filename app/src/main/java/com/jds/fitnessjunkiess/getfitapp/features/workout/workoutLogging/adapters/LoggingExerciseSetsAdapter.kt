package com.jds.fitnessjunkiess.getfitapp.features.workout.workoutLogging.adapters

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.pojo.LogSet

class ExerciseSetsAdapter(
    numberOfSets: Int,
    repsTarget: Int = 0
): RecyclerView.Adapter<ExerciseSetsAdapter.ExerciseSetViewHolder>() {

    private val dataSet: ArrayList<LogSet> = ArrayList()

    init {
        for (i in 0 until numberOfSets) {
            val e = LogSet(i)
            e.rep = repsTarget
            this.dataSet.add(e)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExerciseSetViewHolder {
        val workoutCard = LayoutInflater.from(parent.context)
            .inflate(R.layout.view_holder_single_exercise_loggin, parent, false)

        return ExerciseSetViewHolder(workoutCard)
    }

    override fun getItemCount(): Int {
        return this.dataSet.size
    }

    override fun onBindViewHolder(holder: ExerciseSetViewHolder, position: Int) {
        holder.reps.setText(this.dataSet[position].rep.toString())
    }

    class ExerciseSetViewHolder(view:View): RecyclerView.ViewHolder(view) {
        val reps: EditText =  view.findViewById(R.id.reps_log)
    }
}