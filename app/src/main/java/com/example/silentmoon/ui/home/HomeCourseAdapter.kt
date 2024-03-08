package com.example.silentmoon.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.HomeBigCardBinding
import com.example.silentmoon.databinding.HomeCourseCardBinding

class HomeCourseAdapter(
    private val context: Context,
    private val data: List<HomeCourse>,
    private val intent: Intent
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_COURSE = 0
        const val TYPE_BIG_CARD = 1
    }


    inner class CourseViewHolder(binding: HomeCourseCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val background: ImageView = binding.courseCardBackground
        val title: TextView = binding.cardTitle
        val subtitle: TextView = binding.cardSubtitle
        val duration: TextView = binding.cardDuration
        val button: Button = binding.startBtn

        init {
            itemView.setOnClickListener { context.startActivity(intent) }
            button.setOnClickListener { context.startActivity(intent) }
        }
    }

    inner class BigCardViewHolder(binding: HomeBigCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val imageView: ImageView = binding.bigCardBackground
        val titleTextView: TextView = binding.bigCardTitle
        val subtitleTextView: TextView = binding.bigCardSubtitle

        init {
            binding.bigCardBackground.setOnClickListener { context.startActivity(intent) }
            binding.playBtn.setOnClickListener { context.startActivity(intent) }
            binding.bigCardSubtitle1.setText(R.string.marker)
            binding.bigCardSubtitle2.setText(R.string.duration)
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == 2) TYPE_BIG_CARD else TYPE_COURSE
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_BIG_CARD -> {
                val bigBinding = HomeBigCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                BigCardViewHolder(bigBinding)
            }

            else -> {
                val binding =
                    HomeCourseCardBinding.inflate(
                        LayoutInflater.from(parent.context),
                        parent,
                        false
                    )
                CourseViewHolder(binding)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) {
            TYPE_BIG_CARD -> {
                val bigCardHolder = holder as BigCardViewHolder
                with(bigCardHolder) {
                    imageView.setImageResource(data[position].image)
                    titleTextView.setText(data[position].title)
                    subtitleTextView.setText(data[position].subtitle)
                }
            }

            else -> {
                val courseHolder = holder as CourseViewHolder
                with(courseHolder) {
                    background.setImageResource(data[position].image)
                    title.text = context.getString(data[position].title)
                    subtitle.text = context.getString(data[position].subtitle)
                    duration.text = context.getString(R.string.duration)

                    val themeColor = when (data[position].theme) {
                        R.string.light -> R.color.light_gray to R.color.dark_gray
                        R.string.dark -> R.color.dark_gray to R.color.white
                        else -> R.color.light_gray to R.color.dark_gray
                    }

                    title.setTextColor(ContextCompat.getColor(context, themeColor.first))
                    subtitle.setTextColor(ContextCompat.getColor(context, themeColor.first))
                    duration.setTextColor(ContextCompat.getColor(context, themeColor.first))
                    button.setBackgroundColor(ContextCompat.getColor(context, themeColor.first))
                    button.setTextColor(ContextCompat.getColor(context, themeColor.second))
                }
            }
        }
    }

    override fun getItemCount() = data.size
}