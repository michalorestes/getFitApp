package com.jds.fitnessjunkiess.getfitapp.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.WorkoutExerciseAssignment;

@Dao
public interface WorkoutExercisesAssignmentDao {
  @Insert
  void insert(WorkoutExerciseAssignment workoutExerciseAssignment);
  @Update
  void update(WorkoutExerciseAssignment workout);
}
