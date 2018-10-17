package com.jds.fitnessjunkiess.getfitapp.data.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.Transformations

import com.jds.fitnessjunkiess.getfitapp.data.dataModels.Exercise
import com.jds.fitnessjunkiess.getfitapp.data.repositories.ExercisesRepository
import com.jds.fitnessjunkiess.getfitapp.data.pojo.ExercisesFilters

class ExerciseViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: ExercisesRepository = ExercisesRepository(application)
    internal var data = MediatorLiveData<List<Exercise>>()

    private val filterMutableLiveData: MutableLiveData<ExercisesFilters> = MutableLiveData()

    val exercisesFilter: ExercisesFilters?
        get() = this.filterMutableLiveData.value

    init {
        val filter = ExercisesFilters()
        this.filterMutableLiveData.value = filter

        val source = Transformations.switchMap(
            this.filterMutableLiveData
        ) {repository.filterSelect(this.filterMutableLiveData.value!!)}

        this.data.addSource(source) {this.data.setValue(it)}
    }

    fun selectData(): LiveData<List<Exercise>> {
        return data
    }

    fun select(exerciseId: Int): LiveData<Exercise> {
        return this.repository.select(exerciseId)
    }

    fun insert(exercise: Exercise) {
        this.repository.insert(exercise)
    }

    fun setFilterMutableLiveData(exercisesFilter: ExercisesFilters) {
        this.filterMutableLiveData.value = exercisesFilter
    }
}
