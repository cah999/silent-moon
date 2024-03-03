package com.example.silentmoon

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.databinding.ActivitySleepMusicBinding
import com.example.silentmoon.ui.sleep.SleepCard
import com.example.silentmoon.ui.sleep.SleepCardsAdapter
import com.example.silentmoon.ui.sleep.SleepSpaceDecoration

class SleepMusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySleepMusicBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySleepMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, SleepMusicPlayer::class.java)
        val cards = listOf(
            SleepCard(R.drawable.night_island, R.string.night_island, intent),
            SleepCard(R.drawable.sweet_sleep, R.string.sweet_sleep, intent),
            SleepCard(R.drawable.good_night, R.string.good_night, intent),
            SleepCard(R.drawable.moon_clouds, R.string.moon_clouds, intent),
            SleepCard(R.drawable.sweet_sleep, R.string.sweet_sleep, intent),
            SleepCard(R.drawable.night_island, R.string.night_island, intent),
            SleepCard(R.drawable.moon_clouds, R.string.moon_clouds, intent),
            SleepCard(R.drawable.good_night, R.string.good_night, intent)
        )
        val recommendations: RecyclerView = binding.sleepMusicCards
        recommendations.setHasFixedSize(true)
        recommendations.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = SleepCardsAdapter(context, cards)
        }
        recommendations.addItemDecoration(
            SleepSpaceDecoration(
                resources.getDimension(R.dimen.margin_10dp).toInt()
            )
        )

        binding.sleepMusicToolbar.setNavigationOnClickListener {
            finish()
        }
    }

}