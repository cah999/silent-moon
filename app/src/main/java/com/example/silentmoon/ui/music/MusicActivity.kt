package com.example.silentmoon.ui.music

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.silentmoon.databinding.ActivityMusicBinding

class MusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMusicBinding
    private var currentTimeInSeconds = 0
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            if (binding.musicPlayBtn.isChecked && currentTimeInSeconds < 2700) {
                currentTimeInSeconds++
                updateSliderAndTime()
            }
            if (currentTimeInSeconds == 2700) {
                binding.musicPlayBtn.isChecked = false
            }
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.musicSlider.valueFrom = 0f
        binding.musicSlider.valueTo = 2700f
        binding.musicToolbar.setNavigationOnClickListener {
            finish()
        }

        binding.plus15SecondBtn.setOnClickListener {
            if (currentTimeInSeconds < 2700) {
                currentTimeInSeconds += 15
                updateSliderAndTime()
            }
        }

        binding.minus15SecondsBtn.setOnClickListener {
            if (currentTimeInSeconds > 0) {
                currentTimeInSeconds -= 15
                updateSliderAndTime()
            }
        }

        binding.musicPlayBtn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                handler.postDelayed(runnable, 1000)
            } else {
                handler.removeCallbacks(runnable)
            }
        }

        binding.musicSlider.addOnChangeListener { _, value, _ ->
            currentTimeInSeconds = value.toInt()
            updateSliderAndTime()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    private fun updateSliderAndTime() {
        binding.musicSlider.value = currentTimeInSeconds.toFloat()
        binding.startMusicTime.text = formatTime(currentTimeInSeconds)
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }
}