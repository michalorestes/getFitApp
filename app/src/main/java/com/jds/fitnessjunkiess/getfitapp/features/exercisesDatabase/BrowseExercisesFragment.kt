package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Toast
import androidx.navigation.findNavController
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseViewModel
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutExerciseAssignmentViewModel
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutsViewModel
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.AbstractExercisesAdapter
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.ExercisesAdapter
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.WorkoutContextExercisesAdapter
import com.jds.fitnessjunkiess.getfitapp.interfaces.OnFragmentActionBarInteractionInterface
import kotlinx.android.synthetic.main.fragment_browse_exercises_list.*

class BrowseExercisesFragment : Fragment(),
    AbstractExercisesAdapter.OnItemMenuClickInterface,
    View.OnClickListener
{
    private lateinit var recyclerViewerAdapter: AbstractExercisesAdapter
    private var selectedWorkout: Workout? = null
    private var workoutsList: List<Workout>? = null
    private lateinit var workoutExerciseAssignment: WorkoutExerciseAssignmentViewModel
    private lateinit var exerciseViewModel: ExerciseViewModel
    private lateinit var workoutsViewModel: WorkoutsViewModel
    private lateinit var actionBarInteractionInterface: OnFragmentActionBarInteractionInterface

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentActionBarInteractionInterface) {
            this.actionBarInteractionInterface = context
        } else {
            throw Exception("Context must be of type: OnFragmentActionBarInteractionInterface")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.selectedWorkout = arguments?.getParcelable("selectedWorkout")
        this.workoutExerciseAssignment = ViewModelProviders
            .of(this)
            .get(WorkoutExerciseAssignmentViewModel::class.java)
        this.exerciseViewModel =  ViewModelProviders
            .of(this)
            .get(ExerciseViewModel::class.java)
        this.workoutsViewModel = ViewModelProviders
            .of(activity!!)
            .get(WorkoutsViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        this.actionBarInteractionInterface.setToolbarTitle("Exercises")

        return inflater
            .inflate(R.layout.fragment_browse_exercises_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_add_custom_exercise.setOnClickListener(this)

        val layoutManager = LinearLayoutManager(view.context)
        recycler_view.layoutManager = layoutManager

        if (this.selectedWorkout == null) {
            this.recyclerViewerAdapter = ExercisesAdapter(context, this)
        } else {
            this.recyclerViewerAdapter = WorkoutContextExercisesAdapter(this)
        }

        recycler_view.adapter = this.recyclerViewerAdapter
    }

    override fun onStart() {
        super.onStart()
        this.exerciseViewModel.select().observe(this, Observer {
            if (it != null) {
                this.recyclerViewerAdapter.updateDataSet(it)
            }
        })

        if(this.selectedWorkout == null) {
            this.workoutsViewModel.data.observe(this, Observer {
                if (it != null) {
                    this.workoutsList = it
                }
            })
        }
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_add_custom_exercise -> AddCustomExerciseDialog().show(
                fragmentManager, "addCustomExerciseDialog"
            )
        }
    }

    override fun insertExerciseAssignment(exercise: Exercise, workout: Workout) {
        val exerciseAssignment = WorkoutExerciseAssignment()
        exerciseAssignment.exerciseId = exercise.id
        exerciseAssignment.workoutId = workout.id

        this.workoutExerciseAssignment.insert(exerciseAssignment)

        Toast.makeText(context,"${exercise.name} added to ${workout.name}",Toast.LENGTH_LONG)
            .show()
    }

    override fun insertExerciseAssignment(exercise: Exercise) {
        val exerciseAssignment = WorkoutExerciseAssignment()
        exerciseAssignment.exerciseId = exercise.id
        exerciseAssignment.workoutId = this.selectedWorkout!!.id
        this.workoutExerciseAssignment.insert(exerciseAssignment)

        Toast.makeText(
            context,
            "${exercise.name} added to ${this.selectedWorkout!!.name}",Toast.LENGTH_LONG
        ).show()
    }

    override fun getWorkoutsList(): List<Workout>? {
        return this.workoutsList
    }
}
