package com.pemrogandroid.movieku.repository

import com.pemrogandroid.movieku.model.TmdbResponse
import com.pemrogandroid.movieku.network.RetrofitClient
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class RemoteDataSource {

    open fun searchResultsObservable(query: String): Observable<TmdbResponse> {
        return RetrofitClient.moviesApi
            .searchMovie(RetrofitClient.API_KEY, query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}