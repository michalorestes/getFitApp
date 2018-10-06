package com.jds.fitnessjunkiess.getfitapp.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.db.SimpleSQLiteQuery
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.RawQuery
import android.arch.persistence.room.Update
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise

@Dao
interface ExerciseDao {
    @RawQuery(observedEntities = [Exercise::class])
    fun filterSelect(simpleSQLiteQuery: SimpleSQLiteQuery): LiveData<List<Exercise>>

    @Query("SELECT * FROM exercises")
    fun selectAll(): LiveData<List<Exercise>>

    @Insert
    fun insert(exercise: Exercise)

    @Update
    fun update(exercise: Exercise)

    @Delete
    fun delete(exercise: Exercise)

    @Query("DELETE FROM exercises")
    fun deleteAll()
}
