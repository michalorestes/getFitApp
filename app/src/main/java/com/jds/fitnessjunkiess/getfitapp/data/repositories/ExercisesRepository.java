package com.jds.fitnessjunkiess.getfitapp.data.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SimpleSQLiteQuery;
import android.os.AsyncTask;
import android.util.Log;

import com.jds.fitnessjunkiess.getfitapp.data.dao.ExerciseDao;
import com.jds.fitnessjunkiess.getfitapp.data.database.WorkoutRoomDatabase;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise;
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExercisesFilters;

import java.util.List;

public class ExercisesRepository {
  private ExerciseDao dao;

  public ExercisesRepository(Application context) {
    WorkoutRoomDatabase database = WorkoutRoomDatabase.Companion.getDb(context);
    this.dao = database.exerciseDao();
  }

  public LiveData<List<Exercise>> filterSelect(ExercisesFilters exercisesFilter) {
    String query = this.buildFilterQuery(exercisesFilter);
    Log.d("------>", query);
    SimpleSQLiteQuery simpleSQLiteQuery = new SimpleSQLiteQuery(query);

    return this.dao.filterSelect(simpleSQLiteQuery);
  }

  public LiveData<List<Exercise>> selectAll() {
    return this.dao.selectAll();
  }

  public void insert(Exercise exercise) {
    new insertAsyncTask(this.dao).execute(exercise);
  }

  private String buildFilterQuery(ExercisesFilters exercisesFilter) {
    if (exercisesFilter.getTypes().size() == 0) {
      exercisesFilter.getTypes().add("");
    }

    if (exercisesFilter.getMuscleGroups().size() == 0) {
      exercisesFilter.getMuscleGroups().add("");
    }

    StringBuilder subQuery = new StringBuilder();
    subQuery.append("SELECT * FROM exercises WHERE ");
    if (exercisesFilter.isCustom() != null) {
      subQuery.append("isCustom = ").append(exercisesFilter.isCustom() ? 1 : 0).append(" AND ");
    }

    for (int i = 0; i < exercisesFilter.getMuscleGroups().size(); i++) {
      if (i != 0) {
        subQuery.append("OR ");
      }

      subQuery
          .append("muscleGroups LIKE '%")
          .append(exercisesFilter.getMuscleGroups().get(i))
          .append("%' ");
    }

    StringBuilder query = new StringBuilder();
    query.append("SELECT * FROM (").append(subQuery);
    query.append(") WHERE ");

    for (int i = 0; i < exercisesFilter.getTypes().size(); i++) {
      if (i != 0) {
        query.append("OR ");
      }
      query.append("type LIKE '%").append(exercisesFilter.getTypes().get(i)).append("%' ");
    }

    return query.toString();
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
