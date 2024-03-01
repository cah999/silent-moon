package com.example.silentmoon.ui.home

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.HomeMeditationCardBinding

class HomeMeditationAdapter(
    private val context: Context, private val data: List<HomeMeditation>, private val intent: Intent
) : RecyclerView.Adapter<HomeMeditationAdapter.MeditationViewHolder>() {

    inner class MeditationViewHolder(binding: HomeMeditationCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val background: ImageView = binding.meditationCardBackground
        val title: TextView = binding.meditationCardTitle
        val subtitle: TextView = binding.meditationCardSubtitle
        val subtitleMarker: TextView = binding.meditationCardSubtitle1
        val subtitleDuration: TextView = binding.meditationCardSubtitle2

        init {
            itemView.setOnClickListener { context.startActivity(intent) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeditationViewHolder {
        val binding =
            HomeMeditationCardBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MeditationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MeditationViewHolder, position: Int) {
        with(holder) {
            background.setImageResource(data[position].image)
            title.text = context.getString(data[position].title)
            subtitle.text = context.getString(R.string.meditation)
            subtitleMarker.text = context.getString(R.string.marker)
            subtitleDuration.text = context.getString(data[position].duration)
        }
    }

    override fun getItemCount() = data.size
}