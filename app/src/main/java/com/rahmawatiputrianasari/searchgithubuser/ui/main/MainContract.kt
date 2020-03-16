package com.rahmawatiputrianasari.searchgithubuser.ui.main

import com.rahmawatiputrianasari.searchgithubuser.app.model.Joke
import com.rahmawatiputrianasari.searchgithubuser.app.model.SearchResponse
import io.reactivex.Single

interface MainContract {
    interface View {
        fun showLoading()
        fun hideLoading()
        fun publishData(data: List<Joke>)
        fun publishUsers(data: SearchResponse)
        fun showMessage(msg: String)
    }

    interface Presenter {

        fun bindView(view: MainContract.View)

        fun unbindView()

        fun onViewCreated()

        fun searchUser(username: String, page: String)

        fun onItemClicked(joke: Joke)

        fun onBackClicked()
    }

    interface Interactor {
        fun getJokes(onSuccess: (List<Joke>) -> Unit, onError: (Throwable) -> Unit)
        fun getUsers(
            onSuccess: (SearchResponse) -> Unit,
            onError: (Throwable) -> Unit,
            username: String,
            page: String
        )
    }

    interface Router {
        fun finish()
        fun openFullJoke(joke: Joke)
    }

    interface Repo {
        fun getJokes(): Single<List<Joke>>
        fun getUsers(username: String, page: String): Single<SearchResponse>
    }
}