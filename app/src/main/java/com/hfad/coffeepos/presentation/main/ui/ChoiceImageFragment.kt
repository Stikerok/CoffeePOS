package com.hfad.coffeepos.presentation.main.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hfad.coffeepos.R
import com.hfad.coffeepos.databinding.FragmentChoiceImageBinding
import com.hfad.coffeepos.presentation.main.adapter.ChoiceImageAdapter
import com.hfad.coffeepos.presentation.main.adapter.ChoiceImageItemClickListener
import com.hfad.coffeepos.presentation.main.viewmodel.IngredientViewModel

class ChoiceImageFragment : Fragment(), ChoiceImageItemClickListener {

    private var _binding: FragmentChoiceImageBinding? = null
    private val binding get() = _binding !!
    private val choiceImageAdapter = ChoiceImageAdapter(listOf())
    private val ingredientViewModel: IngredientViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentChoiceImageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recyclerChoiceImage.layoutManager = GridLayoutManager(requireContext(),3)
        binding.recyclerChoiceImage.adapter = choiceImageAdapter
        choiceImageAdapter.setListener(this)
        choiceImageAdapter.setData(createListImage())

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(image: Int) {
        ingredientViewModel.setImage(image)
        ingredientViewModel.editClickable = true
        findNavController().popBackStack()
    }

    private fun createListImage(): List<Int> {
        return listOf(
            R.drawable.add_image_24,
            R.drawable.ic_baseline_add_24,
            R.drawable.ic_baseline_remove_24,
            R.drawable.common_full_open_on_phone
        )
    }

}