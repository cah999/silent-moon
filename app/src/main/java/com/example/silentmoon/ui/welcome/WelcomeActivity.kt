package com.example.silentmoon.ui.welcome

import android.content.Intent
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import androidx.appcompat.app.AppCompatActivity
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ActivityWelcomeBinding
import com.example.silentmoon.ui.topic.TopicsActivity

class WelcomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(getString(R.string.mysharedpref), MODE_PRIVATE)
        var name = sharedPreferences.getString(
            getString(R.string.name),
            getString(R.string.default_nickname)
        )
        if (name != null) {
            if (name.isEmpty()) {
                name = getString(R.string.default_nickname)
            }
        }
        val welcomeText = binding.welcomeTitle.text.toString().format(name)
        val endOfBoldText = welcomeText.indexOf("\n")
        if (endOfBoldText != -1) {
            val spannableString = SpannableString(welcomeText).apply {
                setSpan(StyleSpan(Typeface.BOLD), 0, endOfBoldText, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            }
            binding.welcomeTitle.text = spannableString
        }
        binding.getStartedBtn.setOnClickListener {
            val intent = Intent(this, TopicsActivity::class.java)
            startActivity(intent)
        }
    }
}