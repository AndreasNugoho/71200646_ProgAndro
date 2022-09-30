package com.pemrogandroid.clickrush

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private var LOG_TAG = "UIElement"
    lateinit var tombolClick: Button
    lateinit var score:TextView
    lateinit var timeLeft:TextView
    var count = 0
    var countDownTimer: CountDownTimer? = null
    var remainingTime: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)
        val score = findViewById<TextView>(R.id.score)
        val tombolClick = findViewById<Button>(R.id.tombolClick)
        val timeLeft = findViewById<TextView>(R.id.timeLeft)
        tombolClick.setOnClickListener(){
            val scaleAnim = AnimationUtils.loadAnimation(this,R.anim.scale)
            tombolClick.startAnimation(scaleAnim)
            if(count == 0){
                countDownTimer = object :CountDownTimer(30000,1000){
                    override fun onTick(mTimeLeftInMillis : Long) {
                        var second = mTimeLeftInMillis /1000
                        remainingTime = second
                        timeLeft.setText("Time left :"+ second)
                        Log.d(LOG_TAG, "time: $remainingTime")

                    }
                    override fun onFinish() {
                        Toast.makeText(this@MainActivity,"Time Left: 30 Second",Toast.LENGTH_SHORT).show()
                        Toast.makeText(this@MainActivity,"Time's up! Your score was: $count",Toast.LENGTH_SHORT).show()
                        count = 0
                        timeLeft.setText("Waktu Habis")
                        score.setText("Score: $count")

                    }

                }.start()
            }
            count++
            score.setText("Score: $count")
        }
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count")
            remainingTime = savedInstanceState.getLong("remainTime")
            score.setText("Score: $count")
            timeLeft.setText("Timeleft: $remainingTime")
            if (remainingTime > 0L){
                countDownTimer?.start()
            }
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putLong("remainTime",remainingTime)
        outState.putInt("count",count)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.about_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val view  = View.inflate(this,R.layout.popup,null)

        val builder = AlertDialog.Builder(this)
        builder.setView(view)

        val dialog = builder.create()
        dialog.show()
        fun dialog(){
            dialog.show()
            dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        }
        when(item.itemId){
            R.id.about->dialog()
        }
        return true
    }
//    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
//        super.onRestoreInstanceState(savedInstanceState)
//        savedInstanceState.getInt("count")
//        savedInstanceState.getString("time")
//    }
}