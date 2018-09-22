package com.jds.fitnessjunkiess.getfitapp.data.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.db.SimpleSQLiteQuery;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.RawQuery;
import android.arch.persistence.room.Update;
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise;
import java.util.List;

@Dao
public interface ExerciseDao {
  @RawQuery(observedEntities = Exercise.class)
  LiveData<List<Exercise>> filterSelect(SimpleSQLiteQuery simpleSQLiteQuery);
  @Query("SELECT * FROM exercises")
  LiveData<List<Exercise>> selectAll();
  @Insert
  void insert(Exercise exercise);
  @Update
  void update(Exercise exercise);
  @Delete
  void delete(Exercise exercise);
  @Query("DELETE FROM exercises")
  void deleteAll();
}
