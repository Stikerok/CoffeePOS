package com.hfad.coffeepos.domain.usecase

import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientUseCase {

    fun addIngredient(ingredient: Ingredient) : Flow<State<String>>
    fun deleteIngredient(name: String) : Flow<State<String>>
    fun observeIngredients() : Flow<State<List<Ingredient>>>
}