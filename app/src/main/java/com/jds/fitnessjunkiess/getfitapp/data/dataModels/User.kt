package com.jds.fitnessjunkiess.getfitapp.data.dataModels

import android.arch.persistence.room.Entity
import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
class User(
    @Expose
    var id: Int = 0,
    @Expose
    var email: String = "",
    @Expose
    var username: String = "",
    @Expose
    var password: String = ""
) : Parcelable
