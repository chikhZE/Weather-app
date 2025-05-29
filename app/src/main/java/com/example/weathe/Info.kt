package com.example.weathe

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.weathe.databinding.ActivityInfoBinding
import com.squareup.picasso.Picasso

class Info : AppCompatActivity() {
    lateinit var binding : ActivityInfoBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityInfoBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val name = intent.getStringExtra("name")
        val how = intent.getStringExtra("how")
        val tempC = intent.getStringExtra("temp_c")
        val country = intent.getStringExtra("country")
        val localtime = intent.getStringExtra("localtime")
        val icon = intent.getStringExtra("icon")


        binding.name.text = name
        binding.how.text = how
        binding.tempC.text = tempC
        binding.country.text = country
        binding.localtime.text = localtime
    Picasso.get().load(icon.toString()).into(binding.img)

    }
}