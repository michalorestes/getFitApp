package com.jds.fitnessjunkiess.getfitapp.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.MuscleGroup;

import java.util.List;

@Dao
public interface MuscleGroupDao {
  @Query("SELECT * FROM muscle_groups")
  LiveData<List<MuscleGroup>> selectAll();
  @Insert
  void insert(MuscleGroup muscleGroup);
  @Update
  void update(MuscleGroup muscleGroup);
  @Delete
  void delete(MuscleGroup muscleGroup);
  @Query("DELETE FROM muscle_groups")
  void deleteAll();
}
