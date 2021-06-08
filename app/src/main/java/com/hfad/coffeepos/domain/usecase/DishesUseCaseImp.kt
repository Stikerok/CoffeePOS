package com.hfad.coffeepos.domain.usecase

import com.google.firebase.firestore.DocumentReference
import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Dish
import kotlinx.coroutines.flow.Flow

class DishesUseCaseImp(
    private val dishDatabase: DishRepository
) : DishUseCase {

    override fun addDish(dish: Dish): Flow<State<DocumentReference>> {
        return dishDatabase.addDish(dish)
    }

    override fun deleteDish(name: String): Flow<State<String>> {
        return dishDatabase.deleteDish(name)
    }

    override fun observeDishes(): Flow<State<List<Dish>>> {
        return dishDatabase.observeDishes()
    }
}