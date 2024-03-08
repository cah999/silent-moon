package com.example.silentmoon.ui.sleep

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.silentmoon.R
import com.example.silentmoon.databinding.FragmentSleepBinding
import com.example.silentmoon.ui.play_option.PlayOptionActivity
import com.example.silentmoon.ui.sleep_music.SleepMusicActivity
import com.example.silentmoon.ui.utils.CategoriesAdapter
import com.example.silentmoon.ui.utils.CategoriesItemDecoration
import com.google.android.material.bottomnavigation.BottomNavigationView

class SleepFragment : Fragment() {

    private var _binding: FragmentSleepBinding? = null

    private val binding get() = _binding!!
    private var bottomNavigationView: BottomNavigationView? = null
    private val viewModel by lazy { ViewModelProvider(this)[SleepViewModel::class.java] }

    override fun onCreate(savedInstanceState: Bundle?) {
        val intent = Intent(activity, SleepActivity::class.java)
        startActivity(intent)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        _binding = FragmentSleepBinding.inflate(inflater, container, false)

        val root: View = binding.root
        bottomNavigationView = activity?.findViewById(R.id.nav_view)
        bottomNavigationView?.setBackgroundColor(
            ContextCompat.getColor(requireContext(), R.color.sleep_background)
        )
        setupCategories()

        val nextIntent = Intent(context, PlayOptionActivity::class.java)
        val cards: RecyclerView = binding.sleepMusicCards
        cards.setHasFixedSize(true)
        cards.apply {
            layoutManager = GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)
            adapter = SleepCardsAdapter(context, viewModel.getCards(nextIntent))
        }
        cards.addItemDecoration(
            SleepSpaceDecoration(
                resources.getDimension(R.dimen.margin_10dp).toInt()
            )
        )

        val musicIntent = Intent(context, SleepMusicActivity::class.java)

        binding.sleepBigCard.sleepBigCardBackground.setOnClickListener {
            startActivity(musicIntent)
        }

        binding.sleepBigCard.sleepButtonBtn.setOnClickListener {
            startActivity(musicIntent)
        }
        return root
    }

    private fun setupCategories() {
        val categories: RecyclerView = binding.sleepCategories
        categories.setHasFixedSize(true)
        categories.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = CategoriesAdapter(context.getString(R.string.sleep))
        }
        categories.addItemDecoration(
            CategoriesItemDecoration(
                resources.getDimension(
                    R.dimen.text_margin
                ).toInt()
            )
        )
    }

    override fun onDestroyView() {
        bottomNavigationView?.setBackgroundColor(
            ContextCompat.getColor(requireContext(), R.color.white)
        )
        super.onDestroyView()
        _binding = null
    }
}

