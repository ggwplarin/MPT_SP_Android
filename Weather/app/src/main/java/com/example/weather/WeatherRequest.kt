package com.example.weather

import android.os.AsyncTask
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
private class WeatherRequest {

}
    fun getWeather(q:String):WeatherModel{

        val client = OkHttpClient()
        val request = Request.Builder().url("https://api.openweathermap.org/data/2.5/weather?q=$q&appid=${BuildConfig.API_KEY}").build()
        val response = client.newCall(request).execute().body?.string()

        return WeatherModel(JSONObject(response).get("cod").toString(),JSONObject(response).getJSONObject("main").get("temp").toString(),JSONObject(response).getJSONArray("weather").getJSONObject(0).get("main").toString())
    }
