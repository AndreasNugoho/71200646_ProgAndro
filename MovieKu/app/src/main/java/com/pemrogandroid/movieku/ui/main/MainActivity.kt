package com.pemrogandroid.movieku.ui.main

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.pemrogandroid.movieku.R
import com.pemrogandroid.movieku.ui.add.AddMovieActivity
import com.pemrogandroid.movieku.model.Movie
import com.pemrogandroid.movieku.repository.LocalDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.annotations.NonNull
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers

class MainActivity : AppCompatActivity(), MainContract.ViewInterface {

    private lateinit var moviesRecyclerView: RecyclerView
    private lateinit var adapter: MainAdapter
    private lateinit var fab: FloatingActionButton
    private lateinit var noMoviesLayout: LinearLayout

    private lateinit var mainPresenter: MainContract.PresenterInterface

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setupPresenter()
        setupViews()
    }

    override fun onStart() {
        super.onStart()
        mainPresenter.getMyMoviesList()
    }

    override fun onStop() {
        super.onStop()
        mainPresenter.stop()
    }

    private fun setupPresenter() {
        val dataSource = LocalDataSource(application)
        mainPresenter = MainPresenter(this, dataSource)
    }

    private fun setupViews() {
        moviesRecyclerView = findViewById(R.id.movies_recyclerview)
        moviesRecyclerView.layoutManager = LinearLayoutManager(this)
        fab = findViewById(R.id.fab)
        noMoviesLayout = findViewById(R.id.no_movies_layout)
        supportActionBar?.title = "Daftar Tontonan"
    }

    override fun displayMovies(movieList: List<Movie>) {
        adapter = MainAdapter(movieList, this@MainActivity)
        moviesRecyclerView.adapter = adapter

        moviesRecyclerView.visibility = VISIBLE
        noMoviesLayout.visibility = INVISIBLE
    }

    override fun displayNoMovies() {
        Log.d(TAG, "Tidak ada film yang ditampilkan")

        moviesRecyclerView.visibility = INVISIBLE
        noMoviesLayout.visibility = VISIBLE
    }

    fun goToAddMovieActivity(v: View) {
        val myIntent = Intent(this@MainActivity, AddMovieActivity::class.java)
        startActivityForResult(myIntent, ADD_MOVIE_ACTIVITY_REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_MOVIE_ACTIVITY_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            showToast("film sukses di tambahkan")
        } else {
            displayError("film gagal di tambahkan")
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.deleteMenuItem) {
            mainPresenter.onDeleteTapped(adapter.selectedMovies)
        }

        return super.onOptionsItemSelected(item)
    }

    override fun showToast(string: String) {
        Toast.makeText(this@MainActivity, string, Toast.LENGTH_LONG).show()
    }

    override fun displayError(e: String) {
        showToast(e)
    }

    companion object {
        const val ADD_MOVIE_ACTIVITY_REQUEST_CODE = 1
    }
}