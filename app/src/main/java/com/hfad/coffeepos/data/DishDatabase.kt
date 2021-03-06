package com.hfad.coffeepos.data

import android.content.Context
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hfad.coffeepos.Constants.DISHES_DB
import com.hfad.coffeepos.Constants.TRANSACTION_SUCCESS
import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.domain.usecase.DishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import java.lang.Exception

class DishDatabase(
    private val context: Context
) : DishRepository {
    private val auth = Firebase.auth
    private val db = Firebase.firestore
    private val dishesCollection = db
        .collection("users")
        .document("stszMOESuSgh1Me583Mt0OVkSDY2")
        .collection(DISHES_DB)

    override suspend fun addDish(dish: Dish): State<String> {
        var state : State<String>
        withContext(Dispatchers.IO) {
            state =  try {
                dishesCollection.document(dish.name.toString()).set(dish).await()
                State.success(TRANSACTION_SUCCESS)
            } catch (e: Exception) {
                State.failed(e.message.toString())
            }
        }
        return state
    }

    override suspend fun deleteDish(name: String): State<String> {
        var state : State<String>
        withContext(Dispatchers.IO) {
            state = try {
                dishesCollection.document(name).delete().await()
                State.success(TRANSACTION_SUCCESS)
            } catch (e: Exception) {
                State.failed(e.message.toString())
            }
        }
        return state
    }

    @ExperimentalCoroutinesApi
    override fun observeDishes(): Flow<State<List<Dish>>> = callbackFlow {
        val subscription = dishesCollection.addSnapshotListener { value, error ->
            if (error != null) {
                offer(State.failed(error.message.toString()))
            }
            if (value != null && !value.isEmpty) {
                val dish = value.toObjects(Dish::class.java)
                offer(State.success(dish))
            }
        }
        awaitClose { subscription.remove() }
    }


}