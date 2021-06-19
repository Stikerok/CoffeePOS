package com.hfad.coffeepos.domain.usecase

import com.google.firebase.firestore.DocumentReference
import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Dish
import kotlinx.coroutines.flow.Flow

interface DishesUseCase {

    suspend fun addDish(dish: Dish) : State<String>
    suspend fun deleteDish(name: String) : State<String>
    fun observeDishes() : Flow<State<List<Dish>>>
    suspend fun confirmOrder(dishesMap : HashMap<Dish, Int>) : State<String>
}