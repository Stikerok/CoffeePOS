package com.hfad.coffeepos.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.coffeepos.R

class DishEditIngridAdapter internal constructor(
    private var dataIngrid: List<Pair<String,Double>>

) : RecyclerView.Adapter<DishEditIngridAdapter.ViewHolder>() {
    private var ingridChangedMap =  mutableMapOf<String?,Double?>()
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.dih_edit_ingrid_item, viewGroup, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        val item = dataIngrid[position]
        viewHolder.name.text = item.first
        viewHolder.quantity.setText(item.second.toString())
         ingridChangedMap[viewHolder.name.text.toString()] =  viewHolder.quantity.text.toString().toDouble()
         viewHolder.apply.setOnClickListener {
        ingridChangedMap[viewHolder.name.text.toString()] = viewHolder.quantity.text.toString().toDouble()
        }


    }


    fun setData(data: List<Pair<String,Double>>) {
        this.dataIngrid = data
        notifyDataSetChanged()
    }


    fun getChangedList(): MutableMap<String?, Double?> {

        return ingridChangedMap
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.dish_add_ingrid_name_item)
        val quantity: EditText = view.findViewById(R.id.dish_add_ingrid_quant_item)
        val apply: Button = view.findViewById(R.id.dish_add_checkbox)

    }

    override fun getItemCount() = dataIngrid.size

}

