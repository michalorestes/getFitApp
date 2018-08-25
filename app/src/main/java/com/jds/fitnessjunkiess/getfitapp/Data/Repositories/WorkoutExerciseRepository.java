package com.jds.fitnessjunkiess.getfitapp.Data.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.jds.fitnessjunkiess.getfitapp.Data.DAO.WorkoutExerciseDao;
import com.jds.fitnessjunkiess.getfitapp.Data.Database.WorkoutRoomDatabase;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.WorkoutExercise;

import java.util.List;

public class WorkoutExerciseRepository {
  private WorkoutExerciseDao dao;
  private LiveData<List<WorkoutExercise>> data;

  public WorkoutExerciseRepository(Application context) {
    WorkoutRoomDatabase database = WorkoutRoomDatabase.getDb(context);
    this.dao = database.workoutExerciseDao();
  }

  public LiveData<List<WorkoutExercise>> selectAll() {
    return this.dao.selectAll();
  }

  public void insert(WorkoutExercise workoutExercise) {
    new insertAsyncTask(this.dao).execute(workoutExercise);
  }

  private static class insertAsyncTask extends AsyncTask<WorkoutExercise, Void, Void> {

    private WorkoutExerciseDao mAsyncTaskDao;

    insertAsyncTask(WorkoutExerciseDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final WorkoutExercise... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }
}
