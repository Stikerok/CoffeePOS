package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.coffeepos.Constants.BUNDLE_KEY
import com.hfad.coffeepos.R
import com.hfad.coffeepos.ToolbarController
import com.hfad.coffeepos.databinding.OrderFormFragmentBinding
import com.hfad.coffeepos.presentation.main.adapter.OrderAdapter
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class OrderFormFragment : Fragment() {

    private val viewModel: MainViewModel by viewModel()
    private var _binding: OrderFormFragmentBinding? = null
    private val binding get() = _binding!!
    private val orderAdapter = OrderAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = OrderFormFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerOrder.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerOrder.adapter = orderAdapter

        val mainActivity = activity as ToolbarController
        mainActivity.setTitleToolbar("Заказ")

        viewModel.getDishes().observe(viewLifecycleOwner, Observer {
            orderAdapter.setData(it)
        })
        binding.buttonApply.setOnClickListener {
//            val order = orderAdapter.getOrder()
//            val bundle = Bundle()
//            bundle.putSerializable(BUNDLE_KEY,order)
//            findNavController().navigate(R.id.confirmOrderFragment,bundle)
            findNavController().navigate(R.id.ingredientCardFragment)
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
