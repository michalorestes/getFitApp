package com.jds.fitnessjunkiess.getfitapp.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.jds.fitnessjunkiess.getfitapp.Entities.WorkoutExercise;

import java.util.List;

@Dao
public interface WorkoutExerciseDao {
  @Query("SELECT * FROM workout_exercises WHERE workoutId = :workoutId")
  LiveData<List<WorkoutExercise>> selectAll(final int workoutId);
  @Insert
  void insert(WorkoutExercise workout);
  @Update
  void update(WorkoutExercise workout);
  @Delete
  void delete(WorkoutExercise workout);
}
