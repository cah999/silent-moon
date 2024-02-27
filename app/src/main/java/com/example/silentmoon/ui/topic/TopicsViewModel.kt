package com.example.silentmoon.ui.topic

import androidx.lifecycle.ViewModel
import com.example.silentmoon.R

class TopicsViewModel : ViewModel() {
    val topics = listOf(
        Topic(
            R.drawable.topic_reduce_stress_picture,
            R.string.reduce_stress,
            R.color.title_color
        ),
        Topic(
            R.drawable.topic_improve_performance_picture,
            R.string.improve_performance,
            R.color.topic_white
        ),
        Topic(
            R.drawable.topic_reduce_anxiety_picture,
            R.string.reduce_anxiety,
            R.color.dark_gray
        ),
        Topic(
            R.drawable.topic_increase_happiness_picture,
            R.string.increase_happiness,
            R.color.dark_gray
        ),
        Topic(
            R.drawable.topic_better_sleep_picture,
            R.string.better_sleep,
            R.color.topic_white
        ),
        Topic(
            R.drawable.topic_personal_growth_picture,
            R.string.personal_growth,
            R.color.title_color
        ),
        Topic(
            R.drawable.topic_improve_focus_picture,
            R.string.improve_focus,
            R.color.topic_white
        ),
        Topic(
            R.drawable.topic_reduce_pain_picture,
            R.string.reduce_pain,
            R.color.topic_white
        ),
    )
}