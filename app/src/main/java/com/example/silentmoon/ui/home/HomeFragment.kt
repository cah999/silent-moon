package com.example.silentmoon.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.silentmoon.CourseActivity
import com.example.silentmoon.R
import com.example.silentmoon.databinding.FragmentHomeBinding

// todo rename drawables
// todo refactor this file pls)

data class HomeMeditation(
    val image: Int, val title: Int, val duration: Int
)

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val courseIntent by lazy { Intent(context, CourseActivity::class.java) }
    private val meditationData = listOf(
        HomeMeditation(R.drawable.focus_meditation, R.string.focus, R.string.duration),
        HomeMeditation(
            R.drawable.happiness_meditation, R.string.happiness, R.string.duration
        ),
    )
    private val courseData = listOf(
        HomeCourse(
            R.drawable.basics_course,
            R.string.basics,
            R.string.course,
            R.string.duration,
            R.string.light
        ), HomeCourse(
            R.drawable.relaxation_music,
            R.string.relaxation,
            R.string.music,
            R.string.duration,
            R.string.dark
        ), HomeCourse(
            R.drawable.daily_thought_meditation,
            R.string.daily_thought,
            R.string.meditation,
            R.string.duration,
            R.string.light
        )
    )
//    private val courseData = emptyList<HomeCourse>()
//    private val meditationData = emptyList<HomeMeditation>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupCourseRecyclerView()
        setupRecommendationsRecyclerView()

        return binding.root
    }

    private fun setupCourseRecyclerView() {
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (binding.homeRecycler.adapter?.getItemViewType(position)) {
                    HomeCourseAdapter.TYPE_BIG_CARD -> 2
                    else -> 1
                }
            }
        }
        binding.homeRecycler.apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
            adapter = HomeCourseAdapter(context, courseData, courseIntent)
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_10dp)))
        }
    }

    private fun setupRecommendationsRecyclerView() {
        binding.homeMeditationRecommendations.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
            adapter = HomeMeditationAdapter(context, meditationData, courseIntent)
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_20dp)))
        }
        PagerSnapHelper().attachToRecyclerView(binding.homeMeditationRecommendations)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

