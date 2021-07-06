package com.hfad.coffeepos.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.coffeepos.State
import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.domain.entity.Ingredient
import com.hfad.coffeepos.domain.usecase.DishesUseCase
import com.hfad.coffeepos.domain.usecase.IngredientUseCase
import com.hfad.coffeepos.extensions.launch
import kotlinx.coroutines.flow.collect

class MainViewModel(
    private val dishesUseCaseImp: DishesUseCase,
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

    fun confirmOrder(map: HashMap<Dish, Int>) {
        launch {
            handleResult(dishesUseCaseImp.confirmOrder(map)) { }
        }
    }

    fun deleteDish(name: String) {
        launch {
            handleResult(dishesUseCaseImp.deleteDish(name)) { }
        }
    }

    fun deleteIngredient(name: String) {
        launch {
            handleResult(ingredientsUseCaseImp.deleteIngredient(name)) { }
        }
    }

    fun addIngredient(ingredient: Ingredient) {
        launch {
            handleResult(ingredientsUseCaseImp.addIngredient(ingredient)) { }
        }
    }

    fun addDish(dish: Dish) {
        launch {
            handleResult(dishesUseCaseImp.addDish(dish)) { }
        }
    }

    private fun updateIngredient() {
        launch {
            ingredientsUseCaseImp.observeIngredients().collect { state ->
                handleResult(state) {
                    ingredients.value = it
                }
            }
        }
    }

     private fun updateDishes() {
        launch {
            dishesUseCaseImp.observeDishes().collect { state ->
                handleResult(state) {
                    dishes.value = it
                }
            }
        }

    }

    private fun <T> handleResult(
        stateResult: State<T>,
        action: (T) -> Unit
    ) {
        when (stateResult) {
            is State.Failed -> {
                error.value = stateResult.message
            }
            is State.Success -> {
                action(stateResult.data)
            }
        }
    }
}