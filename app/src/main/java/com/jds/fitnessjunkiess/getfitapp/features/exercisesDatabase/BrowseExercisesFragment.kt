package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import android.widget.Toast
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExercisesFilters
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseViewModel
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseAssignmentViewModel
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutsViewModel
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.AbstractExercisesAdapter
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.adapters.WorkoutContextExercisesAdapter
import com.jds.fitnessjunkiess.getfitapp.interfaces.OnFragmentActionBarInteractionInterface
import kotlinx.android.synthetic.main.fragment_browse_exercises_list.*
import kotlinx.coroutines.experimental.launch

class BrowseExercisesFragment : Fragment(),
    AbstractExercisesAdapter.OnItemMenuClickInterface,
    View.OnClickListener
{
    private var selectedWorkout: Workout? = null
    private lateinit var exercisesFilters: ExercisesFilters

    private lateinit var exerciseAssignmentViewModel: ExerciseAssignmentViewModel
    private lateinit var exerciseViewModel: ExerciseViewModel
    private lateinit var workoutsViewModel: WorkoutsViewModel

    private lateinit var recyclerViewerAdapter: AbstractExercisesAdapter
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
        this.exercisesFilters = this.getExerciseFilters()

        this.exerciseAssignmentViewModel = ViewModelProviders
            .of(this)
            .get(ExerciseAssignmentViewModel::class.java)
        this.exerciseViewModel =  ViewModelProviders
            .of(activity!!)
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

        this.recyclerViewerAdapter = WorkoutContextExercisesAdapter(this)


        recycler_view.adapter = this.recyclerViewerAdapter
        this.setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()

        exerciseViewModel.setFilterMutableLiveData(this.exercisesFilters)
        this.exerciseViewModel.selectData().observe(this, Observer {
            if (it != null) {
                this.recyclerViewerAdapter.updateDataSet(it)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.activity_exercises_view, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.filter_option -> this.openFiltersDialog()
        }

        return super.onOptionsItemSelected(item)
    }

    override fun onClick(v: View?) {
        when(v?.id) {
            R.id.btn_add_custom_exercise -> AddCustomExerciseDialog().show(
                fragmentManager, "addCustomExerciseDialog"
            )
        }
    }

    private fun getExerciseFilters(): ExercisesFilters {
        var exercisesFilters: ExercisesFilters? = arguments?.getParcelable("exerciseFilters")
        if (exercisesFilters == null) {
            exercisesFilters = ExercisesFilters()
        }

        return exercisesFilters
    }

    private fun openFiltersDialog() {
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val filterDialog = ExerciseFilterDialog()
        filterDialog.show(fragmentTransaction, "exercisesFilterDialog")
    }

    override fun insertExerciseAssignment(exercise: Exercise, workout: Workout) {
        launch {
            val exerciseAssignment = ExerciseAssignment()
            exerciseAssignment.exerciseId = exercise.id
            exerciseAssignment.workoutId = workout.id
            exerciseAssignment.position = exerciseAssignmentViewModel.getLastExercisePosition(workout.id)

            exerciseAssignmentViewModel.insert(exerciseAssignment)
        }

        Toast.makeText(context,"${exercise.name} added to ${workout.name}",Toast.LENGTH_LONG)
            .show()
    }

    override fun insertExerciseAssignment(exercise: Exercise) {
        launch {
            val exerciseAssignment = ExerciseAssignment()
            exerciseAssignment.exerciseId = exercise.id
            exerciseAssignment.workoutId = selectedWorkout!!.id
            exerciseAssignment.position = exerciseAssignmentViewModel.getLastExercisePosition(selectedWorkout!!.id)

            exerciseAssignmentViewModel.insert(exerciseAssignment)
        }

        Toast.makeText(
            context,
            "${exercise.name} added to ${this.selectedWorkout!!.name}",Toast.LENGTH_LONG
        ).show()
    }
}
