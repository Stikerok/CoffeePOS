package com.hfad.coffeepos.domain.entity

import java.io.Serializable

data class Ingredient(
    var name: String? = null,
    var cost: Double? = null,
    var quantity: Double? = null,
    var units: String? = null,
    var image: Int? = null
) : Serializable