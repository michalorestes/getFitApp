package com.jds.fitnessjunkiess.getfitapp.Data.Repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import com.jds.fitnessjunkiess.getfitapp.Data.DAO.ExerciseDao;
import com.jds.fitnessjunkiess.getfitapp.Data.DAO.MuscleGroupDao;
import com.jds.fitnessjunkiess.getfitapp.Data.Database.WorkoutRoomDatabase;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.Exercise;
import com.jds.fitnessjunkiess.getfitapp.Data.Entities.MuscleGroup;
import com.jds.fitnessjunkiess.getfitapp.Pojo.WorkoutExercise;

import java.util.List;

public class MuscleGroupRepository {
  private MuscleGroupDao dao;
  private LiveData<List<MuscleGroup>> data;

  public MuscleGroupRepository(Application context) {
    WorkoutRoomDatabase database = WorkoutRoomDatabase.getDb(context);
    this.dao = database.muscleGroupDao();
  }

  public LiveData<List<MuscleGroup>> selectAll() {
    return this.dao.selectAll();
  }

  public void insert(MuscleGroup muscleGroup) {
    new insertAsyncTask(this.dao).execute(muscleGroup);
  }

  private static class insertAsyncTask extends AsyncTask<MuscleGroup, Void, Void> {

    private MuscleGroupDao muscleGroupDao;

    insertAsyncTask(MuscleGroupDao muscleGroupDao) {
      muscleGroupDao = muscleGroupDao;
    }

    @Override
    protected Void doInBackground(final MuscleGroup... params) {
      muscleGroupDao.insert(params[0]);
      return null;
    }
  }
}
