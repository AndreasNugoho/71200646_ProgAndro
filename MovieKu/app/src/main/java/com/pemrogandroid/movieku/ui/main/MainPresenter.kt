package com.pemrogandroid.movieku.ui.main

import android.util.Log
import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.repository.LocalDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import java.util.HashSet

class MainPresenter(
    private var viewInterface: MainContract.ViewInterface,
    private var dataSource: LocalDataSource
) : MainContract.PresenterInterface {
    private val TAG = "MainPresenter"
    private val compositeDisposable = CompositeDisposable()

    val myMoviesObservable: Observable<List<Movie>>
        get() = dataSource.allMovies

    val observer: DisposableObserver<List<Movie>>
        get() = object : DisposableObserver<List<Movie>>() {

            override fun onNext(movieList: List<Movie>) {
                if (movieList.isEmpty()) {
                    viewInterface.displayNoMovies()
                } else {
                    viewInterface.displayMovies(movieList)
                }
            }

            override fun onError(@NonNull e: Throwable) {
                Log.d(TAG, "Error$e")
                e.printStackTrace()
                viewInterface.displayError("Gagal mengambil data")
            }

            override fun onComplete() {
            }
        }

    override fun getMyMoviesList() {
        val myMoviesDisposable = myMoviesObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)

        compositeDisposable.add(myMoviesDisposable)
    }


    override fun onDeleteTapped(selectedMovies: HashSet<Movie>) {
        for (movie in selectedMovies) {
            dataSource.delete(movie)
        }
        if (selectedMovies.size == 1) {
            viewInterface.showToast("Film berhasil di hapus")
        } else if (selectedMovies.size > 1) {
            viewInterface.showToast("Film berhasil di hapus")
        }
    }

    override fun stop() {
        compositeDisposable.clear()
    }
}