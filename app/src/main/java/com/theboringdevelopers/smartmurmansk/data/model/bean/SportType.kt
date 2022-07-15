package com.theboringdevelopers.smartmurmansk.data.model.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SportType(
    val id: Long? = null,
    val name: String
) : Parcelable