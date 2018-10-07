package com.jds.fitnessjunkiess.getfitapp.data.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.os.AsyncTask
import com.jds.fitnessjunkiess.getfitapp.data.dao.ExerciseDao
import com.jds.fitnessjunkiess.getfitapp.data.dao.MuscleGroupDao
import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutDao
import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutExerciseDao
import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutExercisesAssignmentDao
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.MuscleGroup
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.typeConverters.ExercisePropertiesTypeConverter

@Database(
    entities = [
        Workout::class,
        WorkoutExerciseAssignment::class,
        Exercise::class, MuscleGroup::class
    ],
    version = 12, exportSchema = false
)
@TypeConverters(ExercisePropertiesTypeConverter::class)
abstract class WorkoutRoomDatabase : RoomDatabase() {

    abstract fun workoutDao(): WorkoutDao
    abstract fun workoutExerciseDao(): WorkoutExerciseDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun muscleGroupDao(): MuscleGroupDao
    abstract fun workoutExercisesAssignmentDao(): WorkoutExercisesAssignmentDao

    companion object {
        private var instance: WorkoutRoomDatabase? = null
        private const val DB_NAME = "WORKOUTS"

        fun getDb(context: Context): WorkoutRoomDatabase? {
            if (instance == null) {
                synchronized(WorkoutRoomDatabase::class.java) {
                    if (instance == null) {
                        instance = Room
                            .databaseBuilder(context, WorkoutRoomDatabase::class.java, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback)
                            .build()
                    }
                }
            }

            return instance
        }

        private val sRoomDatabaseCallback = object : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                PopulateDbAsync(instance!!).execute()
            }
        }
    }

    private class PopulateDbAsync internal constructor(db: WorkoutRoomDatabase) :
        AsyncTask<Void, Void, Void>() {

        private val workoutDao: WorkoutDao = db.workoutDao()
        private val workoutExerciseDao: WorkoutExerciseDao = db.workoutExerciseDao()
        private val exerciseDao: ExerciseDao = db.exerciseDao()
        private val muscleGroupDao: MuscleGroupDao = db.muscleGroupDao()

        override fun doInBackground(vararg params: Void): Void? {
            workoutDao.deleteAll()
            exerciseDao.deleteAll()
            workoutExerciseDao.deleteAll()
            muscleGroupDao.deleteAll()

            WorkoutsTestData.getData().forEach { workoutDao.insert(it) }
            ExercisesTestData.getData().forEach { exerciseDao.insert(it) }
            WorkoutExerciseAssignmentTestData.getData().forEach { workoutExerciseDao.insert(it) }
            MuscleGroupTestData.getData().forEach { muscleGroupDao.insert(it) }

            return null
        }
    }
}
