package com.jds.fitnessjunkiess.getfitapp.data.database.testData

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseLoggingTypes
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExerciseTypes
import com.jds.fitnessjunkiess.getfitapp.data.pojo.MuscleGroupKeys
import com.jds.fitnessjunkiess.getfitapp.data.pojo.MuscleGroups

import java.util.ArrayList

object ExercisesTestData {
    val data: List<Exercise>
        get() {
            val exercise = Exercise()
            exercise.id = 1
            exercise.name = "Pull ups "
            exercise.instructions = "Grab the handle and pull yourself upwards."
            exercise.setMuscleGroupsByKey(MuscleGroupKeys.PRIMARY, MuscleGroups.BACK)
            exercise.setMuscleGroupsByKey(MuscleGroupKeys.OTHER, MuscleGroups.BICEPS)
            exercise.type = ExerciseTypes.BODY_WEIGHT
            exercise.picture = "https://nourl.com/img.png"
            exercise.defaultLoggingType = ExerciseLoggingTypes.REPS

            val exercise2 = Exercise()
            exercise2.id = 2
            exercise2.name = "Biceps curl"
            exercise2.instructions = "Pull the weight upwards."
            exercise2.setMuscleGroupsByKey(MuscleGroupKeys.PRIMARY, MuscleGroups.BICEPS)
            exercise2.type = ExerciseTypes.WEIGHTS
            exercise2.picture = "https://nourl.com/img.png"
            exercise2.defaultLoggingType = ExerciseLoggingTypes.REPS

            val exercise3 = Exercise()
            exercise3.id = 3
            exercise3.name = "Crunches"
            exercise3.instructions = "Lay down on the floow and exercise your abs."
            exercise3.setMuscleGroupsByKey(MuscleGroupKeys.PRIMARY, MuscleGroups.CORE)
            exercise3.type = ExerciseTypes.BODY_WEIGHT
            exercise3.picture = "https://nourl.com/img.png"
            exercise3.defaultLoggingType = ExerciseLoggingTypes.REPS

            val exercise4 = Exercise()
            exercise4.id = 4
            exercise4.name = "Deadlift"
            exercise4.instructions = "Lift up heavy weights."
            exercise4.setMuscleGroupsByKey(MuscleGroupKeys.PRIMARY, MuscleGroups.BACK)
            exercise4.setMuscleGroupsByKey(MuscleGroupKeys.OTHER, MuscleGroups.HAMSTRINGS)
            exercise4.setMuscleGroupsByKey(MuscleGroupKeys.OTHER, MuscleGroups.QUADRICEPS)
            exercise4.setMuscleGroupsByKey(MuscleGroupKeys.OTHER, MuscleGroups.CORE)
            exercise4.type = ExerciseTypes.WEIGHTS
            exercise4.picture = "https://nourl.com/img.png"
            exercise4.defaultLoggingType = ExerciseLoggingTypes.REPS

            val exercise5 = Exercise()
            exercise5.id = 5
            exercise5.name = "Trademil Run"
            exercise5.instructions = "Continue running"
            exercise5.type = ExerciseTypes.CARDIO
            exercise5.defaultLoggingType = ExerciseLoggingTypes.TIME

            val exercises = ArrayList<Exercise>()
            exercises.add(exercise)
            exercises.add(exercise2)
            exercises.add(exercise3)
            exercises.add(exercise4)
            exercises.add(exercise5)

            return exercises
        }
}
