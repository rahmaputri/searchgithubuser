package com.rahmawatiputrianasari.searchgithubuser.ui.main.di

import com.rahmawatiputrianasari.searchgithubuser.app.di.ActivityScope
import com.rahmawatiputrianasari.searchgithubuser.app.di.AppComponent
import com.rahmawatiputrianasari.searchgithubuser.ui.main.MainActivity
import dagger.BindsInstance
import dagger.Component

@ActivityScope
@Component(modules = [MainModule::class], dependencies = [AppComponent::class])
interface MainComponent {

    fun inject(target: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun activity(activity: MainActivity): Builder

        fun appComponent(component: AppComponent): Builder

        fun plus(module: MainModule): Builder

        fun build(): MainComponent
    }
}