package com.hfad.coffeepos.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.coffeepos.domain.entity.Dish

class DishViewModel : ViewModel() {

    var editClickable = false

    private var dish = MutableLiveData<Dish>()
    fun getDish(): MutableLiveData<Dish> {
        return dish
    }

    fun setDish(dish: Dish) {
        this.dish.value = dish
    }

    fun setName(name: String) {
        this.dish.value?.name = name
    }

    fun setImage(image: Int) {
        this.dish.value?.image = image
    }

    fun setCost(cost: Double) {
        this.dish.value?.cost = cost
    }

    fun setIngredient(ingredient: MutableMap<String?, Double?>) {
        this.dish.value?.ingredients = ingredient
    }


}