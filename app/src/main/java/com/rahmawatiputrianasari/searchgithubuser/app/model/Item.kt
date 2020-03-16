package com.rahmawatiputrianasari.searchgithubuser.app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Item(
    @SerializedName("login") var name: String? = null,
    @SerializedName("avatar_url") var avatarUrl: String? = null
) : Parcelable