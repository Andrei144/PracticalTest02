package ro.pub.cs.systems.eim.practicaltest02v8

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class AppClient {

    private val BASE_URL = "https://api.coindesk.com/v1/bpi/"
    private var retrofit : Retrofit? = null

    fun getClient() : Retrofit {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit!!
    }
}