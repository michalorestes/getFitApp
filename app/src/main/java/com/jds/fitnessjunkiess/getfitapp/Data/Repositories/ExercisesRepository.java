package com.jds.fitnessjunkiess.getfitapp.Data.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.jds.fitnessjunkiess.getfitapp.Data.DAO.ExerciseDao;
import com.jds.fitnessjunkiess.getfitapp.Data.Database.WorkoutRoomDatabase;
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.Exercise;

import java.util.List;

public class ExercisesRepository {
  private ExerciseDao dao;

  public ExercisesRepository(Application context) {
    WorkoutRoomDatabase database = WorkoutRoomDatabase.getDb(context);
    this.dao = database.exerciseDao();
  }

  public LiveData<List<Exercise>> filterSelect(String muscleGroups, String type) {
    return this.dao.filterSelect("%" + muscleGroups + "%", "%" + type + "%");
  }

  public LiveData<List<Exercise>> selectAll() {
    return this.dao.selectAll();
  }

  public void insert(Exercise exercise) {
    new insertAsyncTask(this.dao).execute(exercise);
  }

  private static class insertAsyncTask extends AsyncTask<Exercise, Void, Void> {

    private ExerciseDao exerciseDao;

    insertAsyncTask(ExerciseDao dao) {
      exerciseDao = dao;
    }

    @Override
    protected Void doInBackground(final Exercise... params) {
      exerciseDao.insert(params[0]);
      return null;
    }
  }
}
