package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.AbstractExercisesAdapter
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.ExercisesAdapter
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.WorkoutContextExercisesAdapter

class ExercisesListFragment : Fragment(),
    AbstractExercisesAdapter.OnItemMenuClickInterface {

    private var mListener: OnFragmentInteractionListener? = null
    private lateinit var recyclerViewerAdapter: AbstractExercisesAdapter

    override val workoutsList: List<Workout>
        get() = mListener?.workoutsList!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val rootView: View = inflater.inflate(
            R.layout.fragment_exercises_list, container, false
        )
        val recyclerView = rootView.findViewById<RecyclerView>(
            R.id.exercise_view_recycle_viewer
        )

        val layoutManager = LinearLayoutManager(rootView.context)
        recyclerView.layoutManager = layoutManager

        if (this.mListener?.activeWorkout == null) {
            this.recyclerViewerAdapter = ExercisesAdapter(context, this)
        } else {
            this.recyclerViewerAdapter = WorkoutContextExercisesAdapter(this)
        }

        recyclerView.adapter = recyclerViewerAdapter

        return rootView
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            mListener = context
        } else {
            throw RuntimeException(
                "${context!!.toString()} must implement OnFragmentInteractionListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        mListener = null
    }

    fun updateDataSet(exercises: List<Exercise>) {
        this.recyclerViewerAdapter.updateDataSet(exercises)
    }

    override fun insertExerciseAssignment(exercise: Exercise, workout: Workout) {
        val exerciseAssignment = WorkoutExerciseAssignment()
        exerciseAssignment.exerciseId = exercise.id
        exerciseAssignment.workoutId = workout.id

        this.mListener?.insertExerciseAssignment(exerciseAssignment)

        Toast.makeText(
            context,
            "${exercise.name} added to ${workout?.name}",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun insertExerciseAssignment(exercise: Exercise) {
        val exerciseAssignment = WorkoutExerciseAssignment()
        exerciseAssignment.exerciseId = exercise.id
        exerciseAssignment.workoutId = this.mListener!!.activeWorkout.id
        this.mListener!!.insertExerciseAssignment(exerciseAssignment)

        Toast.makeText(
            context,
            "${exercise.name} added to ${this.mListener!!.activeWorkout.name}",
            Toast.LENGTH_LONG
        ).show()
    }

    interface OnFragmentInteractionListener {
        val activeWorkout: Workout
        val workoutsList: List<Workout>
        fun insertExerciseAssignment(workoutExerciseAssignment: WorkoutExerciseAssignment)
    }
}