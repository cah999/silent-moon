package com.example.silentmoon.ui.sleep

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.HomeMeditationCardBinding

class SleepCardsAdapter(
    private val context: Context,
    private val cards: List<SleepCard>
) : RecyclerView.Adapter<SleepCardsAdapter.ViewHolder>() {

    inner class ViewHolder(binding: HomeMeditationCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var background: ImageView = binding.meditationCardBackground
        var title: TextView = binding.meditationCardTitle
        var duration: TextView = binding.meditationCardSubtitle
        var subtitle: TextView = binding.meditationCardSubtitle2

        init {
            binding.meditationCardSubtitle1.text = context.getString(R.string.marker)
            binding.meditationCardSubtitle1.setTextColor(
                ContextCompat.getColor(context, R.color.sleep_grey)
            )
            itemView.setOnClickListener {
                context.startActivity(cards[adapterPosition].intent)
            }
        }
    }

    override fun onCreateViewHolder(group: ViewGroup, position: Int): ViewHolder {
        val binding =
            HomeMeditationCardBinding.inflate(LayoutInflater.from(group.context), group, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val card = cards[position]
        holder.background.setImageResource(card.background)
        holder.title.setText(card.title)
        holder.subtitle.setText(R.string.sleep_music)
        holder.title.setTextColor(ContextCompat.getColor(context, R.color.sleep_text_color))
        holder.subtitle.setTextColor(ContextCompat.getColor(context, R.color.sleep_grey))
        holder.duration.setTextColor(ContextCompat.getColor(context, R.color.sleep_grey))
        holder.duration.setText(R.string.sleep_duration)

        val layoutParams = holder.itemView.layoutParams
        layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
        layoutParams.width = ViewGroup.LayoutParams.WRAP_CONTENT
        holder.itemView.layoutParams = layoutParams
    }

    override fun getItemCount() = cards.size
}