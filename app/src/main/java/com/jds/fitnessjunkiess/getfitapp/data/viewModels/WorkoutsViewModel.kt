package com.jds.fitnessjunkiess.getfitapp.data.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Workout
import com.jds.fitnessjunkiess.getfitapp.data.repositories.WorkoutsRepository

class WorkoutsViewModel(application: Application) : AndroidViewModel(application) {
    internal var repository: WorkoutsRepository = WorkoutsRepository(application)
    var data: LiveData<List<Workout>>
        internal set

    init {
        this.data = this.repository.data
    }

    fun insert(workout: Workout) {
        this.repository.insert(workout)
    }
}
