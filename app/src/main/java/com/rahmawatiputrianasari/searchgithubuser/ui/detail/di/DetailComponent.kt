package com.rahmawatiputrianasari.searchgithubuser.ui.detail.di

import com.rahmawatiputrianasari.searchgithubuser.app.di.ActivityScope
import com.rahmawatiputrianasari.searchgithubuser.app.di.AppComponent
import com.rahmawatiputrianasari.searchgithubuser.ui.detail.DetailActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(modules = [DetailModule::class], dependencies = [AppComponent::class])
interface DetailComponent {

    fun inject(target: DetailActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: DetailActivity): Builder

        fun appComponent(component: AppComponent): Builder

        fun plus(module: DetailModule): Builder

        fun build(): DetailComponent
    }
}