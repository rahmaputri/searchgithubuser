package com.rahmawatiputrianasari.searchgithubuser.ui.main

import com.rahmawatiputrianasari.searchgithubuser.app.model.SearchResponse
import com.rahmawatiputrianasari.searchgithubuser.ui.main.data.MainRepo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class MainInteractor(
    private val repo: MainRepo
) : MainContract.Interactor {

    private val compositeDisposable = CompositeDisposable()

    override fun getUsers(
        onSuccess: (SearchResponse) -> Unit,
        onError: (Throwable) -> Unit,
        username: String,
        page: String
    ) {
        val disposable = repo.getUsers(username, page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnError(onError)
            .doOnSuccess(onSuccess)
            .subscribe({ System.out.println(it) },
                { System.out.println(it) })

        compositeDisposable.add(disposable)
    }

    fun dispose() = compositeDisposable.dispose()
}

