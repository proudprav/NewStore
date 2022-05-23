package com.praveen.android.newstore.repository

import com.example.example.HttpResponse
import com.example.example.ProductResponse
import com.example.example.StoreResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST

interface APIService {

    @GET("StoreInfo")
    fun getStoreInformation(): Call<StoreResponse>

    @GET("Products")
    fun getProductList(): Call<ProductResponse>

    @POST("orderDone")
    fun postOrder(): Call<HttpResponse>
}