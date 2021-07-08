package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hfad.coffeepos.R
import com.hfad.coffeepos.databinding.FragmentAddDishBinding
import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.presentation.main.adapter.AddDishAdapter
import com.hfad.coffeepos.presentation.main.viewmodel.DishViewModel
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class AddDishFragment : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private val dishViewModel: DishViewModel by activityViewModels()

    private var _binding: FragmentAddDishBinding? = null
    private val binding get() = _binding!!
    private lateinit var newProduct: Dish
    var addDishAdapter = AddDishAdapter(listOf())
    var ingridAddListGetMap: MutableMap<String?, Double?>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAddDishBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.dishIngridAddRecycler.layoutManager = LinearLayoutManager(requireContext())
        binding.dishIngridAddRecycler.adapter = addDishAdapter
        viewModel.getIngredients().observe(viewLifecycleOwner, Observer {
            addDishAdapter.setData(it)
        })
        ingridAddListGetMap = addDishAdapter.getIngridAddList()


        dishViewModel.getDish().observe(viewLifecycleOwner, { dish ->
            dish?.let {
                dish.image?.let { binding.imgDishCard.setImageResource(it) }
                binding.imgDishCard.setOnClickListener {
                    findNavController().navigate(R.id.choiceImageFragment)
                }
            }
        }

        )

        binding.addDishButtonApply.setOnClickListener {

            if (binding.textInputAddTextDishName.text?.isNotEmpty() == true) {
                if (binding.textInputAddTextDishCost.text.toString().toDoubleOrNull() != null) {
                    if (ingridAddListGetMap != null) {
                        newProduct = Dish(
                            name = binding.textInputAddTextDishName.text.toString().trim(),
                            cost = binding.textInputAddTextDishCost.text.toString().toDouble(),
                            ingredients = ingridAddListGetMap as MutableMap<String?, Double?>
                        )
                        viewModel.addDish(newProduct)
                        val addProdToast = Toast.makeText(
                            activity?.applicationContext,
                            "Новый продукт добавлен ",
                            Toast.LENGTH_LONG

                        )
                        addProdToast.setGravity(Gravity.CENTER, 0, -20)
                        addProdToast.show()
                        findNavController().popBackStack()
                    } else {

                        toastEmptyFields()
                    }
                } else {
                    toastEmptyFields()
                }

            } else {
                toastEmptyFields()
            }

        }


    }

    private fun toastEmptyFields(): Toast {
        val addProdToast = Toast.makeText(
            activity?.applicationContext,
            "Продукт не добавлен, поля не заполнены",
            Toast.LENGTH_LONG
        )
        addProdToast.setGravity(Gravity.CENTER, 0, -20)
        addProdToast.show()
        return addProdToast
    }

}

