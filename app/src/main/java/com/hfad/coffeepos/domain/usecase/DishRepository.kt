package com.hfad.coffeepos.domain.usecase

import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Dish
import kotlinx.coroutines.flow.Flow

interface DishRepository {

    fun addDish(dish: Dish) : Flow<State<DocumentReference>>
    fun deleteDish(name: String) : Flow<State<String>>
    fun observeDishes() : Flow<State<List<Dish>>>
}