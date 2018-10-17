package com.jds.fitnessjunkiess.getfitapp.data.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import android.os.AsyncTask
import com.jds.fitnessjunkiess.getfitapp.data.dao.ExerciseDao
import com.jds.fitnessjunkiess.getfitapp.data.dao.ExerciseLogDao
import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutDao
import com.jds.fitnessjunkiess.getfitapp.data.dao.ExercisesAssignmentDao
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseAssignment
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseLog
import com.jds.fitnessjunkiess.getfitapp.data.database.testData.ExercisesTestData
import com.jds.fitnessjunkiess.getfitapp.data.database.testData.WorkoutExerciseAssignmentTestData
import com.jds.fitnessjunkiess.getfitapp.data.database.testData.WorkoutsTestData
import com.jds.fitnessjunkiess.getfitapp.data.typeConverters.ExercisePropertiesTypeConverter
import com.jds.fitnessjunkiess.getfitapp.data.typeConverters.ExerciseSetsLogTypeConverter
import com.jds.fitnessjunkiess.getfitapp.data.typeConverters.ExerciseTimeLogTypeConverter

@Database(
    entities = [
        Workout::class,
        ExerciseAssignment::class,
        Exercise::class,
        ExerciseLog::class
    ],
    version = 20, exportSchema = false
)

@TypeConverters(
    ExercisePropertiesTypeConverter::class,
    ExerciseSetsLogTypeConverter::class,
    ExerciseTimeLogTypeConverter::class
)
abstract class WorkoutRoomDatabase : RoomDatabase() {

    abstract fun workoutDao(): WorkoutDao
    abstract fun exerciseDao(): ExerciseDao
    abstract fun exercisesAssignmentDao(): ExercisesAssignmentDao
    abstract fun exerciseLogDao(): ExerciseLogDao

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
        private val exerciseAssignment: ExercisesAssignmentDao = db.exercisesAssignmentDao()
        private val exerciseDao: ExerciseDao = db.exerciseDao()

        override fun doInBackground(vararg params: Void): Void? {
            workoutDao.deleteAll()
            exerciseDao.deleteAll()
            exerciseAssignment.deleteAll()

            WorkoutsTestData.data.forEach { workoutDao.insert(it) }
            ExercisesTestData.data.forEach { exerciseDao.insert(it) }
            WorkoutExerciseAssignmentTestData.data.forEach { exerciseAssignment.insert(it) }

            return null
        }
    }
}
