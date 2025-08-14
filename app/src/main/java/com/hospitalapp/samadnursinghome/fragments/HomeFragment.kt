package com.hospitalapp.samadnursinghome.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import com.hospitalapp.samadnursinghome.R
import com.hospitalapp.samadnursinghome.databinding.FragmentHomeBinding
import com.hospitalapp.samadnursinghome.utils.SessionManager

class HomeFragment : Fragment() {
    
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var sessionManager: SessionManager
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        sessionManager = SessionManager(requireContext())
        
        setupUI()
        setupClickListeners()
        startAnimations()
    }
    
    private fun setupUI() {
        // Set welcome message with user name
        val userName = sessionManager.getUserName() ?: "User"
        binding.tvWelcomeUser.text = "Welcome back, $userName!"
    }
    
    private fun setupClickListeners() {
        binding.cardBookAppointment.setOnClickListener {
            // TODO: Navigate to appointment booking
            // For now, show a placeholder
        }
        
        binding.cardViewRecords.setOnClickListener {
            // TODO: Navigate to patient records
        }
        
        binding.cardEmergency.setOnClickListener {
            // Emergency call functionality
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+911234567890") // Replace with actual emergency number
            startActivity(intent)
        }
        
        binding.cardDoctorSchedule.setOnClickListener {
            // TODO: Navigate to doctor schedule
        }
    }
    
    private fun startAnimations() {
        val slideInLeft = AnimationUtils.loadAnimation(context, android.R.anim.slide_in_left)
        val fadeIn = AnimationUtils.loadAnimation(context, android.R.anim.fade_in)
        
        binding.welcomeCard.startAnimation(fadeIn)
        binding.quickActionsCard.startAnimation(slideInLeft)
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}