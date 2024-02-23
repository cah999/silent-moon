package com.example.silentmoon.ui.meditate

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.silentmoon.CourseActivity
import com.example.silentmoon.ScrollingActivity
import com.example.silentmoon.databinding.FragmentMeditateBinding
import com.example.silentmoon.databinding.FragmentNotificationsBinding

class MeditateFragment : Fragment() {

    private var _binding: FragmentMeditateBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(MeditateViewModel::class.java)

        _binding = FragmentMeditateBinding.inflate(inflater, container, false)
        val root: View = binding.root

        _binding!!.button11.setOnClickListener {
            val intent = Intent(activity, ScrollingActivity::class.java)
            startActivity(intent)
        }

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}