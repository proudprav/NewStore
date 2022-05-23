package com.praveen.android.newstore.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Product
import com.praveen.android.newstore.databinding.ItemSummaryBinding

class CartSummaryRecyclerViewAdapter :
    RecyclerView.Adapter<CartSummaryRecyclerViewAdapter.ViewHolder>() {

    var values = ArrayList<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemSummaryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    fun setItems(items: ArrayList<Product>) {
        values.addAll(items)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.viewBinding(values, position)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemSummaryBinding) : RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.tvProductTitle
        val price: TextView = binding.tvPrice

        fun viewBinding(product: ArrayList<Product>, position: Int) {
            title.text = product[position].name
            price.text = product[position].price.toString()

        }
    }
}