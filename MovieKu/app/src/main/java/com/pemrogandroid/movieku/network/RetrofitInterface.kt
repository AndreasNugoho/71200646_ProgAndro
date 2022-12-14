package com.pemrogandroid.movieku.network


import com.pemrogandroid.movieku.model.TmdbResponse
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitInterface {

  @GET("search/movie")
  fun searchMovie(
    @Query("api_key") api_key: String,
    @Query("query") q: String
  ): Observable<TmdbResponse>
}
