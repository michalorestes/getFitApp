package com.jds.fitnessjunkiess.getfitapp.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout

@Dao
interface WorkoutDao {
    //TODO: select based on user id
    @Query("SELECT * FROM workouts")
    fun selectAll(): LiveData<List<Workout>>

    @Query("SELECT * FROM workouts WHERE id = :id")
    fun selectOne(id: Int): Workout

    @Insert
    fun insert(workout: Workout)

    @Update
    fun update(workout: Workout)

    @Delete
    fun delete(workout: Workout)

    @Query("DELETE FROM workouts")
    fun deleteAll()
}
