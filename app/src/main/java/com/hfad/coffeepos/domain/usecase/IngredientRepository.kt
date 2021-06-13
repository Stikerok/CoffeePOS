package com.hfad.coffeepos.domain.usecase

import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {
    suspend fun addIngredient(ingredient: Ingredient): State<String>
    suspend fun deleteIngredient(name: String): State<String>
    fun observeIngredient(): Flow<State<List<Ingredient>>>
    suspend fun updateQuantityIngredients(map: HashMap<String?, Double?>?): State<String>
}