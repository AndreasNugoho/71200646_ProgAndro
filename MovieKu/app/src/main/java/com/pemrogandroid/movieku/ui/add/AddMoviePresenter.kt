package com.pemrogandroid.movieku.ui.add

import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.repository.LocalDataSource

class AddMoviePresenter(
    private var viewInterface: AddMovieContract.ViewInterface,
    private var dataSource: LocalDataSource
) : AddMovieContract.PresenterInterface {

    override fun addMovie(title: String, releaseDate: String, posterPath: String) {
        if (title.isEmpty()) {
            viewInterface.displayError("Judul film tidak boleh kosong")
        } else {
            val movie = Movie(title, releaseDate, posterPath)
            dataSource.insert(movie)
            viewInterface.returnToMain()
        }
    }
}