package com.jds.fitnessjunkiess.getfitapp.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutDao;
import com.jds.fitnessjunkiess.getfitapp.data.database.WorkoutRoomDatabase;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout;

import java.util.List;

public class WorkoutsRepository {
  private WorkoutDao workoutDao;
  private LiveData<List<Workout>> data;

  public WorkoutsRepository(Application context) {
    WorkoutRoomDatabase workoutDatabase = WorkoutRoomDatabase.getDb(context);
    this.workoutDao = workoutDatabase.workoutDao();
    this.data = this.workoutDao.selectAll();
  }

  public LiveData<List<Workout>> getData() {
    return data;
  }

  public void insert(Workout workout) {
    new insertAsyncTask(workoutDao).execute(workout);
  }

  private static class insertAsyncTask extends AsyncTask<Workout, Void, Void> {

    private WorkoutDao mAsyncTaskDao;

    insertAsyncTask(WorkoutDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final Workout... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }
}
