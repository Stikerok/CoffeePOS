package com.hfad.coffeepos.domain.entity

data class Dish(
    val name: String? = null,
    val cost: Float? = null,
    val ingredients: HashMap<String?, Float?>? = null
)