package com.pemrogandroid.movieku.ui.main

import com.pemrogandroid.movieku.model.Movie
import java.util.HashSet

class MainContract {

    interface PresenterInterface {
        fun getMyMoviesList()
        fun onDeleteTapped(selectedMovies: HashSet<Movie>)
        fun stop()
    }

    interface ViewInterface {
        fun displayMovies(movieList: List<Movie>)
        fun displayNoMovies()
        fun showToast(string: String)
        fun displayError(string: String)
    }
}