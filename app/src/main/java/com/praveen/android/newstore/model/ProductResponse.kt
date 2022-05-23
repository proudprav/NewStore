package com.example.example

import com.google.gson.annotations.SerializedName


data class ProductResponse(

    @SerializedName("product") var product: ArrayList<Product> = arrayListOf()

)