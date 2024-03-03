package com.example.silentmoon.ui.meditate

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.silentmoon.R
import com.example.silentmoon.databinding.FragmentMeditateBinding
import com.example.silentmoon.ui.home.HomeCourse
import com.example.silentmoon.ui.music.MusicActivity
import com.example.silentmoon.ui.topic.Topic
import com.example.silentmoon.ui.topic.TopicCardsAdapter

class MeditateFragment : Fragment(R.layout.fragment_meditate) {
    private lateinit var binding: FragmentMeditateBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentMeditateBinding.bind(view)

        setupCategoriesRecycler()
        setupBigCard()
        setupCardsRecyclerView()
    }

    private fun setupCategoriesRecycler() {
        binding.meditateCategories.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoriesAdapter("light")
        }
    }

    private fun setupBigCard() {
        val bigCardData = HomeCourse(
            R.drawable.daily_calm_bg,
            R.string.daily_calm,
            R.string.daily_calm_subtitle,
            R.string.pause_practice,
            R.string.dark
        )

        binding.bigCard.apply {
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

            playBtn.setBackgroundResource(R.drawable.ic_play)

            val intent = Intent(context, MusicActivity::class.java)
            playBtn.setOnClickListener { startActivity(intent) }
            bigCardBackground.setOnClickListener { startActivity(intent) }
        }
    }

    private fun setupCardsRecyclerView() {
        val intent = Intent(context, MusicActivity::class.java)
        val topics = mutableListOf(
            Topic(R.drawable.days_of_calm, R.string.seven_days_of_calm, R.color.white),
            Topic(R.drawable.anxiety_release, R.string.anxiety_release, R.color.white),
            Topic(R.drawable.chill_background, R.string.chill, R.color.white),
            Topic(R.drawable.enjoy_life, R.string.enjoying_life, R.color.white)
        )

        binding.sleepMusicCards.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = TopicCardsAdapter(context, intent).apply { submitList(topics) }
        }
    }
}

