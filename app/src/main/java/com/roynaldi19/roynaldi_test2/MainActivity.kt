package com.roynaldi19.roynaldi_test2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.roynaldi19.roynaldi_test2.api.ApiConfig
import com.roynaldi19.roynaldi_test2.databinding.ActivityMainBinding
import com.roynaldi19.roynaldi_test2.model.WeatherResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGetWeather.setOnClickListener {
            getCurrentWeather()
        }
    }

    private fun getCurrentWeather() {
        val client = ApiConfig.getApiService().getWeather()
        client.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.isSuccessful) {
                    val responseBody = response.body()
                    if (responseBody != null) {
                        val date = responseBody.list.dtTxt.toString()
                        val suhuKf = responseBody.list.main.tempKf
                        val suhuCelcius = suhuKf - 273
                        val message = "$date, $suhuCelcius.toString()"
                        Toast.makeText(this@MainActivity,message, Toast.LENGTH_LONG).show()
                        Log.i("list", message)
                    }

                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("onFailure", t.message.toString())

            }

        })
    }




}