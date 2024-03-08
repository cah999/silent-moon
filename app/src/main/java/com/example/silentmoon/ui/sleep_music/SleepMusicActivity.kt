package com.example.silentmoon.ui.sleep_music

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ActivitySleepMusicBinding
import com.example.silentmoon.ui.sleep.SleepCardsAdapter
import com.example.silentmoon.ui.sleep.SleepSpaceDecoration
import com.example.silentmoon.ui.sleep_player.SleepMusicPlayer

class SleepMusicActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySleepMusicBinding
    private lateinit var viewModel: SleepMusicViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySleepMusicBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[SleepMusicViewModel::class.java]
        val intent = Intent(this, SleepMusicPlayer::class.java)

        val recommendations: RecyclerView = binding.sleepMusicCards
        recommendations.setHasFixedSize(true)
        recommendations.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = SleepCardsAdapter(context, viewModel.getCards(intent))
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