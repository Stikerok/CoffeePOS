package com.hfad.coffeepos.domain.usecase

import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Dish
import kotlinx.coroutines.flow.Flow

class DishesUseCaseImp(
    private val dishDatabase: DishRepository,
    private val ingredientDatabase: IngredientRepository
) : DishesUseCase {

    override suspend fun addDish(dish: Dish): State<String> {
        return dishDatabase.addDish(dish)
    }

    override suspend fun deleteDish(name: String): State<String> {
        return dishDatabase.deleteDish(name)
    }

    override fun observeDishes(): Flow<State<List<Dish>>> {
        return dishDatabase.observeDishes()
    }

    override suspend fun confirmOrder(dish: Dish): State<String> {
        return ingredientDatabase.updateQuantityIngredients(dish.ingredients)
    }
}