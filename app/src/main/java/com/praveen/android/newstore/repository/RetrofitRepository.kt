package com.praveen.android.newstore.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.example.HttpResponse
import com.example.example.ProductResponse
import com.example.example.StoreResponse
import com.google.gson.Gson
import com.praveen.android.newstore.application.NewStoreApplication
import com.praveen.android.newstore.di.APIComponent
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import javax.inject.Inject

class RetrofitRepository {
    private var apiComponent: APIComponent = NewStoreApplication.apiComponent

    @Inject
    lateinit var retrofit: Retrofit
    @Inject
    lateinit var gson : Gson

    init {
        apiComponent.inject(this)
    }

    fun fetchStoreInformation(): LiveData<StoreResponse?> {
        val apiService: APIService = retrofit.create(APIService::class.java)
        val listOfNews = apiService.getStoreInformation()
        val res: MutableLiveData<StoreResponse> = MutableLiveData()
        listOfNews.enqueue(object : Callback<StoreResponse> {
            override fun onResponse(
                call: Call<StoreResponse>, response: Response<StoreResponse>
            ) {
                if (response.isSuccessful) {
                    res.value = response.body()
                }
            }

            override fun onFailure(call: Call<StoreResponse>, t: Throwable) {
                Log.d("RetroRepository", "Failed:::" + t.message)
            }
        })

        return res
    }

    fun fetchProductItem(): LiveData<ProductResponse?> {
        val apiService: APIService = retrofit.create(APIService::class.java)
        val listOfNew = apiService.getProductList()
        val res: MutableLiveData<ProductResponse> = MutableLiveData()
        listOfNew.enqueue(object : Callback<ProductResponse> {
            override fun onResponse(
                call: Call<ProductResponse>, response: Response<ProductResponse>
            ) {
                if (response.isSuccessful) {
                    res.value = response.body()
                }
            }

            override fun onFailure(call: Call<ProductResponse>, t: Throwable) {
                Log.d("RetroRepository", "Failed:::" + t.message)
            }
        })

        return res
    }

    fun postOrder(): LiveData<HttpResponse> {
        val apiService: APIService = retrofit.create(APIService::class.java)
        val listOfNew = apiService.postOrder()
        val res: MutableLiveData<HttpResponse> = MutableLiveData()
        listOfNew.enqueue(object : Callback<HttpResponse> {
            override fun onResponse(
                call: Call<HttpResponse>, response: Response<HttpResponse>
            ) {
                if (response.isSuccessful) {
                    res.value = response.body()
                }
            }

            override fun onFailure(call: Call<HttpResponse>, t: Throwable) {
                Log.d("RetroRepository", "Failed:::" + t.message)
            }
        }
        )
        return res
    }


}



