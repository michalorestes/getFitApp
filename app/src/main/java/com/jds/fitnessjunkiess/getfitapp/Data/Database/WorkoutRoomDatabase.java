package com.jds.fitnessjunkiess.getfitapp.Data.Database;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.jds.fitnessjunkiess.getfitapp.Data.DAO.ExerciseDao;
import com.jds.fitnessjunkiess.getfitapp.Data.DAO.MuscleGroupDao;
import com.jds.fitnessjunkiess.getfitapp.Data.DAO.WorkoutDao;
import com.jds.fitnessjunkiess.getfitapp.Data.DAO.WorkoutExerciseDao;
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.Exercise;
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.MuscleGroup;
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.Workout;
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.WorkoutExerciseAssignment;

@Database(entities = {
    Workout.class,
    WorkoutExerciseAssignment.class,
    Exercise.class,
    MuscleGroup.class
}, version = 9, exportSchema = false)
public abstract class WorkoutRoomDatabase extends RoomDatabase{

  private static WorkoutRoomDatabase instance;
  private final static String DB_NAME = "WORKOUTS";

  public abstract WorkoutDao workoutDao();
  public abstract WorkoutExerciseDao workoutExerciseDao();
  public abstract ExerciseDao exerciseDao();
  public abstract MuscleGroupDao muscleGroupDao();

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
    private final ExerciseDao exerciseDao;
    private final MuscleGroupDao muscleGroupDao;

    PopulateDbAsync(WorkoutRoomDatabase db) {
      workoutDao = db.workoutDao();
      workoutExerciseDao = db.workoutExerciseDao();
      exerciseDao = db.exerciseDao();
      muscleGroupDao = db.muscleGroupDao();
    }

    @Override
    protected Void doInBackground(final Void... params) {
      workoutDao.deleteAll();
      exerciseDao.deleteAll();
      workoutExerciseDao.deleteAll();
      muscleGroupDao.deleteAll();
//
      for (Workout w : WorkoutsTestData.getData()) {
        workoutDao.insert(w);
      }
//
      for (Exercise e : ExercisesTestData.getData()) {
        exerciseDao.insert(e);
      }
//
      for (WorkoutExerciseAssignment wea : WorkoutExerciseAssignmentTestData.getData()) {
        workoutExerciseDao.insert(wea);
      }

      for (MuscleGroup mg : MuscleGroupTestData.getData()) {
        muscleGroupDao.insert(mg);
      }

      return null;
    }
  }

}
