package ro.pub.cs.systems.eim.practicaltest02v8

import com.google.gson.annotations.SerializedName

data class BitcoinResponse(
    @SerializedName("time")
    val time: Time,

    @SerializedName("bpi")
    val bpi: Bpi
)

// Nested Time Class
data class Time(
    @SerializedName("updated")
    val updated: String,
)

// Nested Bpi Class
data class Bpi(
    @SerializedName("USD")
    val usd: Currency,

    @SerializedName("EUR")
    val eur: Currency
)

// Nested Currency Class
data class Currency(
    @SerializedName("rate")
    val rate: String,

    @SerializedName("rate_float")
    val rateFloat: Float
)

