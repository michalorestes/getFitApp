package com.jds.fitnessjunkiess.getfitapp.Database;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;

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
              .addCallback(sRoomDatabaseCallback)
              .build();
        }
      }
    }

    return instance;
  }

  private static RoomDatabase.Callback sRoomDatabaseCallback =
      new RoomDatabase.Callback(){

        @Override
        public void onOpen (@NonNull SupportSQLiteDatabase db){
          super.onOpen(db);
          new PopulateDbAsync(instance).execute();
        }
      };

  private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

    private final WorkoutDao workoutDao;
    private final WorkoutExerciseDao workoutExerciseDao;

    PopulateDbAsync(WorkoutRoomDatabase db) {
      workoutDao = db.workoutDao();
      workoutExerciseDao = db.workoutExerciseDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
      workoutDao.deleteAll();
      workoutExerciseDao.deleteAll();

      Workout workout1 = new Workout();
      workout1.setUserId(7);
      workout1.setName("Massive biceps");
      workout1.setId(1);
      workout1.setSchedule("Monday,Tuesday,Friday");
      workout1.setType("Weights");

      Workout workout2 = new Workout();
      workout2.setUserId(7);
      workout2.setName("Shreded");
      workout2.setId(2);
      workout2.setSchedule("Tuesday,Friday");
      workout2.setType("Interval");

      Workout workout3 = new Workout();
      workout3.setUserId(7);
      workout3.setName("6 pack ABS");
      workout3.setId(3);
      workout3.setSchedule("Saturday");
      workout3.setType("Cardio");

      workoutDao.insert(workout1);
      workoutDao.insert(workout2);
      workoutDao.insert(workout3);

      WorkoutExercise workoutExercise1 = new WorkoutExercise();
      workoutExercise1.setLength("232");
      workoutExercise1.setReps(11);
      workoutExercise1.setRest("1354");
      workoutExercise1.setSets(14);
      workoutExercise1.setExerciseId(1);
      workoutExercise1.setWorkoutId(1);

      workoutExerciseDao.insert(workoutExercise1);
      return null;
    }
  }

}
