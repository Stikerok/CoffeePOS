package com.hfad.coffeepos.presentation.main.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.hfad.coffeepos.R
import com.hfad.coffeepos.domain.entity.Dish


class DishDrinksAdepter internal constructor(
    private var data: List<Dish>
) : RecyclerView.Adapter<DishDrinksAdepter.ViewHolder>() {
    private var listenerDishDrinks: DishDrinksItemClickListener? = null
    private val dishList = listOf<Dish>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.dish_drinks_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = data[position]
        viewHolder.dishName.text = item.name
        /* viewHolder.dishImage.setImageResource(item.) */
        viewHolder.itemView.setOnClickListener{
            listenerDishDrinks?.onClick(data[position])
        }


    }
    fun setData(data: List<Dish>) {
        this.data = data
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val dishImage: ImageView = view.findViewById(R.id.img_prod_item)
        val dishName: TextView = view.findViewById(R.id.name_prod_item)
    }

    override fun getItemCount() = data.size

    fun setListener(dishDrinksItemClickListener: DishDrinksItemClickListener?) {
        listenerDishDrinks = dishDrinksItemClickListener
    }


}








