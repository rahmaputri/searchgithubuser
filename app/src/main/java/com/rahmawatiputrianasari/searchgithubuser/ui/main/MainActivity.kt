package com.rahmawatiputrianasari.searchgithubuser.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
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
import com.rahmawatiputrianasari.searchgithubuser.utils.NetworkEvent
import com.rahmawatiputrianasari.searchgithubuser.utils.NetworkState
import io.reactivex.functions.Consumer
import kotlinx.android.synthetic.main.activity_main.*
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

    var page: Int = 1
    private var loading = true

    private lateinit var mLayoutManager: LinearLayoutManager
    private lateinit var mAdapter: MainAdapter2
    var created = true
    var firstInit = false


    private val PAGE_START = 0
    private var isLoading = false
    private var isLastPage = false
    //    private val TOTAL_PAGES = 3
    private val currentPage = PAGE_START

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
        presenter.searchUser("r", page.toString())

        NetworkEvent.register(this, Consumer {
            when (it) {
                NetworkState.NO_INTERNET -> displayErrorDialog(
                    getString(R.string.generic_no_internet_title),
                    getString(R.string.generic_no_internet_desc)
                )

                NetworkState.NO_RESPONSE -> displayErrorDialog(
                    getString(R.string.generic_http_error_title),
                    getString(R.string.generic_http_error_desc)
                )

                NetworkState.UNAUTHORISED -> {
                    //redirect to login screen - if session expired
                    Toast.makeText(
                        applicationContext,
                        R.string.error_login_expired,
                        Toast.LENGTH_LONG
                    ).show()
                    val intent = Intent(this, MainActivity::class.java)
                    intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(intent)
                }
            }
        })

        firstInit = true

    }

    fun displayErrorDialog(
        title: String,
        desc: String
    ) {
        if (!(this).isFinishing) { //show dialog

            AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(desc)
                .setCancelable(false)
                .setPositiveButton(
                    "Close App"
                ) { dialogInterface, i -> presenter.onBackClicked() }
                .show()

        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unbindView()
    }

    override fun showLoading() {
//        recyclerView.visibility = View.GONE
//        progressBar.visibility = View.VISIBLE
        loading = true
    }

    override fun hideLoading() {
//        recyclerView.visibility = View.VISIBLE
//        progressBar.visibility = View.GONE
        loading = false
    }

    override fun publishData(data: List<Joke>) {
        val adapter = MainAdapter(data, object : MainAdapter.JokeListener {
            override fun onItemClick(joke: Joke) {
                presenter.onItemClicked(joke)
            }
        })
        recyclerView.adapter = mAdapter
    }


    override fun publishUsers(data: SearchResponse) {
        if (created) {
            mAdapter = MainAdapter2(data.items!!, object : MainAdapter2.JokeListener {
                override fun onItemClick(joke: Joke) {
//                presenter.onItemClicked(joke)
                }
            })
            Log.v("...", "total jumlah " + data.totalCount)


            recyclerView.setLayoutManager(mLayoutManager)
            recyclerView.addOnScrollListener(scrollData()!!)
//            recyclerView.addOnScrollListener(object : PaginationScrollListener(mLayoutManager) {
//                protected override fun loadMoreItems() {
//                    loading = true
//                    page += 1
//                    // mocking network delay for API call
//                    Handler().postDelayed(Runnable { loadNextPage() }, 1000)
//                }
//
////                override val totalPageCount: Int
////                    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
////                override val isLastPage: Boolean
////                    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
////                override val isLoading: Boolean
////                    get() = TODO("not implemented") //To change initializer of created properties use File | Settings | File Templates.
//
//                override fun getTotalPageCount(): Int {
//                    return TOTAL_PAGES
//                }
//
//                fun isLastPage(): Boolean {
//                    return isLastPage
//                }
//
//                fun isLoading(): Boolean {
//                    return isLoading
//                }
//
//            })

            recyclerView.adapter = mAdapter
        } else {
//            mAdapter.notifyDataSetChanged()
            mAdapter.removeLoadingFooter()
            mAdapter.addAll(data.items!!)
        }


    }


    private fun scrollData(): EndlessOnScrollListener? {
        return object : EndlessOnScrollListener() {
            override fun onLoadMore() {
                mAdapter.addLoadingFooter()
                page = page + 1
                presenter.searchUser("r", page.toString())
            }
        }
    }

    override fun showMessage(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }

    private fun initView() {
        val manager = LinearLayoutManager(this).apply { orientation = LinearLayoutManager.VERTICAL }
        mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = manager
    }


}

abstract class EndlessOnScrollListener : RecyclerView.OnScrollListener() {
    private var mPreviousTotal = 0
    private var mLoading = true
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = recyclerView.childCount
        val totalItemCount = recyclerView.layoutManager!!.itemCount
        val firstVisibleItem =
            (recyclerView.layoutManager as LinearLayoutManager?)!!.findFirstVisibleItemPosition()
        if (mLoading) {
            if (totalItemCount > mPreviousTotal) {
                mLoading = false
                mPreviousTotal = totalItemCount
            }
        }
        val visibleThreshold = 5
        if (!mLoading && totalItemCount - visibleItemCount
            <= firstVisibleItem + visibleThreshold
        ) {
            onLoadMore()
            mLoading = true
        }
    }

    abstract fun onLoadMore()
}