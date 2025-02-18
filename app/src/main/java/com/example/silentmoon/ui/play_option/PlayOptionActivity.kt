package com.example.silentmoon.ui.play_option

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ActivityPlayOptionBinding
import com.example.silentmoon.ui.sleep.SleepCard
import com.example.silentmoon.ui.sleep.SleepCardsAdapter
import com.example.silentmoon.ui.sleep.SleepSpaceDecoration
import com.example.silentmoon.ui.sleep_player.SleepMusicPlayer

class PlayOptionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPlayOptionBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayOptionBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val intent = Intent(this, PlayOptionActivity::class.java)
        val relatedRecycler: RecyclerView = binding.relatedCards
        relatedRecycler.setHasFixedSize(true)
        val cards = listOf(
            SleepCard(R.drawable.good_night_background, R.string.moon_clouds, intent),
            SleepCard(R.drawable.sweet_sleep_background, R.string.sweet_sleep, intent)
        )
        relatedRecycler.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = SleepCardsAdapter(context, cards)
        }
        relatedRecycler.addItemDecoration(
            SleepSpaceDecoration(
                resources.getDimension(R.dimen.margin_10dp).toInt()
            )
        )

        binding.playToolbar.setNavigationOnClickListener {
            finish()
        }

        binding.sleepPlayBtn.setOnClickListener {
            val playIntent = Intent(this, SleepMusicPlayer::class.java)
            startActivity(playIntent)
        }

        binding.playToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}