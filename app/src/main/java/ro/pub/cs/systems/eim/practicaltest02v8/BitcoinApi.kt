package ro.pub.cs.systems.eim.practicaltest02v8

import retrofit2.Call
import retrofit2.http.GET

interface BitcoinApi {
    @GET("currentprice.json")
    fun getBitcoinValue(
    ): Call<BitcoinResponse>
}