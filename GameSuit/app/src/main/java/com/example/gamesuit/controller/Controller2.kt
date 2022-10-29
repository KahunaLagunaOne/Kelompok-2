package com.example.gamesuit.controller

import com.example.gamesuit.ui.MainActivity2

class Controller2(private val callback: MainActivity2){
    fun banding(one: String, two: String) = when (one + two) {
        "gunting" + "gunting", "batu" + "batu", "kertas" + "kertas" -> {
            callback.showResult("Draw")
        }

        "gunting" + "kertas", "kertas" + "batu", "batu" + "gunting" -> {
            callback.showResult("Pemain 1 MENANG!")
        }

        "kertas" + "gunting", "batu" + "kertas", "gunting" + "batu" -> {
            callback.showResult("Pemain 2 MENANG!")
        }
        else -> fun (){}
        }
    }
