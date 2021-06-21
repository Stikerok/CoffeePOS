package com.hfad.coffeepos.presentation.main.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hfad.coffeepos.R
import com.hfad.coffeepos.domain.entity.Dish
import com.hfad.coffeepos.domain.entity.Ingredient


class IngridientAdapter internal constructor(
    private var data: List<Ingredient>
) : RecyclerView.Adapter<IngridientAdapter.ViewHolder>() {

    private var listenerIngridient: IngridientItemClickListener? = null
    private val dishList = listOf<Ingredient>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.ingridient_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = data[position]
        viewHolder.ingridName.text = item.name
        /* viewHolder.ingridImage.setImageResource(item.) */
        viewHolder.itemView.setOnClickListener{
            listenerIngridient?.onClick(data[position])
        }

    }
    fun setData(data: List<Ingredient>) {
        this.data = data
        notifyDataSetChanged()
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //val dishImage: ImageView = view.findViewById(R.id.img_ingrid_item)
        val ingridName: TextView = view.findViewById(R.id.name_ingrid_item)
    }

    override fun getItemCount() = data.size

    fun setIngridientListener(IngridientItemClickListener : IngridientItemClickListener?) {
        listenerIngridient = IngridientItemClickListener
    }
}

