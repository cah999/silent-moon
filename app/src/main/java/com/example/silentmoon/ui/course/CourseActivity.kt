package com.example.silentmoon.ui.course

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ActivityCourseBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

// todo replace play icons
class CourseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCourseBinding
    private val tabNames = arrayOf(R.string.male_voice, R.string.female_voice);
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseBinding.inflate(layoutInflater);
        setContentView(binding.root);
        binding.favoritesIcon.setOnClickListener {}
        binding.downloadIcon.setOnClickListener {}
        val courseTab: TabLayout = binding.courseTab;
        val coursePager: ViewPager2 = binding.coursePager;
        coursePager.adapter = CoursePagerAdapter(this);
        TabLayoutMediator(courseTab, coursePager) { tab, position ->
            tab.setText(tabNames[position]);
        }.attach();
        courseTab.tabTextColors = ContextCompat.getColorStateList(this, R.color.tab_text_color)
    }
}

