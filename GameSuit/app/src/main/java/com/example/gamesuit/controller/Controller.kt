package com.example.gamesuit.controller

class Controller(private val callback: CallBack){
    fun banding(one: String, two: String) = when (one + two) {
        "gunting" + "gunting", "batu" + "batu", "kertas" + "kertas" -> {
            callback.showResult("SERI!")
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
