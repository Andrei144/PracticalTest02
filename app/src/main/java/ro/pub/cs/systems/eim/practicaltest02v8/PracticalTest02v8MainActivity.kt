package ro.pub.cs.systems.eim.practicaltest02v8

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PracticalTest02v8MainActivity : AppCompatActivity() {

    val TAG = "PracticalTest02v8MainActivity"


    lateinit var valueBitcoinTextView: TextView
    lateinit var valutaEditText: EditText
    lateinit var getBitcoinButton: Button
    lateinit var toNextActivityButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        valueBitcoinTextView = findViewById(R.id.valueBitcoinTextView)
        valutaEditText = findViewById(R.id.valutaEditText)
        getBitcoinButton = findViewById(R.id.getBitcoinBtn)
        toNextActivityButton = findViewById(R.id.toNextActivityButton)

        getBitcoinButton.setOnClickListener {
            getBitcoinRate()
        }

        toNextActivityButton.setOnClickListener {
            val intent = Intent(this, SecondAcitivity::class.java)
            startActivity(intent)
        }

    }

    private fun getBitcoinRate(){
        var responseRate: String?
        val openWeatherApi = AppClient().getClient().create(BitcoinApi::class.java)
        val call = openWeatherApi.getBitcoinValue()

        call.enqueue(object : Callback<BitcoinResponse> {
            override fun onResponse(
                call: Call<BitcoinResponse>, response: Response<BitcoinResponse>
            ) {
                if (response.isSuccessful) {
                    val bitcoinResponse = response.body()
                    if (bitcoinResponse != null) {
                        val time = bitcoinResponse.time.updated
                        val usdRate = bitcoinResponse.bpi.usd.rate
                        val euroRate = bitcoinResponse.bpi.eur.rate


                        responseRate = "Bitcoin rate in Euro is ${euroRate} and in USD is " +
                                "${usdRate} at ${time}."

                        runOnUiThread {
                            valueBitcoinTextView.text = responseRate
                        }
                        Log.d(TAG, responseRate!!)
                    }
                } else {
                    Log.e(TAG, "Error: ${response.code()}\nMessage: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<BitcoinResponse>, t: Throwable) {
                Log.e(TAG, "Error: ${t.message}")
            }
        })
    }
}