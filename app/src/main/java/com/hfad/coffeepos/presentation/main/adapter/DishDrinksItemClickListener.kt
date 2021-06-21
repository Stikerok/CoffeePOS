package com.hfad.coffeepos.presentation.main.adapter

import com.hfad.coffeepos.domain.entity.Dish

interface DishDrinksItemClickListener {
    fun onClick(dish : Dish)
}