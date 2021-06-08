package com.hfad.coffeepos.domain.usecase

import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Dish
import kotlinx.coroutines.flow.Flow

interface DishRepository {

    fun addDish(dish: Dish) : Flow<State<String>>
    fun deleteDish(name: String) : Flow<State<String>>
    fun observeDishes() : Flow<State<List<Dish>>>
}