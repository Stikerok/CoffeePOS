package com.hfad.coffeepos.domain.entity

data class Ingredient(
    val name: String? = null,
    val cost: Double? = null,
    val quantity: Double? = null,
    val units: String? = null,
    val image: Int? = null
)