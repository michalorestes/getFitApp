package com.jds.fitnessjunkiess.getfitapp.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout;

import java.util.List;

@Dao()
public interface WorkoutDao {
  //TODO: select based on user id
  @Query("SELECT * FROM workouts")
  LiveData<List<Workout>> selectAll();
  @Query("SELECT * FROM workouts WHERE id = :id")
  Workout selectOne(final int id);
  @Insert
  void insert(Workout workout);
  @Update
  void update(Workout workout);
  @Delete
  void delete(Workout workout);
  @Query("DELETE FROM workouts")
  void deleteAll();
}
