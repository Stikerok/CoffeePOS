package com.hfad.coffeepos.domain.entity

import java.io.Serializable

data class Dish(
    var name: String? = null,
    var cost: Double? = null,
    var ingredients: MutableMap<String?, Double?>? = null,
    var image: Int? = null
) : Serializable