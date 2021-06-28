package com.hfad.coffeepos.presentation.main.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.hfad.coffeepos.R

class ChoiceImageAdapter internal constructor(
    private var data: List<Int>,
) : RecyclerView.Adapter<ChoiceImageAdapter.ViewHolder>() {

    private var listener: ChoiceImageItemClickListener? = null

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_choise_image, viewGroup, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = data[position]
        viewHolder.image.setImageResource(item)
        viewHolder.itemView.setOnClickListener {
            listener?.onItemClick(item)
        }
    }

    fun setData(data: List<Int>) {
        this.data = data
        notifyDataSetChanged()
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.img_choice)
    }

    override fun getItemCount() = data.size

    fun setListener(choiceImageItemClickListener: ChoiceImageItemClickListener?) {
        listener = choiceImageItemClickListener
    }
}
interface ChoiceImageItemClickListener {
    fun onItemClick(image: Int)
}