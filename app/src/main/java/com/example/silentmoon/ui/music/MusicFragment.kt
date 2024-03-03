package com.example.silentmoon.ui.music

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.silentmoon.databinding.FragmentMusicBinding

class MusicFragment : Fragment() {
    private var _binding: FragmentMusicBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
//        val musicViewModel =
//            ViewModelProvider(this)[MusicViewModel::class.java]

        // Inflate the layout for this fragment
        _binding = FragmentMusicBinding.inflate(inflater, container, false)
        val root: View = binding.root
        startActivity(Intent(activity, MusicActivity::class.java))

        return root
    }

}