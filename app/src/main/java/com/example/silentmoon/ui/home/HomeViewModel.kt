package com.example.silentmoon.ui.home

import androidx.lifecycle.ViewModel
import com.example.silentmoon.R

class HomeViewModel : ViewModel() {

    val courseData = listOf(
        HomeCourse(
            R.drawable.basics_course,
            R.string.basics,
            R.string.course,
            R.string.duration,
            R.string.light
        ), HomeCourse(
            R.drawable.relaxation_music_background,
            R.string.relaxation,
            R.string.music,
            R.string.duration,
            R.string.dark
        ), HomeCourse(
            R.drawable.daily_thought_background,
            R.string.daily_thought,
            R.string.meditation,
            R.string.duration,
            R.string.light
        )
    )

    val meditationData = listOf(
        HomeMeditation(R.drawable.focus_meditation_background, R.string.focus, R.string.duration),
        HomeMeditation(
            R.drawable.happiness_meditation_background, R.string.happiness, R.string.duration
        ),
        HomeMeditation(R.drawable.focus_meditation_background, R.string.focus, R.string.duration),
    )
}