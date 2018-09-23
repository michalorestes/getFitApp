package com.jds.fitnessjunkiess.getfitapp.features.exercisesDatabase

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.*
import android.widget.Button
import androidx.navigation.findNavController
import com.jds.fitnessjunkiess.getfitapp.R
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseTypes
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExercisesFilters
import com.jds.fitnessjunkiess.getfitapp.data.pojo.MuscleGroups

class ExercisesHomeScreenFragment : Fragment(), View.OnClickListener
{
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_exercises_home_screen, container, false)

        val allExercises = view.findViewById<Button>(R.id.view_all_exercises_btn)
        val weights = view.findViewById<Button>(R.id.view_weights_exercises_btn)
        val bodyWeights = view.findViewById<Button>(R.id.view_body_weight_exercises_btn)
        val cardio = view.findViewById<Button>(R.id.view_cardio_exercises_btn)
        val customExercises = view.findViewById<Button>(R.id.view_custom_exercises_btn)

        val abs = view.findViewById<Button>(R.id.view_abs_exercises_btn)
        val biceps = view.findViewById<Button>(R.id.view_biceps_exercises_btn)
        val back = view.findViewById<Button>(R.id.view_back_exercises_btn)
        val legs = view.findViewById<Button>(R.id.view_legs_exercises_btn)
        val triceps = view.findViewById<Button>(R.id.view_triceps_exercises_btn)
        val chest = view.findViewById<Button>(R.id.view_chest_exercises_btn)

        allExercises.setOnClickListener(this)
        bodyWeights.setOnClickListener(this)
        weights.setOnClickListener(this)
        cardio.setOnClickListener(this)
        customExercises.setOnClickListener(this)

        abs.setOnClickListener(this)
        biceps.setOnClickListener(this)
        back.setOnClickListener(this)
        legs.setOnClickListener(this)
        triceps.setOnClickListener(this)
        chest.setOnClickListener(this)

        return view
    }

    override fun onClick(v: View) {
        val exerciseFilters = ExercisesFilters()
        when (v.id) {
            R.id.view_all_exercises_btn -> exerciseFilters.types.add(ExerciseTypes.ALL)
            R.id.view_weights_exercises_btn -> exerciseFilters.types.add(ExerciseTypes.WEIGHTS)
            R.id.view_body_weight_exercises_btn -> exerciseFilters.types.add(ExerciseTypes.BODY_WEIGHT)
            R.id.view_cardio_exercises_btn -> exerciseFilters.types.add(ExerciseTypes.CARDIO)
            R.id.view_custom_exercises_btn -> exerciseFilters.isCustom = true
            R.id.view_abs_exercises_btn -> exerciseFilters.muscleGroups.add(MuscleGroups.CORE)
            R.id.view_biceps_exercises_btn -> exerciseFilters.muscleGroups.add(MuscleGroups.BICEPS)
        }

        val bundle = Bundle()
        bundle.putParcelable("exerciseFilters", exerciseFilters)
        v.findNavController().navigate(R.id.browseExercisesFragment, bundle)
    }
}
