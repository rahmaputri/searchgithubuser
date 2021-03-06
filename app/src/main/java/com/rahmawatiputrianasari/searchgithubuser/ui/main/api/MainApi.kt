package com.rahmawatiputrianasari.searchgithubuser.ui.main.api

import com.rahmawatiputrianasari.searchgithubuser.app.model.SearchResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("search/users?")
    fun getUsers(@Query("q") username: String, @Query("page") page: String): Single<SearchResponse>
}