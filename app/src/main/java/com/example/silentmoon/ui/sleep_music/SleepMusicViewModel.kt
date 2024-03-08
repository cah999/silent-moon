package com.example.silentmoon.ui.sleep_music

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.silentmoon.R
import com.example.silentmoon.ui.sleep.SleepCard

class SleepMusicViewModel : ViewModel() {
    fun getCards(intent: Intent): List<SleepCard> {
        return listOf(
            SleepCard(R.drawable.night_island_background, R.string.night_island, intent),
            SleepCard(R.drawable.sweet_sleep_background, R.string.sweet_sleep, intent),
            SleepCard(R.drawable.good_night_background, R.string.good_night, intent),
            SleepCard(R.drawable.moon_clouds_background, R.string.moon_clouds, intent),
            SleepCard(R.drawable.sweet_sleep_background, R.string.sweet_sleep, intent),
            SleepCard(R.drawable.night_island_background, R.string.night_island, intent),
            SleepCard(R.drawable.moon_clouds_background, R.string.moon_clouds, intent),
            SleepCard(R.drawable.good_night_background, R.string.good_night, intent)
        )
    }

}