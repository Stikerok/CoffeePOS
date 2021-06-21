package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.coffeepos.R
import com.hfad.coffeepos.databinding.DishDrinksBinding
import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.presentation.main.adapter.DishDrinksAdepter
import com.hfad.coffeepos.presentation.main.adapter.DishDrinksItemClickListener
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DishDrinks : Fragment(), DishDrinksItemClickListener {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: DishDrinksBinding? = null
    private val binding get() = _binding!!
    private val dishDrinksAdapter = DishDrinksAdepter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = DishDrinksBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.dishRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.dishRecycler.adapter = dishDrinksAdapter
        viewModel.getDishes().observe(viewLifecycleOwner, Observer {
            dishDrinksAdapter.setData(it)
        })
        dishDrinksAdapter.setListener(this)

        binding.addNewDish.setOnClickListener {
            findNavController().navigate(R.id.action_dishDrinks_to_dishDrinksAddPage)
        }

    }

    override fun onClick(dish: Dish) {
        var objCopy: Bundle = Bundle()
        objCopy.putString("q", dish.name)
        findNavController().navigate(R.id.action_dishDrinks_to_dishDrinksEditPage, objCopy)
    }


}