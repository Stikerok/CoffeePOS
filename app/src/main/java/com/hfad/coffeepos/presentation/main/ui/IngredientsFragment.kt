package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.coffeepos.R
import com.hfad.coffeepos.databinding.IngridientsBinding
import com.hfad.coffeepos.domain.entity.Ingredient
import com.hfad.coffeepos.presentation.main.adapter.IngredientsAdapter
import com.hfad.coffeepos.presentation.main.adapter.IngredientItemClickListener
import com.hfad.coffeepos.presentation.main.viewmodel.IngredientViewModel
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class IngredientsFragment : Fragment(), IngredientItemClickListener {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: IngridientsBinding? = null
    private val binding get() = _binding!!
    private val ingredientsAdapter = IngredientsAdapter(listOf())
    private val ingredientViewModel: IngredientViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = IngridientsBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ingridRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.ingridRecycler.adapter = ingredientsAdapter
        viewModel.getIngredients().observe(viewLifecycleOwner, Observer {
            ingredientsAdapter.setData(it)
        })
        ingredientsAdapter.setIngredientsListener(this)
        binding.addNewIngrid.setOnClickListener {
            ingredientViewModel.setIngredient(Ingredient())
            findNavController().navigate(R.id.addIngredientFragment)
        }
    }

    override fun onClick(ingrid: Ingredient) {
        ingredientViewModel.setIngredient(ingrid)
        ingredientViewModel.editClickable = false
        findNavController().navigate(R.id.ingredientCardFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}