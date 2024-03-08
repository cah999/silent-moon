package com.example.silentmoon.ui.course

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class CoursePagerAdapter(
    fragment: FragmentActivity,
    private val items: List<CourseItem>
) : FragmentStateAdapter(fragment) {
    override fun getItemCount(): Int = 2;

    override fun createFragment(position: Int): Fragment {
        val fragment = CourseMusicListFragment(items);
        return fragment;
    }
}