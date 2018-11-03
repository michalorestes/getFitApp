package com.jds.fitnessjunkiess.getfitapp.features.workout.workoutLogging.adapters

import android.support.v7.widget.RecyclerView
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.pojo.LogSet
import java.lang.NumberFormatException
import kotlin.math.log

class ExerciseSetsAdapter(
    numberOfSets: Int,
    repsTarget: Int = 0
): RecyclerView.Adapter<ExerciseSetsAdapter.ExerciseSetViewHolder>() {

    val dataSet: ArrayList<LogSet> = ArrayList()

    init {
        for (i in 0 until numberOfSets) {
            val e = LogSet(i)
            e.rep = repsTarget
            e.setNo = (i + 1)
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
        holder.weight.setText(this.dataSet[position].weight.toString())
        holder.sets.text = this.dataSet[position].setNo.toString()
    }

    fun updateDataSet(logSet: List<LogSet>) {
        try {
            logSet.forEachIndexed {index, element ->
                this.dataSet[index].rep = element.rep
                this.dataSet[index].weight = element.weight
            }
        } catch (e: IndexOutOfBoundsException) {

        } finally {
            notifyDataSetChanged()
        }
    }

    inner class ExerciseSetViewHolder(view:View): RecyclerView.ViewHolder(view)
    {
        val reps: EditText =  view.findViewById(R.id.reps_log)
        val weight: EditText = view.findViewById(R.id.weight_log)
        val sets: TextView = view.findViewById(R.id.txt_sets_count)

        init {
            reps.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) { return }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { return }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    try {
                        dataSet[adapterPosition].rep = reps.text.toString().toInt()
                    } catch (e: NumberFormatException) {
                        dataSet[adapterPosition].rep = 0
                    }
                }
            })

            weight.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(p0: Editable?) { return }

                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) { return }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    try {
                        dataSet[adapterPosition].weight = weight.text.toString().toFloat()
                    } catch (e: NumberFormatException) {
                        dataSet[adapterPosition].weight = 0f
                    }
                }
            })
        }

    }
}