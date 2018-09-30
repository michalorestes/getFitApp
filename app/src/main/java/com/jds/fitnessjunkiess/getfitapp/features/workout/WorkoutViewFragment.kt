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
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.pojo.WorkoutExercise
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutExerciseViewModel
import com.jds.fitnessjunkiess.getfitapp.features.workout.adapters.WorkoutViewAdapter
import com.jds.fitnessjunkiess.getfitapp.interfaces.OnFragmentActionBarInteractionInterface
import kotlinx.android.synthetic.main.fragment_workout_view.*

class WorkoutViewFragment : Fragment(), WorkoutViewAdapter.OnItemClickListener
{
    private lateinit var workoutExerciseViewModel: WorkoutExerciseViewModel
    private lateinit var workout: Workout
    private lateinit var recyclerViewAdapter: WorkoutViewAdapter

    private lateinit var actionBarInteractionInterface: OnFragmentActionBarInteractionInterface

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentActionBarInteractionInterface) {
            this.actionBarInteractionInterface = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        this.workout = arguments!!.getParcelable("workoutData")
        this.recyclerViewAdapter =
                WorkoutViewAdapter(this)
        this.workoutExerciseViewModel =
                ViewModelProviders.of(this).get(WorkoutExerciseViewModel::class.java)
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
        this.setHasOptionsMenu(true)
    }

    override fun onStart() {
        super.onStart()
        this.workoutExerciseViewModel.selectAll(this.workout.id).observe(
            this,
            Observer { workoutExercises -> this.recyclerViewAdapter.updateDataSet(workoutExercises)
            })
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

    override fun onItemClick(view: View, workoutExercise: WorkoutExercise) {
        val workoutExerciseAssignment = WorkoutExerciseAssignment()
        workoutExerciseAssignment.id = workoutExercise.id
        workoutExerciseAssignment.exerciseId = workoutExercise.exerciseId
        workoutExerciseAssignment.workoutId = workoutExercise.workoutId
        workoutExerciseAssignment.userId = workoutExercise.userId
        workoutExerciseAssignment.lengthTime = workoutExercise.lengthTime
        workoutExerciseAssignment.restTime = workoutExercise.restTime
        workoutExerciseAssignment.sprintTime = workoutExercise.sprintTime
        workoutExerciseAssignment.sets = workoutExercise.sets
        workoutExerciseAssignment.reps = workoutExercise.reps

        val bundle = Bundle()
        bundle.putParcelable("workoutExerciseAssignment", workoutExerciseAssignment)
        val fragmentTransaction = fragmentManager!!.beginTransaction()
        val editWorkoutExerciseDialog = EditWorkoutExerciseDialog()
        editWorkoutExerciseDialog.arguments = bundle
        editWorkoutExerciseDialog.show(fragmentTransaction, "editWorkoutExerciseDialog")
    }
}
