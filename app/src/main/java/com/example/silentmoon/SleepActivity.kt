package com.example.silentmoon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.silentmoon.databinding.ActivityMusicBinding
import com.example.silentmoon.databinding.ActivitySleepBinding
import com.example.silentmoon.ui.sleep.SleepFragment

class SleepActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySleepBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySleepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button12.setOnClickListener {
            finish()
        }
    }
}