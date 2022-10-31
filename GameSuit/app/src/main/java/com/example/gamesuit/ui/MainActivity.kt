package com.example.gamesuit.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast

import androidx.appcompat.app.AlertDialog
import com.example.gamesuit.R
import com.example.gamesuit.controller.CallBack
import com.example.gamesuit.controller.Controller
import com.example.gamesuit.databinding.ActivityMainBinding
import com.example.gamesuit.databinding.CustomDialogBinding

class MainActivity : AppCompatActivity(), CallBack {

    private lateinit var view: CustomDialogBinding

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val playerName = intent.getStringExtra("nama")
        if (binding.textView2.text=="") binding.textView2.text =playerName

        view = CustomDialogBinding.inflate(LayoutInflater.from(this),null,false)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(view.root)

        val dialog = dialogBuilder.create()

        val controller = Controller(this)

        val player = arrayListOf(
            binding.ivBatuP,
            binding.ivKertasP,
            binding.ivGuntingP
        )
        val com = arrayListOf(
            binding.ivBatuC,
            binding.ivKertasC,
            binding.ivGuntingC
        )
        com.forEach{it.isEnabled=false}
        player.forEach { imageView ->
            imageView.setOnClickListener {
                com.forEach{it.isEnabled=false}
                when (imageView.contentDescription){
                    "gunting"-> Toast.makeText(this,"$playerName Memilih Gunting", Toast.LENGTH_SHORT).show()
                    "kertas"-> Toast.makeText(this, "$playerName Memilih Kertas", Toast.LENGTH_SHORT).show()
                    "batu"-> Toast.makeText(this, "$playerName memilih Batu", Toast.LENGTH_SHORT).show()
                }
                imageView.selected()
                val lawan = com.random()
                lawan.selected()
                when (lawan.contentDescription){
                    "gunting"-> Toast.makeText(this,"CPU Memilih Guntinggunting", Toast.LENGTH_SHORT).show()
                    "kertas"-> Toast.makeText(this, "CPU Memilih Kertas", Toast.LENGTH_SHORT).show()
                    "batu"-> Toast.makeText(this, "CPU memilih Batu", Toast.LENGTH_SHORT).show()
                }

                controller.banding(
                    imageView.contentDescription.toString(),
                    lawan.contentDescription.toString()
                )

                view.btnBackToMenu.setOnClickListener{
                    finish()
                }
                view.btnMainLagi.setOnClickListener {
                    buttonStatusTrue()
                    dialog.dismiss()
                    player.forEach { imageView ->
                        imageView.unselected()
                    }
                    com.forEach { imageView ->
                        imageView.unselected()
                    }
                }
                dialog.show()
                buttonStatusFalse()
            }
        }
        binding.ivRefresh.setOnClickListener {
            Log.d("Click","Click button Refresh")
            player.forEach { imageView ->
                imageView.unselected()
            }
            com.forEach { imageView ->
                imageView.unselected()
            }
            buttonStatusTrue()

        }
        binding.btnClose.setOnClickListener {
            finish()
        }
    }
    private fun buttonStatusFalse() {
        binding.ivGuntingP.isEnabled = false
        binding.ivBatuP.isEnabled = false
        binding.ivKertasP.isEnabled = false
    }

    private fun buttonStatusTrue() {
        binding.ivGuntingP.isEnabled = true
        binding.ivBatuP.isEnabled = true
        binding.ivKertasP.isEnabled = true
    }

    private fun ImageView.selected() {
        setBackgroundResource(R.drawable.bg_rounded)
    }

    private fun ImageView.unselected() {
        setBackgroundResource(R.drawable.bg_default)
    }

    override fun showResult(result: String) {
        view.result.text = result
    }
}