package com.example.example

import com.google.gson.annotations.SerializedName


data class StoreResponse(

    @SerializedName("StoreInfo") var StoreInfo: String? = null,
    @SerializedName("Description") var Description: String? = null

)