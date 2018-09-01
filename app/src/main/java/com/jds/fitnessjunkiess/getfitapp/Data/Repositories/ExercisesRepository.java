package com.jds.fitnessjunkiess.getfitapp.Data.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.jds.fitnessjunkiess.getfitapp.Data.DAO.ExerciseDao;
import com.jds.fitnessjunkiess.getfitapp.Data.DAO.WorkoutExerciseDao;
import com.jds.fitnessjunkiess.getfitapp.Data.Database.WorkoutRoomDatabase;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Exercise;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.WorkoutExerciseAssignment;
import com.jds.fitnessjunkiess.getfitapp.Pojo.WorkoutExercise;

import java.util.List;

public class ExercisesRepository {
  private ExerciseDao dao;
  private LiveData<List<WorkoutExercise>> data;

  public ExercisesRepository(Application context, int workoutId) {
    WorkoutRoomDatabase database = WorkoutRoomDatabase.getDb(context);
    this.dao = database.exerciseDao();
  }

  public LiveData<List<Exercise>> filterSelect(String muscleGroups, String type) {
    return this.dao.filterSelect("%" + muscleGroups + "%", "%" + type + "%");
  }

  public void insert(Exercise exercise) {
    new insertAsyncTask(this.dao).execute(exercise);
  }

  private static class insertAsyncTask extends AsyncTask<Exercise, Void, Void> {

    private ExerciseDao exerciseDao;

    insertAsyncTask(ExerciseDao  dao) {
      exerciseDao = dao;
    }

    @Override
    protected Void doInBackground(final Exercise... params) {
      exerciseDao.insert(params[0]);
      return null;
    }
  }
}
