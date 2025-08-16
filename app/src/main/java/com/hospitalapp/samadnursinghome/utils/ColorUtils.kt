package com.hospitalapp.samadnursinghome.utils

import android.content.Context
import android.graphics.Color
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import kotlin.math.roundToInt

/**
 * Utility class for color management and accessibility
 * Provides methods for color manipulation and contrast checking
 */
object ColorUtils {
    
    /**
     * Get color from resources with proper theme support
     */
    @ColorInt
    fun getColor(context: Context, @ColorRes colorRes: Int): Int {
        return ContextCompat.getColor(context, colorRes)
    }
    
    /**
     * Calculate contrast ratio between two colors
     * Returns value between 1 and 21
     */
    fun calculateContrastRatio(@ColorInt color1: Int, @ColorInt color2: Int): Double {
        val luminance1 = calculateLuminance(color1)
        val luminance2 = calculateLuminance(color2)
        
        val lighter = maxOf(luminance1, luminance2)
        val darker = minOf(luminance1, luminance2)
        
        return (lighter + 0.05) / (darker + 0.05)
    }
    
    /**
     * Calculate relative luminance of a color
     */
    private fun calculateLuminance(@ColorInt color: Int): Double {
        val red = Color.red(color) / 255.0
        val green = Color.green(color) / 255.0
        val blue = Color.blue(color) / 255.0
        
        val r = if (red <= 0.03928) red / 12.92 else Math.pow((red + 0.055) / 1.055, 2.4)
        val g = if (green <= 0.03928) green / 12.92 else Math.pow((green + 0.055) / 1.055, 2.4)
        val b = if (blue <= 0.03928) blue / 12.92 else Math.pow((blue + 0.055) / 1.055, 2.4)
        
        return 0.2126 * r + 0.7152 * g + 0.0722 * b
    }
    
    /**
     * Check if color combination meets WCAG AA standards (4.5:1 ratio)
     */
    fun meetsWCAGAA(@ColorInt foreground: Int, @ColorInt background: Int): Boolean {
        return calculateContrastRatio(foreground, background) >= 4.5
    }
    
    /**
     * Check if color combination meets WCAG AAA standards (7:1 ratio)
     */
    fun meetsWCAGAAA(@ColorInt foreground: Int, @ColorInt background: Int): Boolean {
        return calculateContrastRatio(foreground, background) >= 7.0
    }
    
    /**
     * Add alpha to color
     */
    @ColorInt
    fun addAlpha(@ColorInt color: Int, alpha: Float): Int {
        val alphaInt = (alpha * 255).roundToInt()
        return Color.argb(alphaInt, Color.red(color), Color.green(color), Color.blue(color))
    }
    
    /**
     * Darken color by percentage
     */
    @ColorInt
    fun darkenColor(@ColorInt color: Int, factor: Float): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] *= factor
        return Color.HSVToColor(hsv)
    }
    
    /**
     * Lighten color by percentage
     */
    @ColorInt
    fun lightenColor(@ColorInt color: Int, factor: Float): Int {
        val hsv = FloatArray(3)
        Color.colorToHSV(color, hsv)
        hsv[2] = 1f - factor * (1f - hsv[2])
        return Color.HSVToColor(hsv)
    }
    
    /**
     * Get appropriate text color (black or white) for given background
     */
    @ColorInt
    fun getContrastingTextColor(@ColorInt backgroundColor: Int): Int {
        val whiteContrast = calculateContrastRatio(Color.WHITE, backgroundColor)
        val blackContrast = calculateContrastRatio(Color.BLACK, backgroundColor)
        
        return if (whiteContrast > blackContrast) Color.WHITE else Color.BLACK
    }
}