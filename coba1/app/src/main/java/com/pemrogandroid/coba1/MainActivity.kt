package com.pemrogandroid.coba1

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var textView: TextView
    var TAG = "testing"
    lateinit var button: Button
    var textDummy = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate: tes")
        setContentView(R.layout.linear_layout)
        button  = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        button.setOnClickListener(){
            Log.d(TAG, "onCreate: click")
            val animationZoomIn = AnimationUtils.loadAnimation(this,R.anim.coba_anim)
            textView.startAnimation(animationZoomIn)
            Toast.makeText(applicationContext,"menampilkan teks",Toast.LENGTH_LONG).show()
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("DUMMY_TEXT",textDummy)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

}