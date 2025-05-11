package com.itcm.mochilap.model

fun knapsack(items: List<Item>, maxWeight: Int): List<Item> {
    val n = items.size
    val dp = Array(n + 1) { IntArray(maxWeight + 1) }

    for (i in 1..n) {
        for (w in 0..maxWeight) {
            if (items[i - 1].weight <= w) {
                dp[i][w] = maxOf(dp[i - 1][w], dp[i - 1][w - items[i - 1].weight] + items[i - 1].value)
            } else {
                dp[i][w] = dp[i - 1][w]
            }
        }
    }

    val result = mutableListOf<Item>()
    var w = maxWeight
    for (i in n downTo 1) {
        if (dp[i][w] != dp[i - 1][w]) {
            result.add(items[i - 1])
            w -= items[i - 1].weight
        }
    }

    return result
}