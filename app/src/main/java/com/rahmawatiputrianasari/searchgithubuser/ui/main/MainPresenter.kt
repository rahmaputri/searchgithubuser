package com.rahmawatiputrianasari.searchgithubuser.ui.main

class MainPresenter(
    private val router: MainContract.Router,
    private val interactor: MainInteractor
) :
    MainContract.Presenter {

    private var view: MainContract.View? = null

    override fun bindView(view: MainContract.View) {
        this.view = view
    }

    override fun unbindView() {
        view = null
        interactor.dispose()
    }


    override fun searchUser(username: String, page: String) {
        view?.showLoading()
        interactor.getUsers({
            view?.hideLoading()
            view?.publishUsers(it)
        }, this::onError, username, page)
    }

    override fun onBackClicked() {
        router.finish()
    }

    private fun onError(error: Throwable) {
        view?.hideLoading()
        error.message?.let { view?.showMessage(it) }
    }
}