package com.jds.fitnessjunkiess.getfitapp.data.database.testData

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.MuscleGroup

import java.util.ArrayList

object MuscleGroupTestData {

    val data: List<MuscleGroup>
        get() {
            val m1 = MuscleGroup()
            m1.name = "ABS"

            val m2 = MuscleGroup()
            m2.name = "Back"

            val m3 = MuscleGroup()
            m3.name = "Biceps"

            val m4 = MuscleGroup()
            m4.name = "Legs"

            val m5 = MuscleGroup()
            m5.name = "Triceps"

            val m6 = MuscleGroup()
            m6.name = "Chest"

            val muscleGroups = ArrayList<MuscleGroup>()
            muscleGroups.add(m1)
            muscleGroups.add(m2)
            muscleGroups.add(m3)
            muscleGroups.add(m4)
            muscleGroups.add(m5)
            muscleGroups.add(m6)

            return muscleGroups
        }

}
