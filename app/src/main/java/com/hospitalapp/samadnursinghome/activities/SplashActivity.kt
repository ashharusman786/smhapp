package com.hospitalapp.samadnursinghome.activities

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import com.hospitalapp.samadnursinghome.MainActivity
import com.hospitalapp.samadnursinghome.R
import com.hospitalapp.samadnursinghome.databinding.ActivitySplashBinding
import com.hospitalapp.samadnursinghome.utils.SessionManager

class SplashActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivitySplashBinding
    private lateinit var sessionManager: SessionManager
    
    companion object {
        private const val SPLASH_DELAY = 3000L // 3 seconds
    }
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        sessionManager = SessionManager(this)
        
        setupUI()
        startAnimations()
        navigateAfterDelay()
    }
    
    private fun setupUI() {
        // Hide system UI for immersive experience
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_FULLSCREEN
        )
        
        // Set status bar to transparent
        window.statusBarColor = android.graphics.Color.TRANSPARENT
    }
    
    private fun startAnimations() {
        // Logo animation - scale up with fade in
        val logoAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_logo_animation)
        binding.ivLogo.startAnimation(logoAnimation)
        
        // App name animation - slide up with fade in
        val titleAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_title_animation)
        binding.tvAppName.startAnimation(titleAnimation)
        
        // Tagline animation - fade in with delay
        val taglineAnimation = AnimationUtils.loadAnimation(this, R.anim.splash_tagline_animation)
        binding.tvTagline.startAnimation(taglineAnimation)
        
        // Loading animation - fade in with delay
        Handler(Looper.getMainLooper()).postDelayed({
            val loadingAnimation = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
            binding.progressBar.visibility = View.VISIBLE
            binding.progressBar.startAnimation(loadingAnimation)
            binding.tvLoading.visibility = View.VISIBLE
            binding.tvLoading.startAnimation(loadingAnimation)
        }, 1500)
    }
    
    private fun navigateAfterDelay() {
        Handler(Looper.getMainLooper()).postDelayed({
            navigateToNextScreen()
        }, SPLASH_DELAY)
    }
    
    private fun navigateToNextScreen() {
        val intent = if (sessionManager.isLoggedIn() && !sessionManager.isSessionExpired()) {
            // User is logged in, go to main activity
            Intent(this, MainActivity::class.java)
        } else {
            // User not logged in, go to login activity
            Intent(this, LoginActivity::class.java)
        }
        
        startActivity(intent)
        finish()
        
        // Add smooth transition animation
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
    
    override fun onBackPressed() {
        // Disable back button on splash screen
        // Do nothing
    }
}