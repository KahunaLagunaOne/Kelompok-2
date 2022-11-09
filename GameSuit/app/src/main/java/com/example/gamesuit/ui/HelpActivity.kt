package com.example.gamesuit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gamesuit.databinding.ActivityHelpBinding

class HelpActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHelpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHelpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapterAbout = HelpAdapterAbout()
        binding.rvAbout.adapter = adapterAbout
        binding.rvAbout.layoutManager = LinearLayoutManager (this)

        val adapterHowtoPlay = HelpAdapterHowtoPlay()
        binding.rvHowToPlay.adapter = adapterHowtoPlay
        binding.rvHowToPlay.layoutManager = LinearLayoutManager (this)

        binding.ivBack.setOnClickListener {
            finish()
        }
    }
}