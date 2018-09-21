package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.DialogFragment
import android.support.v7.widget.Toolbar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.pojo.MuscleGroupKeys
import com.jds.fitnessjunkiess.getfitapp.data.pojo.MuscleGroups

class AddCustomExerciseDialog : DialogFragment() {

    private lateinit var exerciseName: TextInputEditText
    private var mListener: OnFragmentInteractionInterface? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView: View = inflater.inflate(R.layout.fragment_dialog_add_custom_exercise, container, false)
        this.exerciseName = rootView.findViewById(R.id.exercise_name)

        val toolbar: Toolbar = rootView.findViewById(R.id.toolbar)
        toolbar.title = "Add custom exercise"

        toolbar.setNavigationIcon(R.drawable.baseline_close_white_24dp)
        toolbar.inflateMenu(R.menu.dialog_workout_details_menu)
        toolbar.setNavigationOnClickListener { v -> dismiss() }

        toolbar.setOnMenuItemClickListener { item ->
            if (item.itemId == R.id.save) {
                this.saveWorkout()
            }
            true
        }

        setHasOptionsMenu(true)
        return rootView
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(context, R.style.AppTheme)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)

        return dialog
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionInterface) {
            this.mListener = context
        } else {
            throw Exception("Context must implement OnFragmentInteraction")
        }
    }

    private fun saveWorkout() {
        val exercise = Exercise()
        exercise.name = this.exerciseName.text.toString()
        exercise.setMuscleGroupsByKey(MuscleGroupKeys.PRIMARY, MuscleGroups.ALL)
        this.mListener?.saveCustomExercise(exercise)
        this.dismiss()
    }

    interface OnFragmentInteractionInterface {
        fun saveCustomExercise(exercise: Exercise)
    }
}