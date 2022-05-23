package com.example.example

import com.google.gson.annotations.SerializedName


data class HttpResponse(

    @SerializedName("response") var response: String?,
    @SerializedName("code") var code: String?

)