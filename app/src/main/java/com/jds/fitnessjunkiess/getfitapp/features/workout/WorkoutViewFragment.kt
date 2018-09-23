package com.jds.fitnessjunkiess.getfitapp.features.workout

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import androidx.navigation.findNavController
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutExerciseViewModel
import com.jds.fitnessjunkiess.getfitapp.features.workout.adapters.WorkoutViewViewAdapter
import com.jds.fitnessjunkiess.getfitapp.interfaces.OnFragmentActionBarInteractionInterface
import kotlinx.android.synthetic.main.fragment_workout_view.*

class WorkoutViewFragment : Fragment() {

    private lateinit var workoutExerciseViewModel: WorkoutExerciseViewModel
    private lateinit var workout: Workout
    private lateinit var recyclerViewAdapter: WorkoutViewViewAdapter

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
        this.recyclerViewAdapter = WorkoutViewViewAdapter()
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
}
