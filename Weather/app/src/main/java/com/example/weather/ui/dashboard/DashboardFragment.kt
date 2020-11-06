package com.example.weather.ui.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.weather.R

class DashboardFragment : Fragment() {
    private lateinit var btn: Button
    private lateinit var citiesList:ListView
    private lateinit var editTextCity:EditText
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater!!.inflate(R.layout.fragment_dashboard, container, false)
        val sharedPreference = this.context?.getSharedPreferences("FavoriteCities", Context.MODE_PRIVATE)
        val Cities:MutableList<String> =(sharedPreference?.getStringSet("FavoriteCities", null)?.toList()).orEmpty().toMutableList()
        var listItems =  Cities.toTypedArray()
        btn = view.findViewById(R.id.addCityBtn)
        citiesList= view.findViewById(R.id.ListViewCities)
        editTextCity = view.findViewById(R.id.editTextCity)
        registerForContextMenu(citiesList)
        val adapter = ArrayAdapter(this.requireContext(),android.R.layout.simple_list_item_1,listItems)
        citiesList.adapter = adapter
        btn.setOnClickListener {
            Cities.add(editTextCity.text.toString())
            val editor = sharedPreference?.edit()
            editor?.putStringSet("FavoriteCities",Cities.toSet())
            editor?.apply()
            Log.i("gg",Cities.toString())
            listItems =  Cities.toTypedArray()
            citiesList.adapter = ArrayAdapter(this.requireContext(),android.R.layout.simple_list_item_1,listItems)
        }
        citiesList.setOnItemClickListener{parent, view, position, id ->
            Log.i("ff",Cities[position])
            val editor = sharedPreference?.edit()
            editor?.putString("SelectedCity",Cities[position])
            editor?.apply()
        }
        citiesList.setOnItemLongClickListener { parent, view, position, id ->
            Log.i("pp",Cities[position])
            Cities.removeAt(position)
            val editor = sharedPreference?.edit()
            editor?.putStringSet("FavoriteCities",Cities.toSet())
            editor?.apply()
            listItems =  Cities.toTypedArray()
            citiesList.adapter = ArrayAdapter(this.requireContext(),android.R.layout.simple_list_item_1,listItems)
            true
        }
        return view
    }

}