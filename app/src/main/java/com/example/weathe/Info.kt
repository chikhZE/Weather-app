package com.example.weathe

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.widget.Toast

import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
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
        val hour = localtime.toString().substring(11, 13).toInt()

        val color = when (hour) {
            in 5..12 -> ContextCompat.getColor(this, R.color.early)
            in 13..18 -> ContextCompat.getColor(this, R.color.day)
            else -> ContextCompat.getColor(this, R.color.night)
        }
        binding.root.setBackgroundColor(color)

        binding.name.text = name
        binding.how.text = how
        binding.tempC.text = tempC
        binding.country.text = country
        binding.localtime.text = localtime

        Picasso.get().load("https:$icon").into(binding.img)

    }
}