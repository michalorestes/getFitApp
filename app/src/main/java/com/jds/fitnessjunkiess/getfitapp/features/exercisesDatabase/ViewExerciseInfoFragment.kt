package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.jds.fitnessjunkiess.getfitapp.R

class ViewExerciseInfoFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_view_exercise_info, container, false)
    }
}
