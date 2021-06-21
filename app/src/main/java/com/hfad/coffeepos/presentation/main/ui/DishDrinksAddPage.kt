package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.hfad.coffeepos.R
import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class DishDrinksAddPage : Fragment() {
    private val viewModel: MainViewModel by viewModel()
    private lateinit var inputProdName: EditText
    private lateinit var inputProdCost: EditText
    private lateinit var newProduct: Dish
    private lateinit var addNewProductButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dish_drinks_add_page, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        inputProdName = view.findViewById(R.id.product_name)
        inputProdCost = view.findViewById(R.id.product_cost)
        addNewProductButton = view.findViewById(R.id.add_new_prod)

        addNewProductButton.setOnClickListener {
            if (inputProdName.text.isNotEmpty()) {
                inputProdCost.text.toString().toDoubleOrNull()
                if (inputProdCost.text.toString().toDoubleOrNull() != null) {
                    newProduct = Dish(
                        name = inputProdName.text.toString().trim(),
                        cost = inputProdCost.text.toString().toDouble()
                    )
                    // view.findViewById<TextView>(R.id.textView2).text =newProduct.name.toString()+newProduct.cost.toString()
                    viewModel.addDish(newProduct)
                    var addProdToast = Toast.makeText(
                        activity?.applicationContext,
                        "Новый продукт добавлен ",
                        Toast.LENGTH_LONG
                    )
                    addProdToast.setGravity(Gravity.CENTER, 0, -20)
                    addProdToast.show()
                } else {
                    var addProdToast = Toast.makeText(
                        activity?.applicationContext,
                        "Продукт не добавлен, поля не заполнены",
                        Toast.LENGTH_LONG
                    )
                    addProdToast.setGravity(Gravity.CENTER, 0, -20)
                    addProdToast.show()
                }
            }

        }


        // view.findViewById<TextView>(R.id.textView2).text = newProduct.name.toString()
        //  if (newProduct.name != null) {
        // if (newProduct.cost != null) {
        //    viewModel.addDish(newProduct.name,newProduct.cost)
        //  }
        //  }
        // }
    }
}
