package com.praveen.android.newstore.ui.view

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.example.Product
import com.praveen.android.newstore.databinding.ItemProductlistBinding

class ProductItemListRecyclerViewAdapter(val clickListener: ClickListener) :
    RecyclerView.Adapter<ProductItemListRecyclerViewAdapter.ViewHolder>() {

    var values = ArrayList<Product>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            ItemProductlistBinding.inflate(
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
        holder.viewBinding(values, position, clickListener)
    }

    override fun getItemCount(): Int = values.size

    inner class ViewHolder(binding: ItemProductlistBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val title: TextView = binding.tvProductTitle
        val desc: TextView = binding.tvProductDesc
        val price: TextView = binding.tvProductDesc
        val addToCartButton: Button = binding.btnAddtocart

        fun viewBinding(product: ArrayList<Product>, position: Int, clickListener: ClickListener) {
            title.text = product[position].name
            desc.text = product[position].desc
            price.text = product[position].price.toString()

            if (product[position].addToCart) {
                addToCartButton.isEnabled = false
                addToCartButton.text = "Added to cart"
            }
            addToCartButton.setOnClickListener {
                product[position].addToCart = true
                clickListener.onButtonClickListener(product[position])
                notifyItemChanged(position)
            }
        }
    }

    interface ClickListener {
        fun onButtonClickListener(product: Product)
    }
}