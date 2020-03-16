package com.rahmawatiputrianasari.searchgithubuser.ui.main

import com.rahmawatiputrianasari.searchgithubuser.app.model.Joke

class MainPresenter(private val router: MainContract.Router, private val interactor: MainInteractor) :
    MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
        interactor.dispose()
    }

    override fun onViewCreated() {
        view?.showLoading()
        interactor.getJokes({
            view?.hideLoading()
            view?.publishData(it)
        }, this::onError)
    }

    override fun onItemClicked(joke: Joke) {
        router.openFullJoke(joke)
    }

    override fun onBackClicked() {
        router.finish()
    }

    private fun onError(error: Throwable) {
        view?.hideLoading()
        error.message?.let { view?.showMessage(it) }
    }
}