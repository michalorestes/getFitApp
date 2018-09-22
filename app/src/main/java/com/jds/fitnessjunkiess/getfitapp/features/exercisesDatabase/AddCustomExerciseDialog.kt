package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v4.app.DialogFragment
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.RadioGroup
import android.widget.Spinner
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.compoundViews.customCheckbox.CustomCheckbox
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseTypes
import com.jds.fitnessjunkiess.getfitapp.data.pojo.MuscleGroupKeys
import com.jds.fitnessjunkiess.getfitapp.data.pojo.MuscleGroups

class AddCustomExerciseDialog : DialogFragment() {

    private lateinit var exerciseName: TextInputEditText
    private var mListener: OnFragmentInteractionInterface? = null
    private lateinit var exerciseTypesRadioGroup: RadioGroup
    private lateinit var primaryMuscleGroupSpinner: Spinner

    private lateinit var absCbx: CustomCheckbox
    private lateinit var bicepsCbx: CustomCheckbox
    private lateinit var backCbx: CustomCheckbox
    private lateinit var calvesCbx: CustomCheckbox
    private lateinit var tricepsCbx: CustomCheckbox
    private lateinit var chestCbx: CustomCheckbox
    private lateinit var shouldersCbx: CustomCheckbox
    private lateinit var forearmsCbx: CustomCheckbox
    private lateinit var quadricepsCbx: CustomCheckbox

    //TODO: currently this dialog only accepts basic info for a exercise
    //TODO: implement extra info (e.g. description, instructions etc)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val rootView: View =
            inflater.inflate(R.layout.fragment_dialog_add_custom_exercise, container, false)
        this.exerciseName = rootView.findViewById(R.id.exercise_name)
        this.exerciseTypesRadioGroup = rootView.findViewById(R.id.exercise_type_radio_group)
        this.primaryMuscleGroupSpinner = rootView.findViewById(R.id.primary_muscle_groups_spinner)
        this.absCbx = rootView.findViewById(R.id.core_muscle_group_checkbox)
        this.bicepsCbx = rootView.findViewById(R.id.biceps_muscle_group_checkbox)
        this.backCbx = rootView.findViewById(R.id.back_muscle_group_checkbox)
        this.calvesCbx = rootView.findViewById(R.id.calves_muscle_group_checkbox)
        this.tricepsCbx = rootView.findViewById(R.id.triceps_muscle_group_checkbox)
        this.chestCbx = rootView.findViewById(R.id.chest_muscle_group_checkbox)
        this.shouldersCbx = rootView.findViewById(R.id.shoulders_muscle_group_checkbox)
        this.forearmsCbx = rootView.findViewById(R.id.forearms_muscle_group_checkbox)
        this.quadricepsCbx = rootView.findViewById(R.id.quads_muscle_group_checkbox)

        val toolbar: Toolbar = rootView.findViewById(R.id.toolbar)
        toolbar.title = "Add custom exercise"

        toolbar.setNavigationIcon(R.drawable.baseline_close_white_24dp)
        toolbar.inflateMenu(R.menu.dialog_workout_details_menu)
        toolbar.setNavigationOnClickListener { _ -> dismiss() }

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
        this.mListener?.saveCustomExercise(this.collectInformationFromUi())
        this.dismiss()
    }

    private fun collectInformationFromUi(): Exercise {
        val exercise = Exercise()
        exercise.name = this.exerciseName.text.toString()
        exercise.type = this.getSelectedExerciseType()
        exercise.isCustom = true
        exercise.setMuscleGroupsByKey(MuscleGroupKeys.PRIMARY, this.getPrimaryMuscleGroup())
        this.getOtherMuscleGroups().forEach { muscleGroup ->
            exercise.setMuscleGroupsByKey(MuscleGroupKeys.OTHER, muscleGroup)
        }
        exercise.userId = 10 //todo; needs to use real username

        return exercise
    }

    private fun getSelectedExerciseType(): String {
        val selectedTypeId = this.exerciseTypesRadioGroup.checkedRadioButtonId

        return when (selectedTypeId) {
            R.id.body_weight_radio_button -> ExerciseTypes.BODY_WEIGHT
            R.id.cardio_radio_button -> ExerciseTypes.CARDIO
            R.id.weights_radio_button -> ExerciseTypes.WEIGHTS
            else -> {
                ExerciseTypes.ALL
            }
        }
    }

    private fun getPrimaryMuscleGroup(): String {
        return when (this.primaryMuscleGroupSpinner.selectedItemId.toInt()) {
            0 -> MuscleGroups.CORE
            1 -> MuscleGroups.BICEPS
            2 -> MuscleGroups.BACK
            3 -> MuscleGroups.CALVES
            4 -> MuscleGroups.TRICEPS
            5 -> MuscleGroups.CHEST
            6 -> MuscleGroups.SHOULDERS
            7 -> MuscleGroups.FOREARMS
            8 -> MuscleGroups.QUADRICEPS
            else -> MuscleGroups.ALL
        }
    }

    private fun getOtherMuscleGroups(): ArrayList<String> {
        val muscleGroups: ArrayList<String> = arrayListOf()

        if (this.absCbx.isChecked) muscleGroups.add(MuscleGroups.CORE)
        if (this.bicepsCbx.isChecked) muscleGroups.add(MuscleGroups.BICEPS)
        if (this.backCbx.isChecked) muscleGroups.add(MuscleGroups.BACK)
        if (this.calvesCbx.isChecked) muscleGroups.add(MuscleGroups.CALVES)
        if (this.tricepsCbx.isChecked) muscleGroups.add(MuscleGroups.TRICEPS)
        if (this.chestCbx.isChecked) muscleGroups.add(MuscleGroups.CHEST)
        if (this.shouldersCbx.isChecked) muscleGroups.add(MuscleGroups.SHOULDERS)
        if (this.forearmsCbx.isChecked) muscleGroups.add(MuscleGroups.FOREARMS)
        if (this.quadricepsCbx.isChecked) muscleGroups.add(MuscleGroups.QUADRICEPS)

        return muscleGroups
    }

    interface OnFragmentInteractionInterface {
        fun saveCustomExercise(exercise: Exercise)
    }
}