package com.example.silentmoon.ui.topic

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ActivityTopicsBinding
import com.example.silentmoon.ui.time.TimeActivity


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

        val intent = Intent(this, TimeActivity::class.java)

        binding.topicsRecycler.apply {
            setHasFixedSize(true)
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            adapter = TopicCardsAdapter(context, intent).apply {
                submitList(viewModel.topics)
            }
        }
        val topicsTitle = getString(R.string.topics_title)
        val endOfBoldText = topicsTitle.indexOf("\n")
        if (endOfBoldText != -1) {
            val spannableString = SpannableString(topicsTitle).apply {
                setSpan(StyleSpan(Typeface.BOLD), 0, endOfBoldText, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            }
            binding.topicsTitle.text = spannableString
        }
    }
}

