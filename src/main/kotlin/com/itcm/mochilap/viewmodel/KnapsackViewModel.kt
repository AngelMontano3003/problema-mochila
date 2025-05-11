package com.itcm.mochilap.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.itcm.mochilap.model.Item
import com.itcm.mochilap.model.knapsack

class KnapsackViewModel {
    val items = mutableStateListOf<Item>();
    val maxWeight = mutableStateOf(0)
    val result = mutableStateListOf<Item>()

    fun addItem(name: String, weight: Int, value: Int) {
        items.add(Item(name, weight, value))
    }

    fun solveKnapsack() {
        result.clear()
        result.addAll(knapsack(items.toList(), maxWeight.value))
    }
}