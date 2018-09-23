package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupMenu
import com.jds.fitnessjunkiess.getfitapp.R

class ExercisesAdapterKotlin(
    val context: Context,
    onItemMenuClickInterface: OnItemMenuClickInterface
) : AbstractExercisesAdapter(onItemMenuClickInterface) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.view_holder_exercise, parent, false)

        return AbstractExercisesAdapter.ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.exerciseName.text = this.data[position].name
        holder.imageButton.setOnClickListener { v ->
            val popupMenu = PopupMenu(this.context, v)
            this.onItemMenuClickInterface.getWorkoutsList()?.forEachIndexed { i, workout ->
                popupMenu.menu.add(0, i, i, workout.name)
            }

            popupMenu.setOnMenuItemClickListener { popupItem ->
                this.onItemMenuClickInterface.insertExerciseAssignment(
                    this.data[position],
                    this.onItemMenuClickInterface.getWorkoutsList()?.get(popupItem.itemId)
                )
                true
            }
            popupMenu.show()
        }
    }
}
