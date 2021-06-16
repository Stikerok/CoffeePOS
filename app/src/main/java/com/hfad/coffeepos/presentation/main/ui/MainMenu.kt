package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.hfad.coffeepos.R

class MainMenu: Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.main_menu, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<Button>(R.id.orderButton).setOnClickListener {
            findNavController().navigate(R.id.action_mainMenu_to_orderForm)
        }
        view.findViewById<Button>(R.id.dishButton).setOnClickListener {
            findNavController().navigate(R.id.action_mainMenu_to_dishDrinks)
        }

        view.findViewById<Button>(R.id.ingridButton).setOnClickListener {
            findNavController().navigate(R.id.action_mainMenu_to_ingridients)
        }


    }
}