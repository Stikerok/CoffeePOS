package com.hfad.coffeepos.presentation.main.ui

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import com.hfad.coffeepos.Constants
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DeleteIngredientDialogFragment : DialogFragment() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val ingredientName = arguments?.getString(Constants.DELETE_INGREDIENT_BUNDLE_KEY)
        val dishName = arguments?.getString(Constants.DELETE_DISH_BUNDLE_KEY)
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Удаление игредиента")
                .setMessage("Вы действительно хотите удалить ингредиент?")
                .setCancelable(true)
                .setPositiveButton("Удалить") { _, _ ->
                    if (ingredientName != null) {
                        viewModel.deleteIngredient(ingredientName)
                    } else if (dishName != null) {
                        viewModel.deleteDish(dishName)
                    }
                    findNavController().popBackStack()
                }
                .setNegativeButton("Назад") { _, _ ->
                }
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}