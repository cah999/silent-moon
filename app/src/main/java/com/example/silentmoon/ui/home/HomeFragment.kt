package com.example.silentmoon.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.silentmoon.R
import com.example.silentmoon.databinding.FragmentHomeBinding
import com.example.silentmoon.ui.course.CourseActivity
import com.example.silentmoon.ui.meditate.CategoriesItemDecoration


class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by lazy { ViewModelProvider(this)[HomeViewModel::class.java] }
    private val nextIntent by lazy { Intent(context, CourseActivity::class.java) }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        val sharedPreferences = requireActivity().getSharedPreferences(
            getString(R.string.mysharedpref),
            AppCompatActivity.MODE_PRIVATE
        )
        var name = sharedPreferences.getString(
            getString(R.string.name),
            getString(R.string.default_nickname)
        )
        if (name != null) {
            if (name.isEmpty()) {
                name = getString(R.string.default_nickname)
            }
        }
        binding.homeTitle.text = binding.homeTitle.text.toString().format(name)
        setupCourseRecycler()
        setupMeditationRecycler()

        return binding.root
    }

    private fun setupCourseRecycler() {
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
            adapter = HomeCourseAdapter(context, viewModel.courseData, nextIntent)
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_5dp)))
        }
    }

    private fun setupMeditationRecycler() {
        binding.homeMeditationRecommendations.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
            adapter = HomeMeditationAdapter(context, viewModel.meditationData, nextIntent)
            addItemDecoration(CategoriesItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_10dp)))
        }
    }
}

