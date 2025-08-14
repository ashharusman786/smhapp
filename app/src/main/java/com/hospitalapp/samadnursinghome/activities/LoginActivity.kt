package com.hospitalapp.samadnursinghome.activities

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.hospitalapp.samadnursinghome.MainActivity
import com.hospitalapp.samadnursinghome.R
import com.hospitalapp.samadnursinghome.databinding.ActivityLoginBinding
import com.hospitalapp.samadnursinghome.utils.SessionManager

class LoginActivity : AppCompatActivity() {
    
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sessionManager: SessionManager
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        sessionManager = SessionManager(this)
        
        // Check if user is already logged in
        if (sessionManager.isLoggedIn() && !sessionManager.isSessionExpired()) {
            navigateToMain()
            return
        }
        
        setupUI()
        setupClickListeners()
        startAnimations()
    }
    
    private fun setupUI() {
        // Hide system UI for immersive experience
        window.decorView.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
        )
    }
    
    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            performLogin()
        }
        
        binding.tvForgotPassword.setOnClickListener {
            // TODO: Implement forgot password functionality
            Toast.makeText(this, "Forgot password feature coming soon!", Toast.LENGTH_SHORT).show()
        }
        
        binding.tvCreateAccount.setOnClickListener {
            // TODO: Implement registration functionality
            Toast.makeText(this, "Registration feature coming soon!", Toast.LENGTH_SHORT).show()
        }
    }
    
    private fun startAnimations() {
        // Fade in animation for the main container
        val fadeIn = AnimationUtils.loadAnimation(this, android.R.anim.fade_in)
        binding.loginContainer.startAnimation(fadeIn)
        
        // Slide up animation for the login card
        val slideUp = AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left)
        binding.loginCard.startAnimation(slideUp)
    }
    
    private fun performLogin() {
        val email = binding.etEmail.text.toString().trim()
        val password = binding.etPassword.text.toString().trim()
        
        // Clear previous errors
        binding.tilEmail.error = null
        binding.tilPassword.error = null
        
        // Validate inputs
        if (!validateInputs(email, password)) {
            return
        }
        
        // Show loading state
        showLoading(true)
        
        // Simulate login process (replace with actual authentication)
        simulateLogin(email, password)
    }
    
    private fun validateInputs(email: String, password: String): Boolean {
        var isValid = true
        
        // Email validation
        when {
            email.isEmpty() -> {
                binding.tilEmail.error = getString(R.string.error_empty_email)
                isValid = false
            }
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {
                binding.tilEmail.error = getString(R.string.error_invalid_email)
                isValid = false
            }
        }
        
        // Password validation
        when {
            password.isEmpty() -> {
                binding.tilPassword.error = getString(R.string.error_empty_password)
                isValid = false
            }
            password.length < 6 -> {
                binding.tilPassword.error = getString(R.string.error_short_password)
                isValid = false
            }
        }
        
        return isValid
    }
    
    private fun simulateLogin(email: String, password: String) {
        // Simulate network delay
        binding.btnLogin.postDelayed({
            // For demo purposes, accept any valid email/password combination
            // In production, implement actual authentication logic here
            
            if (isValidCredentials(email, password)) {
                // Save session
                sessionManager.createSession(
                    email = email,
                    name = "User", // Replace with actual user name from API
                    userId = "user_123" // Replace with actual user ID from API
                )
                
                showLoading(false)
                navigateToMain()
            } else {
                showLoading(false)
                Toast.makeText(this, getString(R.string.error_login_failed), Toast.LENGTH_LONG).show()
            }
        }, 2000) // 2 second delay to simulate network call
    }
    
    private fun isValidCredentials(email: String, password: String): Boolean {
        // Demo credentials - replace with actual authentication
        return email.isNotEmpty() && password.length >= 6
    }
    
    private fun showLoading(show: Boolean) {
        if (show) {
            binding.btnLogin.text = getString(R.string.loading)
            binding.btnLogin.isEnabled = false
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.btnLogin.text = getString(R.string.login_button)
            binding.btnLogin.isEnabled = true
            binding.progressBar.visibility = View.GONE
        }
    }
    
    private fun navigateToMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
        finish()
        
        // Add transition animation
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}