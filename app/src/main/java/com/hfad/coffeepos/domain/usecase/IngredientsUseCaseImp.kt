package com.hfad.coffeepos.domain.usecase

import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Ingredient
import kotlinx.coroutines.flow.Flow

class IngredientsUseCaseImp(
    private val ingredientDatabase: IngredientRepository
) : IngredientUseCase {
    override fun addIngredient(ingredient: Ingredient): Flow<State<String>> {
        return ingredientDatabase.addIngredient(ingredient)
    }

    override fun deleteIngredient(name: String): Flow<State<String>> {
        return ingredientDatabase.deleteIngredient(name)
    }

    override fun observeIngredients(): Flow<State<List<Ingredient>>> {
        return ingredientDatabase.observeIngredient()
    }
}