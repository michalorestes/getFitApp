package com.jds.fitnessjunkiess.getfitapp.data.database.testData

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseAssignment
import java.util.ArrayList

object WorkoutExerciseAssignmentTestData {
    val data: List<ExerciseAssignment>
        get() {
            val workoutExerciseAssignment1 = ExerciseAssignment()
            workoutExerciseAssignment1.id = 1
            workoutExerciseAssignment1.reps = 12
            workoutExerciseAssignment1.restTime = "00:00:30"
            workoutExerciseAssignment1.sets = 3
            workoutExerciseAssignment1.exerciseId = 1
            workoutExerciseAssignment1.workoutId = 1

            val workoutExerciseAssignment2 = ExerciseAssignment()
            workoutExerciseAssignment2.id = 2
            workoutExerciseAssignment2.reps = 12
            workoutExerciseAssignment2.restTime = "00:00:30"
            workoutExerciseAssignment2.sets = 4
            workoutExerciseAssignment2.exerciseId = 2
            workoutExerciseAssignment2.workoutId = 1

            val workoutExerciseAssignment3 = ExerciseAssignment()
            workoutExerciseAssignment3.id = 3
            workoutExerciseAssignment3.reps = 12
            workoutExerciseAssignment3.lengthTime = "00:60:00"
            workoutExerciseAssignment3.exerciseId = 5
            workoutExerciseAssignment3.workoutId = 3


            val workoutExerciseAssignment4 = ExerciseAssignment()
            workoutExerciseAssignment4.id = 4
            workoutExerciseAssignment4.reps = 12
            workoutExerciseAssignment4.restTime = "00:00:30"
            workoutExerciseAssignment4.sets = 3
            workoutExerciseAssignment4.exerciseId = 1
            workoutExerciseAssignment4.workoutId = 2

            val workoutExerciseAssignment5 = ExerciseAssignment()
            workoutExerciseAssignment5.id = 5
            workoutExerciseAssignment5.reps = 12
            workoutExerciseAssignment5.restTime = "00:00:30"
            workoutExerciseAssignment5.sets = 4
            workoutExerciseAssignment5.exerciseId = 2
            workoutExerciseAssignment5.workoutId = 2

            val workoutExerciseAssignment6 = ExerciseAssignment()
            workoutExerciseAssignment6.id = 6
            workoutExerciseAssignment6.reps = 12
            workoutExerciseAssignment6.restTime = "00:00:30"
            workoutExerciseAssignment6.sets = 4
            workoutExerciseAssignment6.exerciseId = 4
            workoutExerciseAssignment6.workoutId = 2

            val workoutExerciseAssignments = ArrayList<ExerciseAssignment>()
            workoutExerciseAssignments.add(workoutExerciseAssignment1)
            workoutExerciseAssignments.add(workoutExerciseAssignment2)
            workoutExerciseAssignments.add(workoutExerciseAssignment3)
            workoutExerciseAssignments.add(workoutExerciseAssignment4)
            workoutExerciseAssignments.add(workoutExerciseAssignment5)
            workoutExerciseAssignments.add(workoutExerciseAssignment6)


            return workoutExerciseAssignments
        }
}
