package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.coffeepos.R
import com.hfad.coffeepos.databinding.IngridientsBinding
import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.domain.entity.Ingredient
import com.hfad.coffeepos.presentation.main.adapter.IngridientAdapter
import com.hfad.coffeepos.presentation.main.adapter.IngridientItemClickListener
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class Ingridients: Fragment(),IngridientItemClickListener{

    private val viewModel: MainViewModel by viewModel()
    private var _binding: IngridientsBinding? = null
    private val binding get() = _binding!!
    private val ingridientAdapter = IngridientAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = IngridientsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ingridRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.ingridRecycler.adapter = ingridientAdapter
        viewModel.getIngredients().observe(viewLifecycleOwner, Observer {
            ingridientAdapter.setData(it)
            ingridientAdapter.setIngridientListener(this)
        })

    }
    override fun onClick(ingrid: Ingredient) {
        findNavController().navigate(R.id.action_ingridients_to_mainMenu)
    }

}