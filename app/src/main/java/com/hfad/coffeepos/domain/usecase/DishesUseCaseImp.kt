package com.hfad.coffeepos.domain.usecase

import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Dish
import kotlinx.coroutines.flow.Flow

class DishesUseCaseImp(
    private val dishDatabase: DishRepository,
    private val ingredientDatabase: IngredientRepository
) : DishUseCase {

    override fun addDish(dish: Dish): Flow<State<String>> {
        return dishDatabase.addDish(dish)
    }

    override fun deleteDish(name: String): Flow<State<String>> {
        return dishDatabase.deleteDish(name)
    }

    override fun observeDishes(): Flow<State<List<Dish>>> {
        return dishDatabase.observeDishes()
    }

    override fun confirmOrder(dish: Dish): Flow<State<String>> {
        return ingredientDatabase.updateQuantityIngredients(dish.ingredients)
    }
}