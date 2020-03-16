package com.rahmawatiputrianasari.searchgithubuser.app.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class SearchResponse(
    @SerializedName("total_count") var totalCount: String? = null,
    @SerializedName("incomplete_results") var incompleteResponse: Boolean? = null,
    @SerializedName("items") var items: ArrayList<Item>? = null
) : Parcelable