package com.jds.fitnessjunkiess.getfitapp.features.workout.workoutLogging

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseLog
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseRelationship
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseLogViewModel
import com.jds.fitnessjunkiess.getfitapp.features.workout.workoutLogging.adapters.ExerciseSetsAdapter
import com.jds.fitnessjunkiess.getfitapp.interfaces.OnFragmentActionBarInteractionInterface
import kotlinx.android.synthetic.main.fragment_track_single_exercise.*
import java.text.DateFormat
import java.util.*


class SingleExerciseLoggingFragment : Fragment(), View.OnClickListener {

    private lateinit var fragmentInteraction: OnFragmentActionBarInteractionInterface
    private lateinit var currentExercise: ExerciseRelationship

    private lateinit var exerciseLogViewModel: ExerciseLogViewModel

    private lateinit var recyclerViewAdapter: ExerciseSetsAdapter

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentActionBarInteractionInterface) this.fragmentInteraction = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity: AppCompatActivity = activity as AppCompatActivity

        this.currentExercise = arguments!!.getParcelable("exerciseData")!!

        this.exerciseLogViewModel =
                ViewModelProviders.of(activity).get(ExerciseLogViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater
            .inflate(R.layout.fragment_track_single_exercise, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(view.context)
        recycler_view.layoutManager = layoutManager

        this.recyclerViewAdapter = ExerciseSetsAdapter(
            this.currentExercise.relationship.sets,
            this.currentExercise.relationship.reps
        )
        recycler_view.adapter = recyclerViewAdapter

        btn_save.setOnClickListener(this)
    }

    override fun onStart() {
        super.onStart()
        val activity: AppCompatActivity = activity as AppCompatActivity
        activity.supportActionBar?.elevation = 0.0f

        this.exerciseLogViewModel.selectOne(
            this.currentExercise.exercise.id,
            this.currentExercise.relationship.workoutId
        ).observe(
            this,
            Observer {
            if (it?.setLogs != null) {
                this.recyclerViewAdapter.updateDataSet(it.setLogs!!)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        val activity: AppCompatActivity = activity as AppCompatActivity
        activity.supportActionBar!!.elevation = 22.0f
    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.btn_save -> {
                saveLog()
                Navigation.findNavController(v).navigateUp()
            }
        }
    }

    private fun saveLog() {
        val log = ExerciseLog(
            exerciseId = this.currentExercise.exercise.id,
            workoutId = this.currentExercise.relationship.workoutId,
            date = DateFormat.getDateTimeInstance().format(Date()),
            setLogs = this.recyclerViewAdapter.dataSet
        )

        this.exerciseLogViewModel.insert(log)
    }
}
