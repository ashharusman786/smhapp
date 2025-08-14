package com.hospitalapp.samadnursinghome.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hospitalapp.samadnursinghome.R
import com.hospitalapp.samadnursinghome.activities.AppointmentBookingActivity
import com.hospitalapp.samadnursinghome.adapters.DoctorsAdapter
import com.hospitalapp.samadnursinghome.databinding.FragmentHomeBinding
import com.hospitalapp.samadnursinghome.models.Doctor

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var doctorsAdapter: DoctorsAdapter

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
        
        setupClickListeners()
        setupDoctorsList()
    }

    private fun setupClickListeners() {
        binding.btnGetStarted.setOnClickListener {
            startActivity(Intent(requireContext(), AppointmentBookingActivity::class.java))
        }
        
        binding.btnEmergencyCall.setOnClickListener {
            val intent = Intent(Intent.ACTION_DIAL)
            intent.data = Uri.parse("tel:+917860120688")
            startActivity(intent)
        }
        
        binding.cardBookAppointment.setOnClickListener {
            startActivity(Intent(requireContext(), AppointmentBookingActivity::class.java))
        }
        
        binding.cardVideoConsultation.setOnClickListener {
            // TODO: Implement video consultation
        }
        
        binding.btnDirections.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("geo:0,0?q=Samad Nursing Home, Mahipalpur, New Delhi")
            startActivity(intent)
        }
        
        binding.btnWhatsapp.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://wa.me/917860120688")
            startActivity(intent)
        }
    }

    private fun setupDoctorsList() {
        val doctors = listOf(
            Doctor(
                id = "1",
                name = "Dr. Badshahshah Malik",
                specialization = "Heart Specialist",
                isAvailable = true,
                imageUrl = ""
            ),
            Doctor(
                id = "2",
                name = "Dr. Zeeshan Ahmed",
                specialization = "General Physician and Child Specialist",
                isAvailable = true,
                imageUrl = ""
            )
        )
        
        doctorsAdapter = DoctorsAdapter(doctors) { doctor ->
            // Handle doctor click
        }
        
        binding.recyclerViewDoctors.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = doctorsAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}