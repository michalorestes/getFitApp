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
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseRelationship
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseLogViewModel
import com.jds.fitnessjunkiess.getfitapp.features.workout.workoutLogging.adapters.ExerciseSetsAdapter
import com.jds.fitnessjunkiess.getfitapp.interfaces.OnFragmentActionBarInteractionInterface
import kotlinx.android.synthetic.main.fragment_track_single_exercise.*

class SingleExerciseLoggingFragment : Fragment() {

    private lateinit var fragmentInteraction: OnFragmentActionBarInteractionInterface
    private lateinit var currentExercise: ExerciseRelationship

    private lateinit var exerciseLogViewModel: ExerciseLogViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentActionBarInteractionInterface) this.fragmentInteraction = context
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity: AppCompatActivity = activity as AppCompatActivity
        activity.supportActionBar?.elevation = 0.0f

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

        val recyclerViewAdapter = ExerciseSetsAdapter(
            this.currentExercise.relationship.sets,
            this.currentExercise.relationship.reps
        )
        recycler_view.adapter = recyclerViewAdapter
    }

    override fun onStart() {
        super.onStart()

        this.exerciseLogViewModel.selectOne(this.currentExercise.exercise.id).observe(
            this,
            Observer {
            Log.d("--->", it.toString())
        })
    }

    override fun onPause() {
        super.onPause()
        val activity: AppCompatActivity = activity as AppCompatActivity
        activity.supportActionBar?.elevation = 2.0f
    }
}