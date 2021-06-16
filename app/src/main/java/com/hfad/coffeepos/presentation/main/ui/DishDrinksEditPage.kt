package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

class DishDrinksEditPage: Fragment() {

    lateinit var qwerty:Bundle

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.dish_drinks_edit_page, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        qwerty.get("q")


    }



}