package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.hfad.coffeepos.R
import com.hfad.coffeepos.databinding.FragmentAddIngredientBinding
import com.hfad.coffeepos.databinding.FragmentIngredientCardBinding
import com.hfad.coffeepos.domain.entity.Ingredient
import com.hfad.coffeepos.presentation.main.viewmodel.IngredientViewModel
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AddIngredientFragment : Fragment() {
    private var _binding: FragmentAddIngredientBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModel()
    private val ingredientViewModel: IngredientViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddIngredientBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        ingredientViewModel.getIngredient().observe(viewLifecycleOwner, { ingredient ->
            fillingCard(ingredient)
            binding.addIngredientButtonApply.setOnClickListener {
                viewModel.addIngredient(ingredient)
                findNavController().popBackStack()
            }
        })
        checkInput()
        binding.imgAddIngredientCard.setOnClickListener {
            findNavController().navigate(R.id.choiceImageFragment)
        }
    }

    private fun checkInput() {
        binding.textInputEditTextAddIngredientCost.doAfterTextChanged {
            if (it?.isNotEmpty() == true) {
                ingredientViewModel.setCost(it.toString().toDouble())
                checkEditText()
            }
        }
        binding.textInputEditTextAddIngredientName.doAfterTextChanged {
            if (it?.isNotEmpty() == true) {
                ingredientViewModel.setName(it.toString())
                checkEditText()
            }
        }
        binding.textInputEditTextAddIngredientQuantity.doAfterTextChanged {
            if (it?.isNotEmpty() == true) {
                ingredientViewModel.setQuantity(it.toString().toDouble())
                checkEditText()
            }
        }
        binding.textInputEditTextAddIngredientUnits.doAfterTextChanged {
            if (it?.isNotEmpty() == true) {
                ingredientViewModel.setUnits(it.toString())
                checkEditText()
            }
        }
    }

    private fun checkEditText() {
        if (
            inputIsEmpty(binding.textInputEditTextAddIngredientCost) ||
            inputIsEmpty(binding.textInputEditTextAddIngredientName) ||
            inputIsEmpty(binding.textInputEditTextAddIngredientQuantity) ||
            inputIsEmpty(binding.textInputEditTextAddIngredientUnits)
        ) {
            binding.addIngredientButtonApply.isClickable = false
            binding.addIngredientButtonApply.alpha = 0.5F
        } else {
            binding.addIngredientButtonApply.isClickable = true
            binding.addIngredientButtonApply.alpha = 1.0F
        }
    }

    private fun inputIsEmpty(editText: EditText): Boolean {
        return editText.text.toString().trim() == ""
    }

    private fun fillingCard(ingredient: Ingredient) {
        if (ingredient.name != null) binding.textInputEditTextAddIngredientName.setText(ingredient.name)
        if (ingredient.quantity != null) binding.textInputEditTextAddIngredientQuantity.setText(
            ingredient.quantity.toString()
        )
        if (ingredient.units != null) binding.textInputEditTextAddIngredientUnits.setText(ingredient.units)
        if (ingredient.cost != null) binding.textInputEditTextAddIngredientCost.setText(ingredient.cost.toString())
        ingredient.image?.let { binding.imgAddIngredientCard.setImageResource(it) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}