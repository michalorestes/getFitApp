package com.jds.fitnessjunkiess.getfitapp.data.dao

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.MuscleGroup

@Dao
interface MuscleGroupDao {
    @Query("SELECT * FROM muscle_groups")
    fun selectAll(): LiveData<List<MuscleGroup>>

    @Insert
    fun insert(muscleGroup: MuscleGroup)

    @Update
    fun update(muscleGroup: MuscleGroup)

    @Delete
    fun delete(muscleGroup: MuscleGroup)

    @Query("DELETE FROM muscle_groups")
    fun deleteAll()
}
