package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.view.isVisible
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hfad.coffeepos.Constants.BUNDLE_KEY
import com.hfad.coffeepos.Constants.CHOICE_IMAGE_BUNDLE_KEY
import com.hfad.coffeepos.R
import com.hfad.coffeepos.ToolbarController
import com.hfad.coffeepos.databinding.FragmentIngredientCardBinding
import com.hfad.coffeepos.domain.entity.Ingredient
import com.hfad.coffeepos.presentation.main.viewmodel.IngredientViewModel
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class IngredientCardFragment : Fragment() {

    private var _binding: FragmentIngredientCardBinding? = null
    private val binding get() = _binding!!
    private val viewModel: MainViewModel by viewModel()
    private val ingredientViewModel: IngredientViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentIngredientCardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mainActivity = activity as ToolbarController
        mainActivity.setTitleToolbar("Ингридиент")
        ingredientViewModel.editClickable = changeClickable(ingredientViewModel.editClickable)
        checkInput()
        ingredientViewModel.getIngredient().observe(viewLifecycleOwner, { ingredient ->
            ingredient?.let { it -> fillingCard(it) }
            binding.buttonApply.setOnClickListener {
                viewModel.addIngredient(ingredient)
                findNavController().popBackStack()
            }
        })

        binding.buttonEdit.setOnClickListener {
            ingredientViewModel.editClickable = changeClickable(ingredientViewModel.editClickable)
        }

        binding.imgIngredientCard.setOnClickListener {
            findNavController().navigate(R.id.choiceImageFragment)
        }
    }

    private fun changeClickable(boolean: Boolean) : Boolean {
        binding.imgIngredientCard.isEnabled = boolean
        binding.textInputEditTextIngredientCost.isEnabled = boolean
        binding.textInputEditTextIngredientCost.setTextColor(resources.getColor(R.color.text_input))
        binding.textInputEditTextIngredientUnits.isEnabled = boolean
        binding.textInputEditTextIngredientUnits.setTextColor(resources.getColor(R.color.text_input))
        binding.textInputEditTextIngredientQuantity.isEnabled = boolean
        binding.textInputEditTextIngredientQuantity.setTextColor(resources.getColor(R.color.text_input))
        binding.textInputEditTextIngredientName.isEnabled = boolean
        binding.textInputEditTextIngredientName.setTextColor(resources.getColor(R.color.text_input))
        binding.buttonApply.isVisible = boolean
        if (boolean) {
            binding.buttonEdit.text = getString(R.string.back)
        } else {
            binding.buttonEdit.text = getString(R.string.button_edit)
        }
        return !boolean
    }

    private fun fillingCard(ingredient: Ingredient) {
        binding.textInputEditTextIngredientName.setText(ingredient.name)
        binding.textInputEditTextIngredientQuantity.setText(ingredient.quantity.toString())
        binding.textInputEditTextIngredientUnits.setText(ingredient.units)
        binding.textInputEditTextIngredientCost.setText(ingredient.cost.toString())
        ingredient.image?.let { binding.imgIngredientCard.setImageResource(it) }
    }

    private fun checkInput() {
        binding.textInputEditTextIngredientCost.doAfterTextChanged {
            if (it?.isNotEmpty() == true) {
                ingredientViewModel.setCost(it.toString().toDouble())
                checkEditText()
            }
        }
        binding.textInputEditTextIngredientName.doAfterTextChanged {
            if (it?.isNotEmpty() == true) {
                ingredientViewModel.setName(it.toString())
                checkEditText()
            }
        }
        binding.textInputEditTextIngredientQuantity.doAfterTextChanged {
            if (it?.isNotEmpty() == true) {
                ingredientViewModel.setQuantity(it.toString().toDouble())
                checkEditText()
            }
        }
        binding.textInputEditTextIngredientUnits.doAfterTextChanged {
            if (it?.isNotEmpty() == true) {
                ingredientViewModel.setUnits(it.toString())
                checkEditText()
            }
        }
    }

    private fun checkEditText() {
        if (
            inputIsEmpty(binding.textInputEditTextIngredientCost) ||
            inputIsEmpty(binding.textInputEditTextIngredientName) ||
            inputIsEmpty(binding.textInputEditTextIngredientQuantity) ||
            inputIsEmpty(binding.textInputEditTextIngredientUnits)
        ) {
            binding.buttonApply.isClickable = false
            binding.buttonApply.alpha = 0.5F
        } else {
            binding.buttonApply.isClickable = true
            binding.buttonApply.alpha = 1.0F
        }
    }

    private fun inputIsEmpty(editText: EditText): Boolean {
        return editText.text.toString().trim() == ""
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
