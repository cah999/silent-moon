package com.example.silentmoon.ui.sleep

import android.content.Intent
import androidx.lifecycle.ViewModel
import com.example.silentmoon.R

class SleepViewModel : ViewModel() {

    fun getCards(nextIntent: Intent): List<SleepCard> {
        return listOf(
            SleepCard(R.drawable.night_island_background, R.string.night_island, nextIntent),
            SleepCard(R.drawable.sweet_sleep_background, R.string.sweet_sleep, nextIntent),
            SleepCard(R.drawable.good_night_background, R.string.good_night, nextIntent),
            SleepCard(R.drawable.moon_clouds_background, R.string.moon_clouds, nextIntent)
        )
    }
}