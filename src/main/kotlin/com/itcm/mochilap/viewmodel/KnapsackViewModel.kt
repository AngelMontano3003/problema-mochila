package com.itcm.mochilap.viewmodel

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import com.itcm.mochilap.model.Item
import com.itcm.mochilap.model.fractionalKnapsack

class KnapsackViewModel {
    val items = mutableStateListOf<Item>()
    val maxWeight = mutableStateOf(0.0)
    val result = mutableStateListOf<Item>()

    val totalValue = mutableStateOf(0.0)

    fun addItem(name: String, weight: Double, value: Double) {
        items.add(Item(name, weight, value))
    }

    fun solveKnapsack() {
        result.clear()
        result.addAll(fractionalKnapsack(items.toList(), maxWeight.value))
        totalValue.value = result.sumOf{it.value* it.fraction}
    }
}