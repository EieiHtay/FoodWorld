package com.example.foodhouse.model

data class Food (
    val id : Int ?= null,
    val name : String = "",
    val ftype : String = "",
    val price : String ="",
    val desc : String = "",
    var foodImg : Int ?= null
    )

