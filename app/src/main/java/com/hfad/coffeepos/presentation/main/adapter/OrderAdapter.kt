package com.hfad.coffeepos.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.coffeepos.R
import com.hfad.coffeepos.domain.entity.Dish

class OrderAdapter internal constructor(
    private var data: List<Dish>
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private val order = mutableMapOf<String, String>()
    private val orderConfirm = mutableListOf<Dish>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.order_form_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = data[position]
        viewHolder.name.text = item.name
        viewHolder.plus.setOnClickListener {
            val quantity = viewHolder.quantity.text.toString().toInt() + 1
            viewHolder.quantity.setText(quantity.toString())
        }
        viewHolder.minus.setOnClickListener {
            val quantity = viewHolder.quantity.text.toString().toInt() - 1
            if (quantity >= 0) viewHolder.quantity.setText(quantity.toString())
        }
        viewHolder.quantity.doAfterTextChanged {
            val quantity = viewHolder.quantity.text.toString()
            val name = viewHolder.name.text.toString()
            order[name] = quantity
        }
    }

    fun setData(data: List<Dish>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun getOrder(): List<Dish> {
        order.forEach {item ->
            data.forEach {dish ->
                if (item.key == dish.name) {
                    for (i in 0..item.value.toInt()) {
                        orderConfirm.add(dish)
                    }
                }
            }
        }
        return orderConfirm
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.text_name_prod)
        val plus: Button = view.findViewById(R.id.button_plus_quantity)
        val quantity: EditText = view.findViewById(R.id.edit_text_quantity)
        val minus: Button = view.findViewById(R.id.button_minus_quantity)
    }

    override fun getItemCount() = data.size

}

