package com.example.weathe

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weathe.databinding.ActivityMainBinding
import org.json.JSONArray
import org.json.JSONObject
import java.net.HttpURLConnection
import java.net.URL
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.searchBtn.setOnClickListener {
            if (binding.searchBar.text.toString().trim() != "") {
                fetchData("https://api.weatherapi.com/v1/current.json?key=47e9cf9b7636453890f173006240601&q=${binding.searchBar.text.toString()}&aqi=no")
            }
        }
    }
    private fun fetchData(urlString: String) {
        Thread {
            try {
                val url = URL(urlString)
                val connection = url.openConnection() as HttpURLConnection
                connection.requestMethod = "GET"
                connection.connect()

                val responseText = connection.inputStream.bufferedReader().readText()
                val jsonObject = JSONObject(responseText)

                val name = jsonObject.getJSONObject("location").getString("name")
                val country = jsonObject.getJSONObject("location").getString("country")
                val localtime = jsonObject.getJSONObject("location").getString("localtime")
                val temp_c = jsonObject.getJSONObject("current").getString("temp_c")
                val how = jsonObject.getJSONObject("current").getJSONObject("condition").getString("text")
                val icon = jsonObject.getJSONObject("current").getJSONObject("condition").getString("icon")

                runOnUiThread {
                    val i = Intent(this,Info::class.java)
                    i.putExtra("name",name)
                    i.putExtra("country",country)
                    i.putExtra("localtime",localtime)
                    i.putExtra("temp_c",temp_c)
                    i.putExtra("how",how)
                    i.putExtra("icon",icon)
                    startActivity(i)
                }

            }catch(e:Exception) {
                runOnUiThread {
                    Toast.makeText(this,"error",Toast.LENGTH_SHORT).show()
                }
            }
        }.start()
    }
}