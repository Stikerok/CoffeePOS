package com.hfad.coffeepos.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.coffeepos.R
import com.hfad.coffeepos.domain.entity.Dish

class ConfirmOrderAdapter internal constructor(
    private var data: MutableList<Dish>,
    private var quantityList: MutableList<String>,
) : RecyclerView.Adapter<ConfirmOrderAdapter.ViewHolder>() {

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.confirm_order_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = data[position]
        val itemQuantity = quantityList[position]
        viewHolder.name.text = item.name
        viewHolder.quantity.text = itemQuantity
        viewHolder.cost.text = (item.cost?.times(itemQuantity.toInt())).toString()
    }

    fun setData(data: HashMap<Dish, String>) {
        data.forEach { (key, value) ->
            this.data.add(key)
            quantityList.add(value)
        }
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.text_name_prod_conf)
        val quantity: TextView = view.findViewById(R.id.text_quantity_conf)
        val cost: TextView = view.findViewById(R.id.text_cost_conf)

    }

    override fun getItemCount() = data.size
}