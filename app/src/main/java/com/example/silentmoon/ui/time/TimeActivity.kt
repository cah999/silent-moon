package com.example.silentmoon.ui.time

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.silentmoon.databinding.ActivityTimeBinding
import com.example.silentmoon.ui.home.HomeActivity

class TimeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTimeBinding
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, HomeActivity::class.java)
//        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        binding.saveBtn.setOnClickListener {
            startActivity(intent)
//            Log.d("SELECTED TIME", binding.timePicker.getSelectedTime().toString())
//            Log.d(
//                "SELECTED DAYS",
//                binding.dayCircles.children.toList().filter { (it as Chip).isChecked }
//                    .joinToString(", ") { (it as Chip).text }
//            )
        }

        binding.noThanksText.setOnClickListener {
            startActivity(intent)
        }

    }
}