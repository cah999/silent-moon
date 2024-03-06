package com.example.silentmoon.ui.time

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.silentmoon.databinding.ActivityTimeBinding
import com.example.silentmoon.ui.home.HomeActivity

class TimeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveBtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
//            Log.d("SELECTED TIME", binding.timePicker.getSelectedTime().toString())
        }

        binding.noThanksText.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }
    }
}