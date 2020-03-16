package com.rahmawatiputrianasari.searchgithubuser.ui.main.api

import com.rahmawatiputrianasari.searchgithubuser.app.model.Joke
import io.reactivex.Single
import retrofit2.http.GET

interface MainApi {

    @GET("api/random")
    fun getData(): Single<List<Joke>>
}