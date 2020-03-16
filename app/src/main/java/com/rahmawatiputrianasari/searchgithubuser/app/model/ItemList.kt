package com.rahmawatiputrianasari.searchgithubuser.app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ItemList(
    @SerializedName("") var site: String? = null
) : Parcelable