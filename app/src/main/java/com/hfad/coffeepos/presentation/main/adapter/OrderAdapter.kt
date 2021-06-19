package com.hfad.coffeepos.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.hfad.coffeepos.R
import com.hfad.coffeepos.domain.entity.Dish

class OrderAdapter internal constructor(
    private var data: List<Dish>
) : RecyclerView.Adapter<OrderAdapter.ViewHolder>() {

    private val order = mutableMapOf<Dish, Int>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.order_form_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = data[position]
        viewHolder.name.text = item.name
        if (order[item] == null) {
            order[item] = 0
            viewHolder.quantity.setText(order[item].toString())
        } else {
            viewHolder.quantity.setText(order[item].toString())
        }
        viewHolder.plus.setOnClickListener {
            order[item] = order[item]!!.plus(1)
            viewHolder.quantity.setText(order[item].toString())
        }
        viewHolder.minus.setOnClickListener {
            val value = order[item]
            if (value != null) {
                if (value > 0)
                    order[item] = order[item]!!.minus(1)
                viewHolder.quantity.setText(order[item].toString())
            }
        }
    }

    fun setData(data: List<Dish>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun getOrder(): HashMap<Dish, Int> {
        return order as HashMap<Dish, Int>
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.text_name_prod)
        val plus: Button = view.findViewById(R.id.button_plus_quantity)
        val quantity: EditText = view.findViewById(R.id.text_quantity)
        val minus: Button = view.findViewById(R.id.button_minus_quantity)
    }

    override fun getItemCount() = data.size

}

