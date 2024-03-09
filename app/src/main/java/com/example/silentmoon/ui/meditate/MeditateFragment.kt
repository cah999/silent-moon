package com.example.silentmoon.ui.meditate

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.silentmoon.R
import com.example.silentmoon.databinding.FragmentMeditateBinding
import com.example.silentmoon.ui.course.CourseActivity
import com.example.silentmoon.ui.music.MusicActivity
import com.example.silentmoon.ui.topic.TopicCardsAdapter
import com.example.silentmoon.ui.utils.CategoriesAdapter
import com.example.silentmoon.ui.utils.CategoriesItemDecoration

class MeditateFragment : Fragment(R.layout.fragment_meditate) {
    private lateinit var binding: FragmentMeditateBinding
    private val viewModel by lazy { ViewModelProvider(this)[MeditateViewModel::class.java] }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMeditateBinding.bind(view)

        setupCategoriesRecycler()
        updateBigCard()
        setupCardsRecyclerView()
    }

    private fun setupCategoriesRecycler() {
        binding.meditateCategories.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoriesAdapter(context.getString(R.string.light))
        }
        binding.meditateCategories.setHasFixedSize(true)
        binding.meditateCategories.addItemDecoration(
            CategoriesItemDecoration(
                resources.getDimension(
                    R.dimen.text_margin
                ).toInt()
            )
        )
    }

    private fun updateBigCard() {
        val bigCardData = viewModel.bigCardData

        binding.meditateBigCard.apply {
            bigCardTitle.setText(bigCardData.title)
            bigCardSubtitle.setText(bigCardData.subtitle)
            bigCardSubtitle1.text = context?.getString(R.string.marker) ?: ""
            bigCardSubtitle2.setText(bigCardData.duration)
            bigCardBackground.setImageResource(bigCardData.image)

            val darkGray = ContextCompat.getColor(requireContext(), R.color.dark_gray)
            bigCardTitle.setTextColor(darkGray)
            bigCardSubtitle.setTextColor(darkGray)
            bigCardSubtitle1.setTextColor(darkGray)
            bigCardSubtitle2.setTextColor(darkGray)

            playBtn.setBackgroundResource(R.drawable.play_icon)

            val intent = Intent(context, CourseActivity::class.java)
            playBtn.setOnClickListener { startActivity(intent) }
            bigCardBackground.setOnClickListener { startActivity(intent) }
        }
    }

    private fun setupCardsRecyclerView() {
        val intent = Intent(context, MusicActivity::class.java)

        binding.sleepMusicCards.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = TopicCardsAdapter(context, intent, viewModel.topics)
        }
    }
}

