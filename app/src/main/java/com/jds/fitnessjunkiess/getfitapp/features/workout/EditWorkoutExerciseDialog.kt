package com.jds.fitnessjunkiess.getfitapp.features.workout

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.content.DialogInterface
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.widget.EditText
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseAssignmentViewModel
import kotlinx.android.synthetic.main.dialog_edit_workout_exercise.view.*

class EditWorkoutExerciseDialog : DialogFragment(), DialogInterface.OnClickListener
{
    private lateinit var workoutExerciseAssignment: ExerciseAssignment
    private lateinit var exerciseAssignmentViewModel: ExerciseAssignmentViewModel
    private lateinit var reps: EditText
    private lateinit var sets: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.workoutExerciseAssignment =
                arguments!!["workoutExerciseAssignment"] as ExerciseAssignment
        this.exerciseAssignmentViewModel =
                ViewModelProviders.of(activity!!).get(ExerciseAssignmentViewModel::class.java)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder = AlertDialog.Builder(activity!!)
        val inflater = activity!!.layoutInflater
        val rootView = inflater.inflate(R.layout.dialog_edit_workout_exercise, null)

        rootView.txt_sets.setText(this.workoutExerciseAssignment.sets.toString())
        rootView.txt_reps.setText(this.workoutExerciseAssignment.reps.toString())
        this.sets = rootView.txt_sets
        this.reps = rootView.txt_reps
        builder.setView(rootView)
            .setPositiveButton("OK", this)
            .setNegativeButton("Cancel", this)

        return builder.create()
    }

    override fun onClick(dialog: DialogInterface?, which: Int) {
        if (which == -1) {
            this.workoutExerciseAssignment.sets = this.sets.text.toString().toInt()
            this.workoutExerciseAssignment.reps = this.reps.text.toString().toInt()
            this.exerciseAssignmentViewModel.update(this.workoutExerciseAssignment)
        }
    }
}