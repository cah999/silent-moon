package com.example.silentmoon.ui.home

import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.CourseActivity
import com.example.silentmoon.R
import com.example.silentmoon.databinding.FragmentHomeBinding
import com.example.silentmoon.databinding.HomeBigCardBinding
import com.example.silentmoon.databinding.HomeCourseCardBinding
import com.example.silentmoon.databinding.HomeMeditationCardBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val courseIntent by lazy { Intent(context, CourseActivity::class.java) }
    private val meditationImages =
        arrayOf(R.drawable.focus_meditation, R.drawable.happiness_meditation)
    private val meditationTitles = arrayOf(R.string.focus, R.string.happiness)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)

        setupCourseRecyclerView()
        setupRecommendationsRecyclerView()

        return binding.root
    }

    private fun setupCourseRecyclerView() {
        val layoutManager = GridLayoutManager(context, 2)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (binding.homeRecycler.adapter?.getItemViewType(position)) {
                    CourseRVAdapter.TYPE_BIG_CARD -> 2
                    else -> 1
                }
            }
        }
        binding.homeRecycler.apply {
            setHasFixedSize(true)
            this.layoutManager = layoutManager
            adapter = CourseRVAdapter(context, courseIntent)
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_10dp)))
        }
    }

    private fun setupRecommendationsRecyclerView() {
        binding.homeMeditationRecommendations.apply {
            setHasFixedSize(true)
            layoutManager = GridLayoutManager(context, 1, GridLayoutManager.HORIZONTAL, false)
            adapter = MeditationRVAdapter(context, meditationImages, meditationTitles, courseIntent)
            addItemDecoration(SpaceItemDecoration(resources.getDimensionPixelSize(R.dimen.margin_20dp)))
        }
        PagerSnapHelper().attachToRecyclerView(binding.homeMeditationRecommendations)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

class CourseRVAdapter(
    private val context: Context,
    private val intent: Intent
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        private const val TYPE_COURSE = 0
        const val TYPE_BIG_CARD = 1
    }

    private val backgrounds = arrayOf(
        R.drawable.basics_course,
        R.drawable.relaxation_music,
        R.drawable.daily_thought_meditation
    )
    private val titles = arrayOf(R.string.basics, R.string.relaxation, R.string.daily_thought)
    private val subtitles = arrayOf(R.string.course, R.string.music, R.string.meditation)
    private val themes = arrayOf("light", "dark", "default")

    inner class CourseViewHolder(binding: HomeCourseCardBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val background: ImageView = binding.courseCardBackground
        val title: TextView = binding.cardTitle
        val subtitle: TextView = binding.cardSubtitle
        val duration: TextView = binding.cardDuration
        val button: Button = binding.startBtn

        init {
            itemView.setOnClickListener { context.startActivity(intent) }
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
                    imageView.setImageResource(backgrounds[position])
                    titleTextView.setText(titles[position])
                    subtitleTextView.setText(subtitles[position])
                }
            }

            else -> {
                val courseHolder = holder as CourseViewHolder
                with(courseHolder) {
                    background.setImageResource(backgrounds[position])
                    title.setText(titles[position])
                    subtitle.setText(subtitles[position])
                    duration.setText(R.string.duration)

                    val themeColor = when (themes[position]) {
                        "light" -> R.color.light_gray to R.color.light_gray
                        "dark" -> R.color.dark_gray to R.color.dark_gray
                        else -> R.color.light_gray to R.color.dark_gray
                    }

                    title.setTextColor(ContextCompat.getColor(context, themeColor.first))
                    subtitle.setTextColor(ContextCompat.getColor(context, themeColor.first))
                    duration.setTextColor(ContextCompat.getColor(context, themeColor.first))
                    button.setBackgroundColor(ContextCompat.getColor(context, themeColor.second))
                    if (themes[position] == "dark") {
                        button.setTextColor(ContextCompat.getColor(context, R.color.white))
                    } else {
                        button.setTextColor(ContextCompat.getColor(context, R.color.dark_gray))
                    }
                }
            }
        }
    }

    override fun getItemCount() = backgrounds.size
}

class MeditationRVAdapter(
    private val context: Context,
    private val backgrounds: Array<Int>,
    private val titles: Array<Int>,
    private val intent: Intent
) : RecyclerView.Adapter<MeditationRVAdapter.MeditationViewHolder>() {

    private var screenWidth = 0

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
        screenWidth = parent.resources.displayMetrics.widthPixels
        return MeditationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MeditationViewHolder, position: Int) {
        with(holder) {
            background.setImageResource(backgrounds[position])
            title.setText(titles[position])
            subtitle.setText(R.string.meditation)
            subtitleMarker.setText(R.string.marker)
            subtitleDuration.setText(R.string.duration)


            title.setTextColor(ContextCompat.getColor(context, R.color.dark_gray))
            subtitle.setTextColor(ContextCompat.getColor(context, R.color.gray))
            subtitleMarker.setTextColor(ContextCompat.getColor(context, R.color.gray))
            subtitleDuration.setTextColor(ContextCompat.getColor(context, R.color.gray))
        }
    }

    override fun getItemCount() = backgrounds.size
}

class SpaceItemDecoration(private val space: Int) : RecyclerView.ItemDecoration() {
    override fun getItemOffsets(
        outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)
        val itemCount = parent.adapter?.itemCount ?: 0

        if (position != RecyclerView.NO_POSITION && position != itemCount - 1) {
            if (position % 2 == 0) {
                outRect.right = space
            } else {
                outRect.left = space
            }
        }
    }
}
