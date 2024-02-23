package com.example.silentmoon.ui.sleep

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.silentmoon.PlayOptionActivity
import com.example.silentmoon.SleepActivity
import com.example.silentmoon.SleepMusicActivity
import com.example.silentmoon.databinding.FragmentSleepBinding

class SleepFragment : Fragment() {

    private var _binding: FragmentSleepBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val intent = Intent(activity, SleepActivity::class.java)
        startActivity(intent)
        val sleepViewModel =
            ViewModelProvider(this)[SleepViewModel::class.java]

        _binding = FragmentSleepBinding.inflate(inflater, container, false)

        val root: View = binding.root

        val textView: TextView = binding.textDashboard
        sleepViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        _binding!!.button13.setOnClickListener {
            startActivity(Intent(activity, PlayOptionActivity::class.java))
        }

        _binding!!.button14.setOnClickListener {
            startActivity(Intent(activity, SleepMusicActivity::class.java))
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}