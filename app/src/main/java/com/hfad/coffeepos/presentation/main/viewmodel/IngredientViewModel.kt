package com.hfad.coffeepos.presentation.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.coffeepos.domain.entity.Ingredient

class IngredientViewModel : ViewModel() {

    var editClickable = false

    private var ingredient = MutableLiveData<Ingredient>()
    fun getIngredient(): MutableLiveData<Ingredient> {
        return ingredient
    }

    fun setIngredient(ingredient: Ingredient) {
        this.ingredient.value = ingredient
    }

    fun setImage(image: Int) {
        this.ingredient.value?.image = image
    }

    fun setCost(cost: Double) {
        this.ingredient.value?.cost = cost
    }

    fun setQuantity(quantity: Double) {
        this.ingredient.value?.quantity = quantity
    }

    fun setUnits(units: String) {
        this.ingredient.value?.units = units
    }

    fun setName(name: String) {
        this.ingredient.value?.name = name
    }
}