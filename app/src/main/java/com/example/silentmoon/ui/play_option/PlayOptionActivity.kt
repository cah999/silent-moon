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

        val recyclerIntent = Intent(this, PlayOptionActivity::class.java)
        val recommendations: RecyclerView = binding.relatedCards
        recommendations.setHasFixedSize(true)
        val cards = listOf(
            SleepCard(R.drawable.moon_clouds, R.string.moon_clouds, recyclerIntent),
            SleepCard(R.drawable.sweet_sleep, R.string.sweet_sleep, recyclerIntent)
        )
        recommendations.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = SleepCardsAdapter(context, cards)
        }
        recommendations.addItemDecoration(
            SleepSpaceDecoration(
                resources.getDimension(R.dimen.margin_10dp).toInt()
            )
        )

        binding.playToolbar.setNavigationOnClickListener {
            finish()
        }

        binding.playButton.setOnClickListener {
            val playIntent = Intent(this, SleepMusicPlayer::class.java)
            startActivity(playIntent)
        }
    }
}