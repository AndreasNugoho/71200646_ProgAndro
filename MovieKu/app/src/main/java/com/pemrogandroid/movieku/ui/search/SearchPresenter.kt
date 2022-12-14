package com.pemrogandroid.movieku.ui.search

import com.pemrogandroid.movieku.model.TmdbResponse
import com.pemrogandroid.movieku.repository.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class SearchPresenter(
    private var viewInterface: SearchContract.ViewInterface,
    private var dataSource: RemoteDataSource
) : SearchContract.PresenterInterface {

    private val compositeDisposable = CompositeDisposable()

    val searchResultsObservable: (String) -> Observable<TmdbResponse> =
        { query -> dataSource.searchResultsObservable(query) }

    val observer: DisposableObserver<TmdbResponse>
        get() = object : DisposableObserver<TmdbResponse>() {

            override fun onNext(@NonNull tmdbResponse: TmdbResponse) {
                viewInterface.displayResult(tmdbResponse)
            }

            override fun onError(@NonNull e: Throwable) {
                e.printStackTrace()
                viewInterface.displayError("Gagal mengambil data")
            }

            override fun onComplete() {}
        }
 
    override fun getSearchResults(query: String) {
        val searchResultsDisposable = searchResultsObservable(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(observer)

        compositeDisposable.add(searchResultsDisposable)
    }

    override fun stop() {
        compositeDisposable.clear()
    }
}