package com.hfad.coffeepos.domain.usecase

import com.google.firebase.firestore.DocumentReference
import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Ingredient
import kotlinx.coroutines.flow.Flow

interface IngredientRepository {
    fun addIngredient(ingredient: Ingredient) : Flow<State<DocumentReference>>
    fun observeIngredient() : Flow<State<List<Ingredient>>>
}