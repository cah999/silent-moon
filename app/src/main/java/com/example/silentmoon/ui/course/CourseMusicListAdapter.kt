package com.example.silentmoon.ui.course

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import android.widget.ToggleButton
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.CourseMusicItemBinding

class CourseMusicListAdapter(
    private val context: Context,
    private val courseItems: List<CourseItem>,
    private val nextIntent: Intent
) :
    RecyclerView.Adapter<CourseMusicListAdapter.ViewHolder>() {

    inner class ViewHolder(binding: CourseMusicItemBinding) : RecyclerView.ViewHolder(binding.root) {
        var playBtn: ToggleButton = binding.playMusicBtn
        var title: TextView = binding.musicTitle
        var duration: TextView = binding.musicDuration

        init {
            itemView.setOnClickListener {
                context.startActivity(nextIntent)
            }
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, position: Int): ViewHolder {
        val binding = CourseMusicItemBinding.inflate(LayoutInflater.from(group.context), group, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val courseItem = courseItems[position]
        holder.title.setText(courseItem.title)
        holder.duration.setText(courseItem.duration)

        val playButtonResource = when (courseItem.state) {
            "accent" -> R.drawable.music_play_accent
            else -> R.drawable.music_play_border
        }
        holder.playBtn.setBackgroundResource(playButtonResource)
        holder.playBtn.setOnClickListener {
            context.startActivity(nextIntent)
        }
    }

    override fun getItemCount() = courseItems.size
}