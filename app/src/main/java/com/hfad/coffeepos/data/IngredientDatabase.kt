package com.hfad.coffeepos.data

import android.content.Context
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hfad.coffeepos.Constants.INGREDIENTS_DB
import com.hfad.coffeepos.R
import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Ingredient
import com.hfad.coffeepos.domain.usecase.IngredientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class IngredientDatabase(
    private val context: Context
) : IngredientRepository {

    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private val ingredientCollection =
        db.collection("users").document(auth.currentUser?.uid.toString())
            .collection(INGREDIENTS_DB)

    override fun addIngredient(ingredient: Ingredient) = flow<State<DocumentReference>> {
        //Проверка наличия ингредиентов с таким же именем как добавляемый
        val ingredientSh =
            ingredientCollection.whereEqualTo("name", "${ingredient.name}").get().await()
        val ingredients = ingredientSh.toObjects(Ingredient::class.java)
        if (ingredients.isEmpty()) {
            //Если нету ингридиетов с таким же именем как добавляемый, добавляем ингридиет в Firebase
            val docRef = ingredientCollection.add(ingredient).await()
            emit(State.success(docRef))
        } else {
            emit(State.failed(context.getString(R.string.error_name_ingredient)))
        }
    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    override fun observeIngredient() = flow<State<List<Ingredient>>> {
        val snapshot = ingredientCollection.get().await()
        val ingredients = snapshot.toObjects(Ingredient::class.java)
        emit(State.success(ingredients))
    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

}