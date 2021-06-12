package com.hfad.coffeepos.domain.usecase

import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Dish
import kotlinx.coroutines.flow.Flow

interface DishRepository {

    suspend fun addDish(dish: Dish) : State<String>
    suspend fun deleteDish(name: String) : State<String>
    fun observeDishes() : Flow<State<List<Dish>>>
}