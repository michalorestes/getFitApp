package com.jds.fitnessjunkiess.getfitapp.features.workout

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import androidx.navigation.findNavController
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout
import com.jds.fitnessjunkiess.getfitapp.features.workout.adapters.WorkoutViewAdapter
import com.jds.fitnessjunkiess.getfitapp.interfaces.OnFragmentActionBarInteractionInterface
import kotlinx.android.synthetic.main.fragment_workout_view.*
import android.support.v7.widget.helper.ItemTouchHelper
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseRelationship
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseAssignmentViewModel
import com.jds.fitnessjunkiess.getfitapp.features.workout.helpers.ItemTouchCallback

class WorkoutViewFragment :
    Fragment(),
    WorkoutViewAdapter.OnAdapterInteractionInterface
{
    private lateinit var exerciseAssignmentViewModel: ExerciseAssignmentViewModel
    private lateinit var recyclerViewAdapter: WorkoutViewAdapter
    private lateinit var workout: Workout
    private lateinit var actionBarInteractionInterface: OnFragmentActionBarInteractionInterface

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentActionBarInteractionInterface) {
            this.actionBarInteractionInterface = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.workout = arguments!!.getParcelable("workoutData")!!
        this.recyclerViewAdapter =
                WorkoutViewAdapter(context, this)
        this.exerciseAssignmentViewModel =
                ViewModelProviders.of(this).get(ExerciseAssignmentViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_workout_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val manager = LinearLayoutManager(context)
        this.recycler_view.layoutManager = manager
        this.recycler_view.adapter = this.recyclerViewAdapter

        val callback = ItemTouchCallback(this.recyclerViewAdapter)
        val touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(recycler_view)

        this.setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        this.exerciseAssignmentViewModel.selectExerciseRelationsData(this.workout.id).observe(
            this,
            Observer {
                exerciseAssignment -> this.recyclerViewAdapter.updateDataSet(exerciseAssignment)
            })
    }

    override fun onPause() {
        super.onPause()
        this.exerciseAssignmentViewModel.batchUpdate(
            this.recyclerViewAdapter.dataSet.map { it.relationship }
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        actionBarInteractionInterface.setToolbarTitle(this.workout.name)
        inflater?.inflate(R.menu.activity_toolbar_workout_view, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when(item?.itemId) {
            R.id.action_add_exercise -> {
                val bundle = Bundle();
                bundle.putParcelable("selectedWorkout", this.workout)
                view?.findNavController()?.navigate(
                    R.id.action_workoutViewFragment_to_browseExercisesFragment,
                    bundle
                )
            }

            R.id.action_edit_workout -> {
                val fragmentTransaction = fragmentManager?.beginTransaction()
                val dialogTest = WorkoutDetailsDialog()
                dialogTest.show(fragmentTransaction, "tag2")
            }
            else -> {
               return  super.onOptionsItemSelected(item)
            }
        }

        return true
    }

    override fun onItemClick(view: View, exerciseRelationship: ExerciseRelationship) {
        val bundle = Bundle()
        bundle.putParcelable("workoutExerciseAssignment", exerciseRelationship.relationship)
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val editWorkoutExerciseDialog = EditWorkoutExerciseDialog()
        editWorkoutExerciseDialog.arguments = bundle
        editWorkoutExerciseDialog.show(fragmentTransaction, "editWorkoutExerciseDialog")
    }

    override fun onRemoveExercise(exerciseRelationship: ExerciseRelationship?) {
        this.exerciseAssignmentViewModel.delete(exerciseRelationship!!.relationship)
    }
}
