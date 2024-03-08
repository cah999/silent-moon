package com.example.silentmoon.ui.course

import androidx.lifecycle.ViewModel
import com.example.silentmoon.R

class CourseViewModel : ViewModel() {
    val voices = listOf(
        CourseItem(R.string.focus_attention, R.string.duration_10, "accent"),
        CourseItem(R.string.body_scan, R.string.duration_5, "regular"),
        CourseItem(R.string.making_happiness, R.string.duration_3, "regular")
    )
}