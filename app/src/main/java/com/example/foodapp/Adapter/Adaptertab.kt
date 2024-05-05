package com.example.foodapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.ItemtabBinding
import com.example.foodapp.model.PMeal

class Adaptertab:RecyclerView.Adapter<Adaptertab.Holder>() {

    var mealList: List<PMeal>? = null
    private lateinit var onClick: (Int) -> Unit?
    fun setOnClick(onClick: (Int) -> Unit) {
        this.onClick = onClick
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding = ItemtabBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return mealList?.size ?: 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = mealList!![position]
        holder.bind(data)
    }
    private lateinit var onLongClick: (Int) -> Unit?
    fun setOnLongClick(onLongClick: (Int) -> Unit) {
        this.onLongClick = onLongClick
    }
    inner class Holder(val binding: ItemtabBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick.invoke(mealList!![layoutPosition].idMeal.toInt())
            }
            binding.root.setOnLongClickListener {
                onLongClick.invoke(mealList!![layoutPosition].idMeal.toInt())
                true
            }
        }
        fun bind(mealList: PMeal) {
            Glide.with(binding.root.context)
                .load(mealList.strMealThumb)
                .into(binding.imgMeal)
        }
    }

}

