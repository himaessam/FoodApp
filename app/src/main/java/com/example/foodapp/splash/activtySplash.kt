package com.example.foodapp.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.R
import com.example.foodapp.base.MainActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class activtySplash:AppCompatActivity() {
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_splash)
        intent()
    }
    private fun intent() =
        Handler(Looper.myLooper()!!).postDelayed({
            startActivity(Intent(this@activtySplash, MainActivity::class.java))
            finish()
        }, 7380)

}
