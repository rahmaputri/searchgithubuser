package com.rahmawatiputrianasari.searchgithubuser.ui.detail.di

import com.rahmawatiputrianasari.searchgithubuser.app.di.ActivityScope
import com.rahmawatiputrianasari.searchgithubuser.ui.detail.DetailActivity
import com.rahmawatiputrianasari.searchgithubuser.ui.detail.DetailContract
import com.rahmawatiputrianasari.searchgithubuser.ui.detail.DetailPresenter
import com.rahmawatiputrianasari.searchgithubuser.ui.detail.DetailRouter
import dagger.Module
import dagger.Provides

@Module
class DetailModule {

    @Provides
    @ActivityScope
    fun router(activity: DetailActivity): DetailContract.Router = DetailRouter(activity)

    @Provides
    @ActivityScope
    fun presenter(router: DetailContract.Router) = DetailPresenter(router)
}