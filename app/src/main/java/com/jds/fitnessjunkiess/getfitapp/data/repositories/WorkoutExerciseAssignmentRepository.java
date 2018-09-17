package com.jds.fitnessjunkiess.getfitapp.data.repositories;

import android.app.Application;
import android.os.AsyncTask;

import com.jds.fitnessjunkiess.getfitapp.data.dao.WorkoutExercisesAssignmentDao;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment;
import com.jds.fitnessjunkiess.getfitapp.data.database.WorkoutRoomDatabase;

public class WorkoutExerciseAssignmentRepository {

  private WorkoutExercisesAssignmentDao workoutExercisesAssignmentDao;

  public WorkoutExerciseAssignmentRepository(Application application) {
    WorkoutRoomDatabase workoutRoomDatabase = WorkoutRoomDatabase.getDb(application);
    this.workoutExercisesAssignmentDao = workoutRoomDatabase.workoutExercisesAssignmentDao();
  }

  public void insert(WorkoutExerciseAssignment workoutExerciseAssignment) {
    new insertAsyncTask(this.workoutExercisesAssignmentDao).execute(workoutExerciseAssignment);
  }

  private static class insertAsyncTask extends AsyncTask<WorkoutExerciseAssignment, Void, Void> {

    private WorkoutExercisesAssignmentDao mAsyncTaskDao;

    insertAsyncTask(WorkoutExercisesAssignmentDao dao) {
      mAsyncTaskDao = dao;
    }

    @Override
    protected Void doInBackground(final WorkoutExerciseAssignment ... params) {
      mAsyncTaskDao.insert(params[0]);
      return null;
    }
  }
}
