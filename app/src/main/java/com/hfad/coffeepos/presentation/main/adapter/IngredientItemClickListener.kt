package com.hfad.coffeepos.presentation.main.adapter

import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.domain.entity.Ingredient

interface IngredientItemClickListener {
    fun onClick(ingrid:Ingredient)
}