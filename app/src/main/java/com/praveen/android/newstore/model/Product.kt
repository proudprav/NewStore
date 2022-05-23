package com.example.example

import com.google.gson.annotations.SerializedName


data class Product(

    @SerializedName("name") var name: String? = null,
    @SerializedName("desc") var desc: String? = null,
    @SerializedName("price") var price: Int? = null,
    var addToCart: Boolean = false
)