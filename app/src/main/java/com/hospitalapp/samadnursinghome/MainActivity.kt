package com.hospitalapp.samadnursinghome

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.hospitalapp.samadnursinghome.databinding.ActivityMainBinding
import com.hospitalapp.samadnursinghome.fragments.AppointmentsFragment
import com.hospitalapp.samadnursinghome.fragments.ChatFragment
import com.hospitalapp.samadnursinghome.fragments.HomeFragment
import com.hospitalapp.samadnursinghome.fragments.ProfileFragment
import com.hospitalapp.samadnursinghome.fragments.RecordsFragment
import com.hospitalapp.samadnursinghome.utils.ThemeManager

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var themeManager: ThemeManager

    override fun onCreate(savedInstanceState: Bundle?) {
        // Apply theme before super.onCreate()
        themeManager = ThemeManager(this)
        themeManager.applyTheme()
        
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBottomNavigation()
        
        // Load home fragment by default
        if (savedInstanceState == null) {
            loadFragment(HomeFragment())
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }
    }

    private fun setupBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(HomeFragment())
                    true
                }
                R.id.nav_appointments -> {
                    loadFragment(AppointmentsFragment())
                    true
                }
                R.id.nav_records -> {
                    loadFragment(RecordsFragment())
                    true
                }
                R.id.nav_chat -> {
                    loadFragment(ChatFragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
    
    /**
     * Get current theme manager instance
     */
    fun getThemeManager(): ThemeManager = themeManager
}