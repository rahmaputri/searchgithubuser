package com.rahmawatiputrianasari.searchgithubuser.app.di

import com.rahmawatiputrianasari.searchgithubuser.app.App
import com.rahmawatiputrianasari.searchgithubuser.utils.NetworkInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    companion object {
        private const val SERVER_URL = "https://api.github.com/"
    }

    @Provides
    @Singleton
    fun retrofit() = Retrofit.Builder()
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(SERVER_URL)
        .client(
            OkHttpClient.Builder().addInterceptor(NetworkInterceptor(App.ctx))
                .build()
        )
        .build()
}