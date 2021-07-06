package com.hfad.coffeepos.presentation.main.adapter


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.hfad.coffeepos.R
import com.hfad.coffeepos.domain.entity.Ingredient


class AddDishAdapter internal constructor(
    private var data: List<Ingredient>
) : RecyclerView.Adapter<AddDishAdapter.ViewHolder>() {
    private var clickOnOff: Boolean = false
    var ingredientAddList = mutableMapOf<String?, Double?>()
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.dih_add_ingrid_item, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//        ingredientAddList?.set("coffee", 2.0)
//        ingredientAddList?.set("milk", 2.0)
        val item = data[position]
        viewHolder.ingridAddName.text = item.name.toString()
        viewHolder.ingridAddItemQuantity.setText("0")


        if (!clickOnOff) {
            viewHolder.ingridAddItemQuantity.isEnabled = clickOnOff
            viewHolder.ingridAddName.alpha = 0.3F
            viewHolder.ingridAddItemQuantity.alpha = 0.3F
        }
        viewHolder.addDishCheckBox.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                clickOnOff = true
                viewHolder.ingridAddItemQuantity.isEnabled = clickOnOff
                viewHolder.ingridAddName.alpha = 1.0F
                viewHolder.ingridAddItemQuantity.alpha = 1.0F
            } else {
                viewHolder.ingridAddItemQuantity.setText("0")
                clickOnOff = false
                viewHolder.ingridAddName.alpha = 0.3F
                viewHolder.ingridAddItemQuantity.alpha = 0.3F
                viewHolder.ingridAddItemQuantity.isEnabled = clickOnOff
                ingredientAddList.remove(item.name.toString())
            }

        }
        viewHolder.ingridAddItemQuantity.doAfterTextChanged {
            if (viewHolder.ingridAddItemQuantity.text.isNotEmpty()) {
                ingredientAddList?.put(
                    item.name.toString(),
                    viewHolder.ingridAddItemQuantity.text.toString().toDouble()
                )
            }
        }

    }

    fun setData(data: List<Ingredient>) {
        this.data = data
        notifyDataSetChanged()
    }

    fun getIngridAddList(): MutableMap<String?, Double?>? {

        return ingredientAddList
    }


    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var ingridAddName: TextView = view.findViewById(R.id.dish_add_ingrid_name_item)
        var ingridAddItemQuantity: EditText = view.findViewById(R.id.dish_add_ingrid_quant_item)
        var addDishCheckBox: CheckBox = view.findViewById(R.id.dish_add_checkbox)

    }

    override fun getItemCount() = data.size

}








