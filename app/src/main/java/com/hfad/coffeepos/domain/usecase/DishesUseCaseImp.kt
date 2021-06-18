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

    override suspend fun confirmOrder(dishesMap: HashMap<Dish, String>): State<String> {
        val ingredientResult = mutableMapOf<String?, Double?>()
        dishesMap.forEach { (dishKey, dishValue) ->
            dishKey.ingredients?.forEach { (ingredientKey, ingredientValue) ->
                if (ingredientResult.containsKey(ingredientKey)) {
                    ingredientResult[ingredientKey] = ingredientValue?.let { ingredientResult[ingredientKey]?.plus(it * dishValue.toInt()) }
                } else {
                    ingredientResult[ingredientKey] = ingredientValue?.times(dishValue.toInt())
                }
            }
        }

        return ingredientDatabase.updateQuantityIngredients(ingredientResult as HashMap<String?, Double?>)
    }
}