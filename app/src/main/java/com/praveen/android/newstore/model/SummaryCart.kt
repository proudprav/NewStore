package com.praveen.android.newstore.model

import com.example.example.Product
import com.example.example.ProductResponse

object SummaryCart {
    val productArray = ProductResponse()
    var address: String = ""

    fun totalPrice() {
        var sum = 0
        productArray.product.forEach {
            sum += it.price!!
        }
    }

    fun totalItems() {
        productArray.product.size
    }

    fun addProduct(product: Product) {
        productArray.product.add(product)
    }

    fun removeProduct(product: Product) {
        productArray.product.remove(product)
    }

    fun clearCart() {
        productArray.product.clear()
    }
}