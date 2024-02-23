package com.example.silentmoon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.silentmoon.databinding.ActivityMainBinding
import com.example.silentmoon.databinding.ActivityTopicsBinding

class TopicsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopicsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button4.setOnClickListener {
            val intent = Intent(this, TimeActivity::class.java)
            startActivity(intent)
        }
    }
}