package com.jds.fitnessjunkiess.getfitapp.Database;


import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.jds.fitnessjunkiess.getfitapp.DAO.WorkoutDao;
import com.jds.fitnessjunkiess.getfitapp.DAO.WorkoutExerciseDao;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;
import com.jds.fitnessjunkiess.getfitapp.Entities.WorkoutExercise;

@Database(entities = {Workout.class, WorkoutExercise.class}, version = 3, exportSchema = false)
public abstract class WorkoutRoomDatabase extends RoomDatabase{

  private static WorkoutRoomDatabase instance;
  private final static String DB_NAME = "WORKOUTS";

  public abstract WorkoutDao workoutDao();
  public abstract WorkoutExerciseDao workoutExerciseDao();

  public static WorkoutRoomDatabase getDb(final Context context) {
    if (instance == null) {
      synchronized (WorkoutRoomDatabase.class) {
        if (instance == null) {
          instance = Room
              .databaseBuilder(context, WorkoutRoomDatabase.class, DB_NAME)
              .fallbackToDestructiveMigration()
              .build();
        }
      }
    }

    return instance;
  }
}
