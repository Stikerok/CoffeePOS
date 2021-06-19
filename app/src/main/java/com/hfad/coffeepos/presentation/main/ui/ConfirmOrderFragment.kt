package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.coffeepos.databinding.ConfirmOrderFragmentBinding
import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.presentation.main.adapter.ConfirmOrderAdapter
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class ConfirmOrderFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: ConfirmOrderFragmentBinding? = null
    private val binding get() = _binding!!
    private val confirmOrderAdapter = ConfirmOrderAdapter(mutableListOf(), mutableListOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = ConfirmOrderFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerConfirmOrder.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerConfirmOrder.adapter = confirmOrderAdapter
        val order = arguments?.getSerializable("str") as HashMap<Dish, Int>
        confirmOrderAdapter.setData(order)
        binding.textOrderCost.text = String.format("%.2f",getTotalCost(order))
        binding.buttonConfirm.setOnClickListener {
            viewModel.confirmOrder(order)
        }
    }

    private fun getTotalCost(map: HashMap<Dish, Int>): Double {
        var totalCost = 0.0
        map.forEach { (dish,value) ->
            totalCost += dish.cost?.times(value) ?: 0.0
        }
        return totalCost
    }


}