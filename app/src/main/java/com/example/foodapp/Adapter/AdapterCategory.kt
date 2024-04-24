package com.example.foodapp.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodapp.databinding.ItemcategoryBinding
import com.example.foodapp.model.Category

class AdapterCategory : RecyclerView.Adapter<AdapterCategory.Holder>() {
    var categoryList : List<Category>?=null
    private lateinit var onClick: (String) -> Unit?
    fun setOnClic(onClic:(String)->Unit){
        this.onClick =onClic
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val binding =ItemcategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return Holder(binding)
    }

    override fun getItemCount(): Int {
        return categoryList?.size ?: 0
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val data = categoryList!![position]
        holder.bind(data)
    }
    private lateinit var onLongClick: (Int) -> Unit?
    fun setOnLongClick(onLongClick: (Int) -> Unit) {
        this.onLongClick = onLongClick
    }

    inner class Holder(val binding: ItemcategoryBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            binding.root.setOnClickListener {
                onClick.invoke(categoryList!![layoutPosition].strCategory)
            }
        }
        fun bind(categoryList: Category) {
            binding.apply {

                Glide.with(binding.root.context)
                    .load(categoryList.strCategoryThumb)
                    .into(imgCategory)
                tvCategoryName.text = categoryList.strCategory

            }

        }
    }
}