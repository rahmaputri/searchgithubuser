package com.rahmawatiputrianasari.searchgithubuser.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.rahmawatiputrianasari.searchgithubuser.R
import com.rahmawatiputrianasari.searchgithubuser.app.App
import com.rahmawatiputrianasari.searchgithubuser.app.model.Joke
import com.rahmawatiputrianasari.searchgithubuser.app.model.SearchResponse
import com.rahmawatiputrianasari.searchgithubuser.ui.main.adapter.MainAdapter
import com.rahmawatiputrianasari.searchgithubuser.ui.main.adapter.MainAdapter2
import com.rahmawatiputrianasari.searchgithubuser.ui.main.di.DaggerMainComponent
import com.rahmawatiputrianasari.searchgithubuser.ui.main.di.MainComponent
import com.rahmawatiputrianasari.searchgithubuser.ui.main.di.MainModule
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject


class MainActivity : AppCompatActivity(), MainContract.View {

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, MainActivity::class.java)
            context.startActivity(intent)
        }
    }

    @Inject
    lateinit var presenter: MainPresenter
//    @Inject
//    lateinit var username: String
//    @Inject
//    lateinit var page: String

    val component: MainComponent by lazy {
        DaggerMainComponent.builder()
            .appComponent((application as App).component)
            .activity(this)
            .plus(MainModule())
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initView()
        component.inject(this)
        presenter.bindView(this)
//        presenter.onViewCreated()
        presenter.searchUser("rahmaputri","1")
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }

    override fun showLoading() {
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        recyclerView.visibility = View.VISIBLE
        progressBar.visibility = View.GONE
    }

    override fun publishData(data: List<Joke>) {
        val adapter = MainAdapter(data, object : MainAdapter.JokeListener {
            override fun onItemClick(joke: Joke) {
                presenter.onItemClicked(joke)
            }
        })
        recyclerView.adapter = adapter
    }

    override fun publishUsers(data: SearchResponse) {
//        print("datsa")

        val adapter = MainAdapter2(data.items!!, object : MainAdapter2.JokeListener {
            override fun onItemClick(joke: Joke) {
//                presenter.onItemClicked(joke)
            }
        })
        recyclerView.adapter = adapter

    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        val manager = LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }
        recyclerView.layoutManager = manager
        toolbar.setTitle(R.string.main_title)
        toolbar.setTitleTextColor(ContextCompat.getColor(this, android.R.color.white))
        toolbar.setNavigationOnClickListener { presenter.onBackClicked() }
    }
}