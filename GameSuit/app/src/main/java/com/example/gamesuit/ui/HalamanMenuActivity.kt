package com.example.gamesuit.ui

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.gamesuit.R
import com.example.gamesuit.databinding.ActivityHalamanMenuBinding
import com.google.android.material.snackbar.Snackbar

class HalamanMenuActivity : AppCompatActivity() {



    private lateinit var binding: ActivityHalamanMenuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityHalamanMenuBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val player = intent.getStringExtra("name")

        if (binding.tvNamaPlayer.text=="") binding.tvNamaPlayerInCPUMode.text = player
        if (binding.tvNamaPlayer.text=="") binding.tvNamaPlayer.text = player

        val snackBar = Snackbar.make(binding.textView,"Selamat Datang "+ player, Snackbar.LENGTH_INDEFINITE)
        snackBar.setActionTextColor(Color.parseColor("#FFFF9800"))
        snackBar.setAction("Tutup"){
            snackBar.dismiss()
        }
        snackBar.show()


        binding.ivPlayerVsCPU.setOnClickListener{
            val intent = Intent (this, MainActivity::class.java)
            intent.putExtra("nama", player)
            startActivity(intent)
        }
        binding.ivPlayerVsPlayer2.setOnClickListener {
            val intent = Intent (this,MainActivity2::class.java)
            intent.putExtra("nama", player)
            startActivity(intent)
        }
    }
}