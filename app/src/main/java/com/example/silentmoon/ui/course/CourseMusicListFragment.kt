package com.example.silentmoon.ui.course

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.CourseMusicListBinding
import com.example.silentmoon.ui.music.MusicActivity

class CourseMusicListFragment(
    private val courseItems: List<CourseItem>
) : Fragment(R.layout.course_music_list) {
    private lateinit var binding: CourseMusicListBinding



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = CourseMusicListBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = binding.containerRecyclerView
        val intent = Intent(context, MusicActivity::class.java)
        recyclerView.setHasFixedSize(true)
        recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = CourseMusicListAdapter(context, courseItems, intent)
        }

        val decoration = CustomDividerItemDecoration(recyclerView.context)
        recyclerView.addItemDecoration(decoration)
    }
}