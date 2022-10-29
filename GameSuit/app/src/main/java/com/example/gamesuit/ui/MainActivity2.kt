package com.example.gamesuit.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.gamesuit.R
import com.example.gamesuit.controller.Controller2
import com.example.gamesuit.databinding.ActivityMain2Binding
import com.example.gamesuit.databinding.CustomDialogBinding

class MainActivity2 : AppCompatActivity() {

    private lateinit var view: CustomDialogBinding

    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        view = CustomDialogBinding.inflate(LayoutInflater.from(this),null,false)
        val dialogBuilder = AlertDialog.Builder(this)
        dialogBuilder.setView(view.root)

        val dialog = dialogBuilder.create()

        val playerName = intent.getStringExtra("nama")
        if (binding.textView2.text=="") binding.textView2.text =playerName

        val controller = Controller2(this)

        var player2Input = ""
        var playerInput = ""

        val player = arrayListOf(
            binding.ivBatuP,
            binding.ivKertasP,
            binding.ivGuntingP
        )
        val player2 = arrayListOf(
            binding.ivBatuP2,
            binding.ivKertasP2,
            binding.ivGuntingP2
        )
        player2.forEach{ imageView ->
            imageView.isEnabled = false
        }
        player.forEach { imageView ->
            imageView.setOnClickListener {
                when (imageView.contentDescription){
                    "gunting"-> Toast.makeText(this, "$playerName Memilih Gunting", Toast.LENGTH_SHORT).show()
                    "kertas"-> Toast.makeText(this, "$playerName Memilih Kertas", Toast.LENGTH_SHORT).show()
                    "batu"-> Toast.makeText(this, "$playerName memilih Batu", Toast.LENGTH_SHORT).show()
                }
                imageView.selected()
                buttonStatusPlayerFalse()
                buttonStatusPlayer2True()
                imageView.contentDescription.toString()
                playerInput = imageView.contentDescription.toString()
                when(player2Input) {
                    "batu","gunting","kertas"-> controller.banding(playerInput, player2Input)
                }
            }
        }
        player2.forEach { imageView ->
            imageView.setOnClickListener {
                when (imageView.contentDescription){
                    "gunting"-> Toast.makeText(this,"Player 2 Memilih Gunting", Toast.LENGTH_SHORT).show()
                    "kertas"-> Toast.makeText(this, "Player 2 Memilih Kertas", Toast.LENGTH_SHORT).show()
                    "batu"-> Toast.makeText(this, "Player 2 memilih Batu", Toast.LENGTH_SHORT).show()
                }
                imageView.selected()
                buttonStatusPlayer2False()
                imageView.contentDescription.toString()
                player2Input = imageView.contentDescription.toString()

                view.btnBackToMenu.setOnClickListener{
                    finish()
                }
                view.btnMainLagi.setOnClickListener {
                    buttonStatusPlayerTrue()
                    buttonStatusPlayer2False()
                    dialog.dismiss()
                    player.forEach { imageView ->
                        imageView.unselected()
                    }
                    player2.forEach { imageView ->
                        imageView.unselected()
                    }
                    playerInput = ""
                    player2Input= ""
                }
                dialog.show()

                when(playerInput) {
                    "batu","kertas","gunting"-> controller.banding(playerInput, player2Input)
                }
            }
        }
        binding.ivRefresh.setOnClickListener {
            buttonStatusPlayerTrue()
            buttonStatusPlayer2False()
            player.forEach { imageView ->
                imageView.unselected()
            }
            player2.forEach { imageView ->
                imageView.unselected()
            }
            playerInput = ""
            player2Input= ""
        }
        binding.btnClose.setOnClickListener {
            finish()
        }
    }
    private fun buttonStatusPlayerFalse() {
        binding.ivGuntingP.isEnabled = false
        binding.ivBatuP.isEnabled = false
        binding.ivKertasP.isEnabled = false
    }
    private fun buttonStatusPlayer2False() {
        binding.ivGuntingP2.isEnabled = false
        binding.ivBatuP2.isEnabled = false
        binding.ivKertasP2.isEnabled = false
    }

    private fun buttonStatusPlayerTrue() {
        binding.ivGuntingP.isEnabled = true
        binding.ivBatuP.isEnabled = true
        binding.ivKertasP.isEnabled = true
    }
    private fun buttonStatusPlayer2True() {
        binding.ivGuntingP2.isEnabled = true
        binding.ivBatuP2.isEnabled = true
        binding.ivKertasP2.isEnabled = true
    }

    private fun ImageView.selected() {
        setBackgroundResource(R.drawable.bg_rounded)
    }

    private fun ImageView.unselected() {
        setBackgroundResource(R.drawable.bg_default)
    }

    fun showResult(result: String) {
        view.result.text = result
    }
}