package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.hfad.coffeepos.R
import com.hfad.coffeepos.databinding.MainFragmentBinding
import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.domain.entity.Ingredient
import com.hfad.coffeepos.presentation.main.viewmodel.MainViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class MainFragment : Fragment() {

    private  val viewModel: MainViewModel by viewModel()
    private lateinit var auth: FirebaseAuth
    private var _binding: MainFragmentBinding? = null
    private val binding get() = _binding!!



    override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        if (currentUser != null) {
            findNavController().navigate(R.id.orderForm)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = MainFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        auth = Firebase.auth

        val milk = Ingredient(
            "milk",
            5.15,
            100.0,
            "ml",
        )
        val coffee = Ingredient(
            "coffee",
            26.57,
            50.0,
            "kg",
        )
        val hMap = hashMapOf(milk.name to milk.quantity, coffee.name to coffee.quantity)
        val latte = Dish(
            "jok",
            5.156,
            hMap
        )

        viewModel.getDishes().observe(viewLifecycleOwner, Observer { list ->
            if (list.isNotEmpty()) {
               val str = list.joinToString("\n") {
                   "${it.name} ~ ${it.cost}"
               }
                binding.singInButton.text = str
            }
        })

        viewModel.getIngredients().observe(viewLifecycleOwner, Observer { list ->
            if (list.isNotEmpty()) {
                val str = list.joinToString("\n") {
                    "${it.name} ~ ${it.quantity}"
                }
                binding.textTwo.text = str
            }
        })

        binding.buttonGo.setOnClickListener {

        }

        viewModel.getError().observe(viewLifecycleOwner, Observer {
            Toast.makeText(requireContext(), it, Toast.LENGTH_LONG).show()
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}