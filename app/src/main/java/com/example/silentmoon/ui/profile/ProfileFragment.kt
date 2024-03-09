package com.example.silentmoon.ui.profile

import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.silentmoon.R
import com.example.silentmoon.databinding.FragmentProfileBinding
import com.example.silentmoon.ui.main.MainActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val sharedPreferences =
            requireActivity().getSharedPreferences(getString(R.string.mysharedpref), MODE_PRIVATE)
        var name = sharedPreferences.getString(
            getString(R.string.name),
            getString(R.string.default_nickname)
        )
        if (name != null) {
            if (name.isEmpty()) {
                name = getString(R.string.default_nickname)
            }
        }
        binding.profileNickname.text = name

        binding.logoutBtn.setOnClickListener {
            val editor = sharedPreferences.edit()
            editor.clear()
            editor.apply()
            val intent = Intent(requireActivity(), MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
        }
        return binding.root
    }

}