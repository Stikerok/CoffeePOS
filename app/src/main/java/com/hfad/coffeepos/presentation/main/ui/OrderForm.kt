package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.coffeepos.databinding.OrderFormBinding
import com.hfad.coffeepos.presentation.main.adapter.OrderAdapter
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class OrderForm : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: OrderFormBinding? = null
    private val binding get() = _binding!!
    private val orderAdapter = OrderAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = OrderFormBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.orderRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.orderRecycler.adapter = orderAdapter
        viewModel.getDishes().observe(viewLifecycleOwner, Observer {
            orderAdapter.setData(it)
        })
        binding.orderButton.setOnClickListener {
            val dishes = orderAdapter.getOrder()
            dishes.forEach {

                Log.d("TAG",it.name.toString())
            }
        }
    }
}