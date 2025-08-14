package com.hospitalapp.samadnursinghome.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.hospitalapp.samadnursinghome.R
import com.hospitalapp.samadnursinghome.adapters.DoctorSelectionAdapter
import com.hospitalapp.samadnursinghome.adapters.TimeSlotAdapter
import com.hospitalapp.samadnursinghome.databinding.ActivityAppointmentBookingBinding
import com.hospitalapp.samadnursinghome.models.Doctor
import com.hospitalapp.samadnursinghome.models.TimeSlot

class AppointmentBookingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppointmentBookingBinding
    private lateinit var doctorAdapter: DoctorSelectionAdapter
    private lateinit var timeSlotAdapter: TimeSlotAdapter
    private var selectedConsultationType = "in_person"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupToolbar()
        setupConsultationTypes()
        setupDoctorsList()
        setupTimeSlots()
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun setupConsultationTypes() {
        binding.cardInPerson.setOnClickListener {
            selectConsultationType("in_person")
        }
        
        binding.cardVideoCall.setOnClickListener {
            selectConsultationType("video_call")
        }
        
        // Default selection
        selectConsultationType("in_person")
    }

    private fun selectConsultationType(type: String) {
        selectedConsultationType = type
        
        // Reset all cards
        binding.cardInPerson.setCardBackgroundColor(getColor(R.color.card_dark))
        binding.cardVideoCall.setCardBackgroundColor(getColor(R.color.card_dark))
        
        // Highlight selected card
        when (type) {
            "in_person" -> binding.cardInPerson.setCardBackgroundColor(getColor(R.color.accent_blue))
            "video_call" -> binding.cardVideoCall.setCardBackgroundColor(getColor(R.color.accent_blue))
        }
    }

    private fun setupDoctorsList() {
        val doctors = listOf(
            Doctor("1", "Dr. Badshahshah Malik", "Heart Specialist", true, ""),
            Doctor("2", "Dr. Zeeshan Ahmed", "General Physician and Child Specialist", true, "")
        )
        
        doctorAdapter = DoctorSelectionAdapter(doctors) { doctor ->
            // Handle doctor selection
        }
        
        binding.recyclerViewDoctors.apply {
            layoutManager = LinearLayoutManager(this@AppointmentBookingActivity)
            adapter = doctorAdapter
        }
    }

    private fun setupTimeSlots() {
        val timeSlots = listOf(
            TimeSlot("9:00", true),
            TimeSlot("10:00", false),
            TimeSlot("11:00", true),
            TimeSlot("2:00", true)
        )
        
        timeSlotAdapter = TimeSlotAdapter(timeSlots) { timeSlot ->
            // Handle time slot selection
        }
        
        binding.recyclerViewTimeSlots.apply {
            layoutManager = LinearLayoutManager(this@AppointmentBookingActivity, LinearLayoutManager.HORIZONTAL, false)
            adapter = timeSlotAdapter
        }
    }
}