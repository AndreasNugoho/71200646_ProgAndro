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

    private lateinit var tombolStart:Button
    private lateinit var tombolClick:Button
    private lateinit var score:TextView
    private lateinit var timeLeft:TextView

    var waktu:Int = 30
    var click:Int = 0



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome)

        tombolStart = findViewById(R.id.tombolStart)
        tombolClick = findViewById(R.id.tombolClick)
        score = findViewById(R.id.score)
        timeLeft = findViewById(R.id.timeLeft)

        tombolStart.isEnabled = true
        tombolClick.isEnabled = false

        
        var timer = object :CountDownTimer(30000,1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished : Long) {
                waktu--
                timeLeft.setText("Waktu: "+waktu)

            }

            override fun onFinish() {
                tombolClick.isEnabled = false
                tombolStart.isEnabled = true
                timeLeft.setText("Waktu: 0")
                Toast.makeText(this@MainActivity,"waktu habis!",Toast.LENGTH_SHORT).show()
                Toast.makeText(this@MainActivity,"score anda = $click",Toast.LENGTH_SHORT).show()
            }
        }

        tombolClick.setOnClickListener(object : View.OnClickListener {
            @SuppressLint("SetTextI18n")
            override fun onClick(view: View?) {
                click++
                score.setText("Click: "+click)
            }

        })
        tombolStart.setOnClickListener(object : View.OnClickListener {
            @SuppressLint("SetTextI18n")
            override fun onClick(view: View?) {
                timer.start()
                tombolClick.isEnabled = true
                tombolStart.isEnabled = false
                click = 0
                waktu = 30
                timeLeft.setText("Waktu: "+waktu)
                score.setText("Click: "+click)
            }

        })

    }

}
