package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hfad.coffeepos.R
import com.hfad.coffeepos.databinding.MainMenuFragmentBinding

class MainMenuFragment: Fragment() {

    private var _binding: MainMenuFragmentBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onStart() {
        val currentUser = auth.currentUser
        if (currentUser == null) { 
            findNavController().navigate(R.id.loginFragment)
        }
        super.onStart()
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        _binding = MainMenuFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = Firebase.auth
        binding.buttonOrder.setOnClickListener {
            findNavController().navigate(R.id.orderFormFragment)
        }

        binding.buttonDish.setOnClickListener {
            findNavController().navigate(R.id.dishDrinks)
        }

        binding.buttonIngredient.setOnClickListener {
            findNavController().navigate(R.id.ingridients)
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}