package com.example.silentmoon.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.silentmoon.R
import com.example.silentmoon.databinding.ActivityHomeBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navView.itemIconTintList = null

        val navView: BottomNavigationView = binding.navView
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_home) as NavHostFragment
        val navController = navHostFragment.navController
        navView.setupWithNavController(navController)


        val lastMenuItem = navView.menu.getItem(navView.menu.size() - 1)

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
        lastMenuItem.title = name
    }
}