package com.praveen.android.newstore.repository

import com.praveen.android.newstore.application.NewStoreApplication
import com.praveen.android.newstore.model.SummaryCart
import com.praveen.android.newstore.utils.FileUtility
import okhttp3.*
import java.io.IOException
import java.io.InputStream

class MockInterceptor : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {
        val uri = chain.request().url().uri().toString()

        when {
            uri.endsWith("StoreInfo") -> {
                var responseString = ""
                responseString = getPositiveResponseJson
                return chain.proceed(chain.request())
                    .newBuilder()
                    .code(200)
                    .protocol(Protocol.HTTP_2)
                    .message(responseString)
                    .body(
                        ResponseBody.create(
                            MediaType.parse("application/json"),
                            loadJSONFile("StoreInformation.json")?.toByteArray()
                        )
                    )
                    .addHeader("content-type", "application/json")
                    .build()
            }
            uri.endsWith("Products") -> {
                var responseString = ""
                responseString = getPositiveResponseJson
                return chain.proceed(chain.request())
                    .newBuilder()
                    .code(200)
                    .protocol(Protocol.HTTP_2)
                    .message(responseString)
                    .body(
                        ResponseBody.create(
                            MediaType.parse("application/json"),
                            loadJSONFile("product.json")?.toByteArray()
                        )
                    )
                    .addHeader("content-type", "application/json")
                    .build()
            }
            uri.endsWith("orderDone") -> {
                var responseString = ""
                responseString = getPositiveResponseJson
                FileUtility.create(
                    NewStoreApplication.ctx,
                    "order",
                    SummaryCart.productArray.product.toString()
                )
                return chain.proceed(chain.request())
                    .newBuilder()
                    .code(200)
                    .protocol(Protocol.HTTP_2)
                    .message(responseString)
                    .addHeader("content-type", "application/json")
                    .body(
                        ResponseBody.create(
                            MediaType.parse("application/json"),
                            responseString
                        )
                    )
                    .build()
            }
            else -> {
                return chain.proceed(chain.request())
            }
        }

    }

    val getPositiveResponseJson = """
        {
            "response": "Success",
            "code": 200
        }"""

    fun loadJSONFile(fileName: String): String? {
        var json: String? = null
        json = try {
            val inputStream: InputStream = NewStoreApplication.ctx.assets.open(fileName)
            val size: Int = inputStream.available()
            val byteArray = ByteArray(size)
            inputStream.read(byteArray)
            inputStream.close()
            String(byteArray, charset("UTF-8"))
        } catch (e: IOException) {
            e.printStackTrace()
            return null
        }
        return json
    }
}