package com.example.weather.ui.home

import android.app.Application
import android.content.Context
import android.os.StrictMode
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.weather.getWeather
import kotlin.math.roundToInt


class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val _text = MutableLiveData<String>().apply {

        val policy =
            StrictMode.ThreadPolicy.Builder().permitAll().build() //supported by a responsive ui
        StrictMode.setThreadPolicy(policy)
        try {
            val sharedPreference =application.getSharedPreferences("FavoriteCities", Context.MODE_PRIVATE)
            val gg = getWeather(sharedPreference.getString("SelectedCity","Moscow").orEmpty())
            val advice = when(gg.weather){
                "Rain"-> "Если захочешь выйти из дома не забудь зонт"
                "Clouds"-> "Выйди за хлебом"
                "Clear"-> "Почему б и не позагорать на пляже"
                "Atmosphere"-> "Готовь противотуманки"
                "Snow"-> "Го в снежки"
                "Thunderstorm"-> "Держи крышу"
                "Drizzle"-> "Сиди дома за бортом гадость"
                else -> "¯\\_(ツ)_/¯"

            }
                value ="${(gg.temp.toDouble()-273.15).roundToInt()}°C ${gg.weather} \n $advice"
            } catch (e: Exception) {
                value = "¯\\_(ツ)_/¯"
                e.printStackTrace()
            }



    }
    val text: LiveData<String> = _text
}