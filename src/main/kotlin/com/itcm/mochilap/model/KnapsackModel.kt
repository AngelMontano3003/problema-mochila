package com.itcm.mochilap.model

fun fractionalKnapsack(items: List<Item>, maxWeight: Double): List<Item> {
    val sortedItems = items.sortedByDescending { it.value / it.weight }
    val result = mutableListOf<Item>()
    var currentWeight = 0.0

    for (item in sortedItems) {
        if (currentWeight + item.weight <= maxWeight) {
            result.add(item.copy(fraction = 1.0))
            currentWeight += item.weight
        } else {
            val remaining = maxWeight - currentWeight
            if (remaining > 0) {
                val fraction = remaining / item.weight
                result.add(item.copy(fraction = fraction))
                currentWeight = maxWeight
            }
            break
        }
    }

    return result
}