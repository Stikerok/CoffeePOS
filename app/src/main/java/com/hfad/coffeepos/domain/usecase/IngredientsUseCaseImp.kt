package com.hfad.coffeepos.domain.usecase

import com.google.firebase.firestore.DocumentReference
import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Ingredient
import kotlinx.coroutines.flow.Flow

class IngredientsUseCaseImp(
    private val ingredientRepository: IngredientRepository
) : IngredientUseCase {
    override fun addIngredient(ingredient: Ingredient): Flow<State<DocumentReference>> {
        return ingredientRepository.addIngredient(ingredient)
    }

    override fun observeIngredients(): Flow<State<List<Ingredient>>> {
        return ingredientRepository.observeIngredient()
    }
}