package com.example.silentmoon.ui.meditate

import androidx.lifecycle.ViewModel
import com.example.silentmoon.R
import com.example.silentmoon.ui.home.HomeCourse
import com.example.silentmoon.ui.topic.Topic

class MeditateViewModel : ViewModel() {

    val bigCardData = HomeCourse(
        R.drawable.daily_calm_background,
        R.string.daily_calm,
        R.string.daily_calm_subtitle,
        R.string.pause_practice,
        R.string.dark
    )

    val topics = mutableListOf(
        Topic(R.drawable.days_of_calm_background, R.string.seven_days_of_calm, R.color.white),
        Topic(R.drawable.anxiety_release_background, R.string.anxiety_release, R.color.white),
        Topic(R.drawable.chill_background, R.string.chill, R.color.white),
        Topic(R.drawable.enjoying_life_background, R.string.enjoying_life, R.color.white)
    )
}