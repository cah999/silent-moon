package com.example.silentmoon.ui.topic

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.databinding.TopicsTopicBinding

class TopicCardsAdapter(
    private val context: Context,
    private val nextIntent: Intent
) :
    ListAdapter<Topic, TopicCardsAdapter.ViewHolder>(TopicDiffCallback()) {

    inner class ViewHolder(binding: TopicsTopicBinding) : RecyclerView.ViewHolder(binding.root) {
        var background = binding.topicBackground
        var title = binding.topicText

        init {
            itemView.setOnClickListener {
                context.startActivity(nextIntent)
            }
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, position: Int): ViewHolder {
        val binding = TopicsTopicBinding.inflate(LayoutInflater.from(group.context), group, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val topic = getItem(position)
        holder.background.setImageResource(topic.background)
        holder.title.setText(topic.text)
        holder.title.setTextColor(ContextCompat.getColor(context, topic.textColor))
    }
}