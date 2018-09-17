package com.jds.fitnessjunkiess.getfitapp.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutExerciseDao;
import com.jds.fitnessjunkiess.getfitapp.data.database.WorkoutRoomDatabase;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment;
import com.jds.fitnessjunkiess.getfitapp.data.pojo.WorkoutExercise;

import java.util.List;

public class WorkoutExerciseRepository {
  private WorkoutExerciseDao dao;
  private LiveData<List<WorkoutExercise>> data;
  private int workoutId;

  public WorkoutExerciseRepository(Application context, int workoutId) {
    WorkoutRoomDatabase database = WorkoutRoomDatabase.getDb(context);
    this.dao = database.workoutExerciseDao();
    this.workoutId = workoutId;
    this.data = dao.getWorkoutExercises(this.workoutId);
  }

  public LiveData<List<WorkoutExercise>> getWorkoutExercises(int workoutId) {
    return data;
  }

  public LiveData<List<WorkoutExerciseAssignment>> selectAll() {
    return this.dao.selectAll();
  }

  public void insert(WorkoutExerciseAssignment workoutExerciseAssignment) {
    new insertAsyncTask(this.dao).execute(workoutExerciseAssignment);
  }

  private static class insertAsyncTask extends AsyncTask<WorkoutExerciseAssignment, Void, Void> {

    private WorkoutExerciseDao mAsyncTaskDao;

    insertAsyncTask(WorkoutExerciseDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final WorkoutExerciseAssignment... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }
}
