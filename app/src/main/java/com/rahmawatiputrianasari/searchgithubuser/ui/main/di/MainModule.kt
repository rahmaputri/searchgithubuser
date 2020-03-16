package com.rahmawatiputrianasari.searchgithubuser.ui.main.di

import com.rahmawatiputrianasari.searchgithubuser.app.di.ActivityScope
import com.rahmawatiputrianasari.searchgithubuser.ui.main.*
import com.rahmawatiputrianasari.searchgithubuser.ui.main.api.MainApi
import com.rahmawatiputrianasari.searchgithubuser.ui.main.data.MainRepo
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {

    @Provides
    @ActivityScope
    fun api(retrofit: Retrofit) = retrofit.create(MainApi::class.java)

    @Provides
    @ActivityScope
    fun repository(api: MainApi) = MainRepo(api)

    @Provides
    @ActivityScope
    fun router(activity: MainActivity): MainContract.Router = MainRouter(activity)

    @Provides
    @ActivityScope
    fun presenter(router: MainContract.Router, interactor: MainInteractor) =
        MainPresenter(router, interactor)

    @Provides
    @ActivityScope
    fun interactor(repository: MainRepo) =
        MainInteractor(repository)
}