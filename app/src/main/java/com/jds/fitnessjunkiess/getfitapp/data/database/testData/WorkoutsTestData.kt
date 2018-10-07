package com.jds.fitnessjunkiess.getfitapp.data.database.testData

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout
import java.util.ArrayList

object WorkoutsTestData {
    val data: List<Workout>
        get() {

            val workout1 = Workout()
            workout1.userId = 7
            workout1.name = "Massive biceps"
            workout1.id = 1
            workout1.schedule = "Monday,Tuesday,Friday"
            workout1.type = "Weights"

            val workout2 = Workout()
            workout2.userId = 7
            workout2.name = "Shreded"
            workout2.id = 2
            workout2.schedule = "Tuesday,Friday"
            workout2.type = "Interval"

            val workout3 = Workout()
            workout3.userId = 7
            workout3.name = "6 pack ABS"
            workout3.id = 3
            workout3.schedule = "Saturday"
            workout3.type = "Cardio"

            val workouts = ArrayList<Workout>()
            workouts.add(workout1)
            workouts.add(workout2)
            workouts.add(workout3)

            return workouts
        }
}
