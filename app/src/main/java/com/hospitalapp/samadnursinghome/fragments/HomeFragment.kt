package com.hospitalapp.samadnursinghome.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hospitalapp.samadnursinghome.R
import com.hospitalapp.samadnursinghome.activities.AppointmentBookingActivity
import com.hospitalapp.samadnursinghome.adapters.DoctorsAdapter
import com.hospitalapp.samadnursinghome.adapters.ServicesAdapter
import com.hospitalapp.samadnursinghome.databinding.FragmentHomeBinding
import com.hospitalapp.samadnursinghome.models.Doctor
import com.hospitalapp.samadnursinghome.models.HospitalService
import com.hospitalapp.samadnursinghome.utils.Constants
import com.hospitalapp.samadnursinghome.utils.PermissionHelper

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var doctorsAdapter: DoctorsAdapter
    private lateinit var servicesAdapter: ServicesAdapter
    
    companion object {
        private const val CALL_PERMISSION_REQUEST_CODE = 100
    }

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
        setupServicesList()
    }

    private fun setupClickListeners() {
        // Emergency call button
        binding.btnEmergencyCall.setOnClickListener {
            makeEmergencyCall()
        }
        
        // Emergency FAB
        binding.fabEmergency.setOnClickListener {
            makeEmergencyCall()
        }
        
        // Book appointment
        binding.cardBookAppointment.setOnClickListener {
            startActivity(Intent(requireContext(), AppointmentBookingActivity::class.java))
        }
        
        // Video consultation
        binding.cardVideoConsultation.setOnClickListener {
            // TODO: Implement video consultation
            Toast.makeText(requireContext(), getString(R.string.feature_coming_soon), Toast.LENGTH_SHORT).show()
        }
        
        // Directions
        binding.btnDirections.setOnClickListener {
            openDirections()
        }
        
        // WhatsApp
        binding.btnWhatsapp.setOnClickListener {
            openWhatsApp()
        }
        
        // View all doctors
        binding.tvViewAllDoctors.setOnClickListener {
            // TODO: Navigate to doctors list
            Toast.makeText(requireContext(), getString(R.string.feature_coming_soon), Toast.LENGTH_SHORT).show()
        }
        
        // View all services
        binding.tvViewAllServices.setOnClickListener {
            // TODO: Navigate to services list
            Toast.makeText(requireContext(), getString(R.string.feature_coming_soon), Toast.LENGTH_SHORT).show()
        }
        
        // Notifications
        binding.ivNotifications.setOnClickListener {
            // TODO: Navigate to notifications
            Toast.makeText(requireContext(), getString(R.string.feature_coming_soon), Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupDoctorsList() {
        val doctors = listOf(
            Doctor(
                id = "1",
                name = getString(R.string.dr_badshah_malik),
                specialization = getString(R.string.heart_specialist),
                isAvailable = true,
                imageUrl = ""
            ),
            Doctor(
                id = "2",
                name = getString(R.string.dr_zeeshan_ahmed),
                specialization = getString(R.string.general_physician_child),
                isAvailable = true,
                imageUrl = ""
            )
        )
        
        doctorsAdapter = DoctorsAdapter(doctors) { doctor ->
            // Handle doctor click - navigate to doctor details or book appointment
            val intent = Intent(requireContext(), AppointmentBookingActivity::class.java)
            intent.putExtra("selected_doctor_id", doctor.id)
            startActivity(intent)
        }
        
        binding.recyclerViewDoctors.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = doctorsAdapter
        }
    }

    private fun setupServicesList() {
        val services = listOf(
            HospitalService(
                id = "1",
                name = getString(R.string.emergency_care),
                description = getString(R.string.emergency_care_desc),
                iconRes = R.drawable.ic_emergency,
                category = "Emergency"
            ),
            HospitalService(
                id = "2",
                name = getString(R.string.cardiology),
                description = getString(R.string.cardiology_desc),
                iconRes = R.drawable.ic_heart,
                category = "Specialty"
            ),
            HospitalService(
                id = "3",
                name = getString(R.string.pediatrics),
                description = getString(R.string.pediatrics_desc),
                iconRes = R.drawable.ic_child,
                category = "Specialty"
            ),
            HospitalService(
                id = "4",
                name = getString(R.string.laboratory),
                description = getString(R.string.laboratory_desc),
                iconRes = R.drawable.ic_lab,
                category = "Diagnostic"
            )
        )
        
        servicesAdapter = ServicesAdapter(services) { service ->
            // Handle service click
            Toast.makeText(requireContext(), "Selected: ${service.name}", Toast.LENGTH_SHORT).show()
        }
        
        binding.recyclerViewServices.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = servicesAdapter
        }
    }

    private fun makeEmergencyCall() {
        if (PermissionHelper.hasCallPermission(requireContext())) {
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:${Constants.EMERGENCY_NUMBER}")
            try {
                startActivity(intent)
            } catch (e: Exception) {
                // Fallback to dialer
                val dialIntent = Intent(Intent.ACTION_DIAL)
                dialIntent.data = Uri.parse("tel:${Constants.EMERGENCY_NUMBER}")
                startActivity(dialIntent)
            }
        } else {
            requestCallPermission()
        }
    }

    private fun requestCallPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(),
                Manifest.permission.CALL_PHONE
            )
        ) {
            // Show explanation dialog
            androidx.appcompat.app.AlertDialog.Builder(requireContext())
                .setTitle(getString(R.string.permission_required))
                .setMessage(getString(R.string.call_permission_explanation))
                .setPositiveButton(getString(R.string.grant_permission)) { _, _ ->
                    ActivityCompat.requestPermissions(
                        requireActivity(),
                        arrayOf(Manifest.permission.CALL_PHONE),
                        CALL_PERMISSION_REQUEST_CODE
                    )
                }
                .setNegativeButton(getString(R.string.cancel)) { dialog, _ ->
                    dialog.dismiss()
                    // Fallback to dialer
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:${Constants.EMERGENCY_NUMBER}")
                    startActivity(intent)
                }
                .show()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                arrayOf(Manifest.permission.CALL_PHONE),
                CALL_PERMISSION_REQUEST_CODE
            )
        }
    }

    private fun openDirections() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("geo:0,0?q=${Constants.HOSPITAL_ADDRESS}")
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(requireContext(), getString(R.string.no_maps_app), Toast.LENGTH_SHORT).show()
        }
    }

    private fun openWhatsApp() {
        try {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://wa.me/${Constants.WHATSAPP_NUMBER}")
            startActivity(intent)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), getString(R.string.whatsapp_not_installed), Toast.LENGTH_SHORT).show()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        
        when (requestCode) {
            CALL_PERMISSION_REQUEST_CODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    makeEmergencyCall()
                } else {
                    // Permission denied, fallback to dialer
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:${Constants.EMERGENCY_NUMBER}")
                    startActivity(intent)
                    
                    Toast.makeText(
                        requireContext(),
                        getString(R.string.call_permission_denied),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}