package com.example.silentmoon.ui.topic

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.TextAppearanceSpan
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.silentmoon.R
import com.example.silentmoon.TimeActivity
import com.example.silentmoon.databinding.ActivityTopicsBinding


class TopicsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopicsBinding
    private lateinit var viewModel: TopicsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(TopicsViewModel::class.java)

        binding.topicsSubtitle.setOnClickListener {
            startActivity(Intent(this, TimeActivity::class.java))
        }

        binding.topicsRecycler.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = TopicCardsAdapter(context).apply {
                submitList(viewModel.topics)
            }
        }

        with(SpannableStringBuilder(getString(R.string.topics_title))) {
            setSpan(
                TextAppearanceSpan(this@TopicsActivity, R.style.title),
                0,
                15,
                Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
            )
            binding.topicsTitle.text = this
        }
    }
}

