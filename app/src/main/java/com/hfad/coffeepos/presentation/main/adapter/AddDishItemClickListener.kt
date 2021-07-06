package com.hfad.coffeepos.presentation.main.adapter

import com.hfad.coffeepos.domain.entity.Ingredient

interface AddDishItemClickListener {
    fun onClick(ingredient: Ingredient )
}