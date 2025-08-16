package com.hospitalapp.samadnursinghome.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatDelegate

/**
 * Manages theme preferences and application of themes
 * Handles light/dark mode switching with proper color management
 */
class ThemeManager(context: Context) {
    
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        THEME_PREFERENCES, Context.MODE_PRIVATE
    )
    
    companion object {
        private const val THEME_PREFERENCES = "theme_preferences"
        private const val KEY_THEME_MODE = "theme_mode"
        
        const val THEME_LIGHT = 0
        const val THEME_DARK = 1
        const val THEME_SYSTEM = 2
    }
    
    /**
     * Get current theme mode
     */
    fun getCurrentTheme(): Int {
        return sharedPreferences.getInt(KEY_THEME_MODE, THEME_SYSTEM)
    }
    
    /**
     * Set theme mode and apply it
     */
    fun setTheme(themeMode: Int) {
        sharedPreferences.edit().putInt(KEY_THEME_MODE, themeMode).apply()
        applyTheme(themeMode)
    }
    
    /**
     * Apply theme based on mode
     */
    fun applyTheme(themeMode: Int = getCurrentTheme()) {
        when (themeMode) {
            THEME_LIGHT -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            THEME_DARK -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            THEME_SYSTEM -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
        }
    }
    
    /**
     * Check if current theme is dark
     */
    fun isDarkTheme(context: Context): Boolean {
        val currentNightMode = context.resources.configuration.uiMode and 
                android.content.res.Configuration.UI_MODE_NIGHT_MASK
        return currentNightMode == android.content.res.Configuration.UI_MODE_NIGHT_YES
    }
}