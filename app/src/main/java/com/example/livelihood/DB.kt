package com.example.livelihood

import okhttp3.*
import java.io.IOException

private val client = OkHttpClient().newBuilder().addInterceptor { chain ->
    val originalRequest = chain.request()

    val builder = originalRequest.newBuilder()
        .header("Authorization", Credentials.basic("android", "12345"))

    val newRequest = builder.build()
    chain.proceed(newRequest)
}.build()

fun getAPI(url: String, callback:(result: String?) -> Unit) {
    val request = Request.Builder()
        .url(url)
        .build()

    client.newCall(request).enqueue(object: Callback {
        override fun onFailure(call: Call, e: IOException) {
            e.printStackTrace()
        }

        override fun onResponse(call: Call, response: Response) {
            response.use {
                if (!response.isSuccessful) throw IOException("Unexpected code $response")

                callback.invoke(response.body()!!.string())
            }
        }
    })
}
