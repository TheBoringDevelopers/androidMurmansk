package com.theboringdevelopers.smartmurmansk.data.model.bean

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Image(
    val id: Long,
    val content: ByteArray
) : Parcelable