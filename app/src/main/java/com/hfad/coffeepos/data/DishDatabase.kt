package com.hfad.coffeepos.data

import android.content.Context
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.hfad.coffeepos.Constants.DISHES_DB
import com.hfad.coffeepos.Constants.TRANSACTION_SUCCESS
import com.hfad.coffeepos.R
import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.domain.usecase.DishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.tasks.await

class DishDatabase(
    private val context: Context
) : DishRepository {
    private val auth = Firebase.auth
    private val db = Firebase.firestore
    val batch = db.batch()
    private val dishesCollection =
        db.collection("users").document(auth.currentUser?.uid.toString())
            .collection(DISHES_DB)

    override fun addDish(dish: Dish): Flow<State<String>> = flow<State<String>> {
        val dishSh =
            dishesCollection.whereEqualTo("name", "${dish.name}").get().await()
        val dishes = dishSh.toObjects(Dish::class.java)
        if (dishes.isEmpty()) {
            dishesCollection.document(dish.name.toString()).set(dish).await()
            emit(State.success(TRANSACTION_SUCCESS))
        } else {
            emit(State.failed(context.getString(R.string.error_name_dishes)))
        }
    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    override fun deleteDish(name: String) = flow<State<String>> {

        dishesCollection.document(name).delete().await()
        emit(State.success(TRANSACTION_SUCCESS))


    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)

    override fun observeDishes() = flow<State<List<Dish>>> {
        val snapshot = dishesCollection.get().await()
        val dishes = snapshot.toObjects(Dish::class.java)
        emit(State.success(dishes))
    }.catch {
        emit(State.failed(it.message.toString()))
    }.flowOn(Dispatchers.IO)


}