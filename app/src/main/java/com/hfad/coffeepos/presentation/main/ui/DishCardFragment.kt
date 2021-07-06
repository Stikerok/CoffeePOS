package com.hfad.coffeepos.presentation.main.ui

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.*
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import com.hfad.coffeepos.R
import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.presentation.main.adapter.DishEditIngridAdapter
import com.hfad.coffeepos.presentation.main.viewmodel.DishViewModel
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DishCardFragment : Fragment() {


    private lateinit var prodImage: ImageView
    private lateinit var prodName: TextInputEditText
    private lateinit var prodCost: TextInputEditText
    private lateinit var editButton: MaterialButton
    private lateinit var applyButton: MaterialButton
    private var ingridList = emptyList<Pair<String?, Double?>>()
    private lateinit var dishIngAdapter: RecyclerView

    private val viewModel: MainViewModel by viewModel()
    private val dishViewModel: DishViewModel by activityViewModels()
    private val dishEditIngridAdapter = DishEditIngridAdapter(listOf())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_dish_card, container, false)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        prodImage = view.findViewById(R.id.img_dish_card)
        prodName = view.findViewById(R.id.text_input_add_text_dish_name)
        prodCost = view.findViewById(R.id.text_input_add_text_dish_cost)
        editButton = view.findViewById(R.id.button_edit)
        applyButton = view.findViewById(R.id.button_apply)

        dishIngAdapter = view.findViewById(R.id.dish_ingrid_recycler)
        dishIngAdapter.layoutManager = LinearLayoutManager(requireContext())
        dishIngAdapter.adapter = dishEditIngridAdapter

        dishViewModel.getDish().observe(viewLifecycleOwner, { dish ->
            dish?.let {
                dish.image?.let { prodImage.setImageResource(it) }
                prodName.setText(dish.name.toString())
                prodCost.setText(dish.cost.toString())
                if (dish.ingredients != null) ingridList = dish.ingredients!!.toList()
                dishEditIngridAdapter.setData(ingridList as List<Pair<String, Double>>)

            }

            prodImage.setOnClickListener {
                findNavController().navigate(R.id.choiceImageFragment)
            }


            applyButton.setOnClickListener {
                if (prodCost.text?.isNotEmpty() == true) {
                    dish.cost = prodCost.text.toString().trim().toDouble()


                    dish.ingredients = dishEditIngridAdapter.getChangedList()
                    viewModel.addDish(dish)
                    findNavController().popBackStack()
                }
            }
        })

        prodImage.isEnabled = false
        prodName.isEnabled = false
        prodCost.isEnabled = false

        editButton.setOnClickListener {
            if (applyButton.visibility == VISIBLE) {
                prodImage.isEnabled = false
                prodCost.isEnabled = false

                applyButton.visibility = INVISIBLE
            } else {
                prodImage.isEnabled = true
                prodCost.isEnabled = true
                applyButton.visibility = VISIBLE
            }


        }

    }


}


