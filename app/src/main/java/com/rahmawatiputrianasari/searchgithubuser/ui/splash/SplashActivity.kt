package com.rahmawatiputrianasari.searchgithubuser.ui.splash

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.rahmawatiputrianasari.searchgithubuser.R
import com.rahmawatiputrianasari.searchgithubuser.app.App
import com.rahmawatiputrianasari.searchgithubuser.ui.splash.di.DaggerSplashComponent
import com.rahmawatiputrianasari.searchgithubuser.ui.splash.di.SplashComponent
import com.rahmawatiputrianasari.searchgithubuser.ui.splash.di.SplashModule
import javax.inject.Inject


class SplashActivity : AppCompatActivity(), SplashContract.View {

    @Inject
    lateinit var presenter: SplashPresenter

    val component: SplashComponent by lazy {
        DaggerSplashComponent.builder()
            .appComponent((application as App).component)
            .activity(this)
            .plus(SplashModule())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        component.inject(this)
        presenter.bindView(this)
        presenter.onViewCreated()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }
}
