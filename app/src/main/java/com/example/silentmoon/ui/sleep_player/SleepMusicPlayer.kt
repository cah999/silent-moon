package com.example.silentmoon.ui.sleep_player

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.example.silentmoon.databinding.ActivitySleepMusicPlayerBinding

class SleepMusicPlayer : AppCompatActivity() {
    private lateinit var binding: ActivitySleepMusicPlayerBinding
    private var currentTimeInSeconds = 0
    private val handler = Handler(Looper.getMainLooper())
    private val runnable = object : Runnable {
        override fun run() {
            if (binding.sleepMusicPlayBtn.isChecked && currentTimeInSeconds < 2700) {
                currentTimeInSeconds++
                updateSliderAndTime()
            }
            if (currentTimeInSeconds == 2700) {
                binding.sleepMusicPlayBtn.isChecked = false
            }
            handler.postDelayed(this, 1000)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySleepMusicPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.sleepMusicSlider.valueFrom = 0f
        binding.sleepMusicSlider.valueTo = 2700f
        binding.sleepSleepMusicToolbar.setNavigationOnClickListener {
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

        binding.sleepMusicPlayBtn.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                handler.postDelayed(runnable, 1000)
            } else {
                handler.removeCallbacks(runnable)
            }
        }

        binding.sleepMusicSlider.addOnChangeListener { _, value, _ ->
            currentTimeInSeconds = value.toInt()
            updateSliderAndTime()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacks(runnable)
    }

    private fun updateSliderAndTime() {
        binding.sleepMusicSlider.value = currentTimeInSeconds.toFloat()
        binding.startSleepMusicTime.text = formatTime(currentTimeInSeconds)
    }

    private fun formatTime(seconds: Int): String {
        val minutes = seconds / 60
        val remainingSeconds = seconds % 60
        return String.format("%02d:%02d", minutes, remainingSeconds)
    }
}