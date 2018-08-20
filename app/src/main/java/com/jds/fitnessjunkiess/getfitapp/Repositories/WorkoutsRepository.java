package com.jds.fitnessjunkiess.getfitapp.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;
import com.jds.fitnessjunkiess.getfitapp.DAO.WorkoutDao;
import com.jds.fitnessjunkiess.getfitapp.Database.WorkoutRoomDatabase;
import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;

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
