package com.jds.fitnessjunkiess.getfitapp.DAO;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jds.fitnessjunkiess.getfitapp.Entities.Workout;

import java.util.List;

@Dao()
public interface WorkoutDao {
  @Query("SELECT * FROM workouts")
  LiveData<List<Workout>> selectAll();
  @Insert
  void insert(Workout workout);
  @Update
  void update(Workout workout);
  @Delete
  void delete(Workout workout);
}
