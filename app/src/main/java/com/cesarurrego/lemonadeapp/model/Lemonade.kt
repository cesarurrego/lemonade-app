package com.cesarurrego.lemonadeapp.model


enum class LemonadeStep {
    TREE,
    SQUEEZE,
    DRINK,
    EMPTY
}

class Lemonade(
    val step: LemonadeStep,
    val squeezeCount: Int,
    val label: String,
    val contentDescription: String,
    val image: String
)