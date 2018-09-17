package com.jds.fitnessjunkiess.getfitapp.data.database;


import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import com.jds.fitnessjunkiess.getfitapp.data.dao.ExerciseDao;
import com.jds.fitnessjunkiess.getfitapp.data.dao.MuscleGroupDao;
import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutDao;
import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutExerciseDao;
import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutExercisesAssignmentDao;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.MuscleGroup;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment;

@Database(entities = {
    Workout.class,
    WorkoutExerciseAssignment.class,
    Exercise.class,
    MuscleGroup.class
}, version = 10, exportSchema = false)
public abstract class WorkoutRoomDatabase extends RoomDatabase{

  private static WorkoutRoomDatabase instance;
  private final static String DB_NAME = "WORKOUTS";

  public abstract WorkoutDao workoutDao();
  public abstract WorkoutExerciseDao workoutExerciseDao();
  public abstract ExerciseDao exerciseDao();
  public abstract MuscleGroupDao muscleGroupDao();
  public abstract WorkoutExercisesAssignmentDao workoutExercisesAssignmentDao();

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
