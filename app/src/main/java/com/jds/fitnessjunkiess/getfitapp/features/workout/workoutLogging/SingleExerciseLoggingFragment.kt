package com.jds.fitnessjunkiess.getfitapp.features.workout.workoutLogging

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseLogViewModel
import com.jds.fitnessjunkiess.getfitapp.data.viewModels.ExerciseViewModel
import com.jds.fitnessjunkiess.getfitapp.interfaces.OnFragmentActionBarInteractionInterface

class SingleExerciseLoggingFragment : Fragment() {

    private lateinit var fragmentInteraction: OnFragmentActionBarInteractionInterface
    private lateinit var currentExercise: Exercise

    private lateinit var exerciseLogViewModel: ExerciseLogViewModel

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        if (context is OnFragmentActionBarInteractionInterface) {
            this.fragmentInteraction = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.currentExercise = arguments!!.getParcelable("exerciseData")!!
        this.fragmentInteraction.setToolbarTitle("Log ${currentExercise.name}")

        this.exerciseLogViewModel =
                ViewModelProviders.of(activity!!).get(ExerciseLogViewModel::class.java)
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

    override fun onStart() {
        super.onStart()

        this.exerciseLogViewModel.selectOne(this.currentExercise.id).observe(this, Observer {
            Log.d("--->", it.toString())
        })
    }
}