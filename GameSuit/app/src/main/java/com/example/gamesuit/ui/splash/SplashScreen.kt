package com.example.gamesuit.ui.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.bumptech.glide.Glide
import com.example.gamesuit.R
import com.example.gamesuit.databinding.ActivitySplashScreenBinding
import com.example.gamesuit.ui.FragmentSpaceActivity

class SplashScreen : AppCompatActivity() {
    lateinit var binding : ActivitySplashScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        Glide.with(this)
            .load("https://i.ibb.co/HC5ZPgD/splash-screen1.png")
            .override(200,200)
            .into(binding.ivKerGunBat)

        Handler(Looper.getMainLooper()).postDelayed({
            Intent(this, FragmentSpaceActivity::class.java).apply{
            startActivity(this)
            finish()}
        },3000)
    }
}