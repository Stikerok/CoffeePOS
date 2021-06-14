package com.hfad.coffeepos.presentation.main.adapter

import androidx.recyclerview.widget.DiffUtil
import com.hfad.coffeepos.domain.entity.Dish

class OrderCompareCallback  : DiffUtil.ItemCallback<Dish>() {
    override fun areItemsTheSame(oldItem: Dish, newItem: Dish): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Dish, newItem: Dish): Boolean {
        return oldItem == newItem
    }
}