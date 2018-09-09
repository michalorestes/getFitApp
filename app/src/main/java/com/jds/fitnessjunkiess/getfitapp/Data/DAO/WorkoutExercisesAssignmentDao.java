package com.jds.fitnessjunkiess.getfitapp.Data.DAO;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;

import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.WorkoutExerciseAssignment;

@Dao
public interface WorkoutExercisesAssignmentDao {
  @Insert
  void insert(WorkoutExerciseAssignment workoutExerciseAssignment);
}
