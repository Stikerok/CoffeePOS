package com.hfad.coffeepos.data

import android.content.Context
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hfad.coffeepos.Constants.DOCUMENT_FIELD_QUANTITY
import com.hfad.coffeepos.Constants.INGREDIENTS_DB
import com.hfad.coffeepos.Constants.TRANSACTION_SUCCESS
import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Ingredient
import com.hfad.coffeepos.domain.usecase.IngredientRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class IngredientDatabase(
    private val context: Context
) : IngredientRepository {

    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private val ingredientCollection = db
        .collection("users")
        .document("stszMOESuSgh1Me583Mt0OVkSDY2")
        .collection(INGREDIENTS_DB)

    override suspend fun addIngredient(ingredient: Ingredient): State<String> {
        var state: State<String>
        withContext(Dispatchers.IO) {
            state = try {
                ingredientCollection.document(ingredient.name.toString()).set(ingredient).await()
                State.success(TRANSACTION_SUCCESS)
            } catch (e: Exception) {
                State.failed(e.message.toString())
            }
        }
        return state
    }

    override suspend fun deleteIngredient(name: String): State<String> {
        var state: State<String>
        withContext(Dispatchers.IO) {
            state = try {
                ingredientCollection.document(name).delete().await()
                State.success(TRANSACTION_SUCCESS)
            } catch (e: Exception) {
                State.failed(e.message.toString())
            }
        }
        return state
    }

    @ExperimentalCoroutinesApi
    override fun observeIngredient(): Flow<State<List<Ingredient>>> = callbackFlow {
        val subscription = ingredientCollection.addSnapshotListener { value, error ->
            if (error != null) {
                offer(State.failed(error.message.toString()))
            }
            if (value != null && !value.isEmpty) {
                val ingredients = value.toObjects(Ingredient::class.java)
                offer(State.success(ingredients))
            }
        }
        awaitClose { subscription.remove() }
    }


    override suspend fun updateQuantityIngredients(map: HashMap<String?, Double?>?): State<String> {
        var state: State<String>
        withContext(Dispatchers.IO) {
            state = try {
                map?.forEach() {
                    val ingredientRef = ingredientCollection.document(it.key!!)
                    val updateValue = it.value!!
                    ingredientRef.update(
                        DOCUMENT_FIELD_QUANTITY,
                        FieldValue.increment(-updateValue)
                    )
                        .await()
                }
                State.success(TRANSACTION_SUCCESS)
            } catch (e: Exception) {
                State.failed(e.message.toString())
            }
        }
        return state
    }
}

