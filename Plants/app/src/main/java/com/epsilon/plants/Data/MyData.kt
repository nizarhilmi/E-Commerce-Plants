package com.epsilon.plants.Data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MyData(
    var name: String,
    var price: String,
    var photo: String,
) : Parcelable