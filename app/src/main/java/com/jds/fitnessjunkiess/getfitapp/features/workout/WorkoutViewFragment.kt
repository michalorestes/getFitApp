package com.jds.fitnessjunkiess.getfitapp.features.workout

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.*
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.WorkoutExerciseViewModel
import com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase.ExercisesViewActivity
import com.jds.fitnessjunkiess.getfitapp.features.workout.adapters.WorkoutViewViewAdapter
import kotlinx.android.synthetic.main.fragment_workout_view.*

class WorkoutViewFragment : Fragment() {
interface testTest {
    fun setToolbarTitle(text: String)
}
    private lateinit var workoutExerciseViewModel: WorkoutExerciseViewModel
    private lateinit var workout: Workout
    private lateinit var recyclerViewAdapter: WorkoutViewViewAdapter

    private lateinit var contextCC: testTest

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is testTest) {
            this.contextCC = context
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

        contextCC.setToolbarTitle(this.workout.name)
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
        inflater?.inflate(R.menu.activity_toolbar_workout_view, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {

        when(item?.itemId) {
            R.id.action_add_exercise -> {
                val intent = Intent(context, ExercisesViewActivity::class.java)
                intent.putExtra("workoutContext", this.workout)
                startActivity(intent)
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
