package com.itcm.mochilap.model

data class Item(
    val name: String,
    val weight: Double,
    val value: Double,
    val fraction: Double = 1.0
)
