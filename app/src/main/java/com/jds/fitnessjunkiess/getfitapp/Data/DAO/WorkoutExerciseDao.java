package com.jds.fitnessjunkiess.getfitapp.Data.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.jds.fitnessjunkiess.getfitapp.Data.DataModels.WorkoutExerciseAssignment;
import com.jds.fitnessjunkiess.getfitapp.Pojo.WorkoutExercise;
import java.util.List;

@Dao
public interface WorkoutExerciseDao {
  @Query("SELECT * FROM WorkoutExerciseAssignment")
  LiveData<List<WorkoutExerciseAssignment>> selectAll();
  @Insert
  void insert(WorkoutExerciseAssignment workout);
  @Update
  void update(WorkoutExerciseAssignment workout);
  @Delete
  void delete(WorkoutExerciseAssignment workout);
  @Query("DELETE FROM WorkoutExerciseAssignment")
  void deleteAll();

  @Query("SELECT " +
      "e.id AS exerciseId, e.name, e.picture, e.instructions, e.type AS exerciseType, " +
      "e.muscleGroups AS exerciseMuscleGroups, wea.id, wea.userId, wea.workoutId, wea.lengthTime, wea.reps, " +
      "wea.restTime, wea.sprintTime, wea.sets " +
      "FROM WorkoutExerciseAssignment wea " +
      "INNER JOIN exercises e ON wea.exerciseId = e.id " +
      "WHERE wea.workoutId = :workoutId")
  LiveData<List<WorkoutExercise>> getWorkoutExercises(int workoutId);
}
