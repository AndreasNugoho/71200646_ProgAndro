package com.pemrogandroid.movieku.ui.search

import com.pemrogandroid.movieku.model.TmdbResponse

class SearchContract {

    interface PresenterInterface {

        fun getSearchResults(query: String)
        fun stop()
    }

    interface ViewInterface {

        fun showToast(string: String)
        fun displayResult(tmdbResponse: TmdbResponse)
        fun displayError(string: String)
    }
}