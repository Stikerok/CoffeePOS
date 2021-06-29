package com.hfad.coffeepos.presentation.main.adapter


import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.coffeepos.R
import com.hfad.coffeepos.domain.entity.Ingredient


class IngredientsAdapter internal constructor(
    private var data: List<Ingredient>
) : RecyclerView.Adapter<IngredientsAdapter.ViewHolder>() {

    private var listenerIngredient: IngredientItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.ingridient_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = data[position]
        val info = "${item.name}: ${item.quantity} ${item.units}"
        Log.d("TAG","adapter: ${item.name} ${item.image}")
        viewHolder.ingredientsInfo.text = info
        item.image?.let { viewHolder.ingredientsImage.setImageResource(it) }
        viewHolder.itemView.setOnClickListener{
            listenerIngredient?.onClick(item)
        }

    }
    fun setData(data: List<Ingredient>) {
        this.data = data
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ingredientsImage: ImageView = view.findViewById(R.id.img_ingredient_item)
        val ingredientsInfo: TextView = view.findViewById(R.id.text_name_ingredient)
    }

    override fun getItemCount() = data.size

    fun setIngredientsListener(ingredientItemClickListener : IngredientItemClickListener?) {
        listenerIngredient = ingredientItemClickListener
    }
}

