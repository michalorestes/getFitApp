package com.jds.fitnessjunkiess.getfitapp.data.pojo

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ExercisesFilters(
    val muscleGroups: ArrayList<String> = ArrayList(),
    val types: ArrayList<String> = ArrayList(),
    var isCustom: Boolean? = null
) : Parcelable
