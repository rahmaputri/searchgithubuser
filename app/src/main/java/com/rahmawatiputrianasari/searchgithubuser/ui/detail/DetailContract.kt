package com.rahmawatiputrianasari.searchgithubuser.ui.detail

import com.rahmawatiputrianasari.searchgithubuser.app.model.Joke
import io.reactivex.Single

interface DetailContract {
    interface View {
        fun publishData(joke: Joke)

        fun showMessage(msg: Int)
    }

    interface Presenter {
        fun bindView(view: DetailContract.View)

        fun unbindView()

        fun onViewCreated(joke: Joke)

        fun onBackClicked()

        fun onEmptyData(msg: Int)
    }

    interface Router {
        fun finish()
    }
}