package com.hfad.coffeepos.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.domain.entity.Ingredient
import com.hfad.coffeepos.domain.usecase.DishUseCase
import com.hfad.coffeepos.domain.usecase.IngredientUseCase
import com.hfad.coffeepos.extensions.launch
import kotlinx.coroutines.flow.collect

class MainViewModel(
    private val dishesUseCaseImp: DishUseCase,
    private val ingredientsUseCaseImp: IngredientUseCase
) : ViewModel() {

    private var dishes = MutableLiveData<List<Dish>>(listOf())
    fun getDishes(): LiveData<List<Dish>> {
        return dishes
    }

    private var ingredients = MutableLiveData<List<Ingredient>>(listOf())
    fun getIngredients(): LiveData<List<Ingredient>> {
        return ingredients
    }

    private var error = MutableLiveData<String>()
    fun getError(): LiveData<String> = error

    init {
        updateDishes()
        updateIngredient()
    }

    fun deleteDish(name: String) {
        launch {
            dishesUseCaseImp.deleteDish(name).collect { state ->
                when (state) {
                    is State.Success -> {
                        updateDishes()
                    }
                    is State.Failed -> {
                        error.value = state.message
                    }
                }

            }
        }
    }

    fun addIngredient(ingredient: Ingredient) {
        launch {
            ingredientsUseCaseImp.addIngredient(ingredient).collect { state ->
                when (state) {
                    is State.Success -> {
                        updateIngredient()
                    }

                    is State.Failed -> {
                        error.value = state.message
                    }
                }
            }
        }
    }

    fun addDish(dish: Dish) {
        launch {
            dishesUseCaseImp.addDish(dish).collect { state ->
                when (state) {
                    is State.Success -> {
                        updateDishes()
                    }

                    is State.Failed -> {
                        error.value = state.message
                    }
                }
            }
        }
    }

    private fun updateIngredient() {
        launch {
            ingredientsUseCaseImp.observeIngredients().collect { state ->
                when (state) {
                    is State.Success -> {
                        ingredients.value = state.data!!
                    }
                    is State.Failed -> {
                        error.value = state.message
                    }
                }
            }
        }
    }

    private fun updateDishes() {
        launch {
            dishesUseCaseImp.observeDishes().collect { state ->
                when (state) {
                    is State.Success -> {
                        dishes.value = state.data!!
                    }
                    is State.Failed -> {
                        error.value = state.message
                    }
                }
            }
        }

    }

}