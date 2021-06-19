package com.hfad.coffeepos.domain.entity

data class Dish(
    val name: String? = null,
    val cost: Double? = null,
    val ingredients: HashMap<String?, Double?>? = null,
    val image: Int? = null
)