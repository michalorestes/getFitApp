package com.jds.fitnessjunkiess.getfitapp.data.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.jds.fitnessjunkiess.getfitapp.data.dataModels.ExerciseLog
import com.jds.fitnessjunkiess.getfitapp.data.repositories.ExerciseLogRepository

class ExerciseLogViewModel(application: Application): AndroidViewModel(application) {

    private val repository: ExerciseLogRepository = ExerciseLogRepository(application)

    fun selectOne(logId: Int): LiveData<ExerciseLog> {
        return this.repository.selectOne(logId)
    }
}