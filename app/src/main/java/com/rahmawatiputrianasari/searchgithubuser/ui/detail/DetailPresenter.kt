package com.rahmawatiputrianasari.searchgithubuser.ui.detail

import com.rahmawatiputrianasari.searchgithubuser.app.model.Joke

class DetailPresenter(private val router: DetailContract.Router) : DetailContract.Presenter {

    private var view: DetailContract.View? = null

    override fun bindView(view: DetailContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
    }

    override fun onViewCreated(joke: Joke) {
        view?.publishData(joke)
    }

    override fun onEmptyData(msg: Int) {
        view?.showMessage(msg)
        router.finish()
    }

    override fun onBackClicked() {
        router.finish()
    }
}