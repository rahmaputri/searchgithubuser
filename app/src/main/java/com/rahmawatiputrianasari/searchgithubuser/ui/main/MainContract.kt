package com.rahmawatiputrianasari.searchgithubuser.ui.main

import com.rahmawatiputrianasari.searchgithubuser.app.model.SearchResponse
import io.reactivex.Single

interface MainContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun publishUsers(data: SearchResponse)
        fun showMessage(msg: String)
    }

    interface Presenter {

        fun bindView(view: MainContract.View)

        fun unbindView()

        fun searchUser(username: String, page: String)

        fun onBackClicked()
    }

    interface Interactor {
        fun getUsers(
            onSuccess: (SearchResponse) -> Unit,
            onError: (Throwable) -> Unit,
            username: String,
            page: String
        )
    }

    interface Router {
        fun finish()
    }

    interface Repo {
        fun getUsers(username: String, page: String): Single<SearchResponse>
    }
}