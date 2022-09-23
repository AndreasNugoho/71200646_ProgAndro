package com.pemrogandroid.clickrush

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)

    }
    private var count = 0
    fun onTap(view: View) {
        val timeLeft = findViewById<TextView>(R.id.timeLeft)
        val score = findViewById<TextView>(R.id.score)
        if(count == 0){
            object :CountDownTimer(30000,1000){
                override fun onTick(p0: Long) {
                    timeLeft.setText("Time left :"+p0/1000)
                }

                override fun onFinish() {
                    timeLeft.setText("Waktu Habis")
                    score.setText("Score: 0")
                    Toast.makeText(this@MainActivity,"Time Left: 30 Second",Toast.LENGTH_SHORT).show()
                    Toast.makeText(this@MainActivity,"Time's up! Your score was: $count",Toast.LENGTH_SHORT).show()
                    count = 0

                }

            }.start()
        }
        count++
        score.setText("Score: $count")
    }

}
