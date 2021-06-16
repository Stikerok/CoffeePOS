package com.hfad.coffeepos.domain.entity

data class Dish(
    var name: String? = null,
    var cost: Double? = null,
    val ingredients: HashMap<String?, Double?>? = null
)