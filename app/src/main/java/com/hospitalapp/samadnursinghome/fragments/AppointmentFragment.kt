package com.hospitalapp.samadnursinghome.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hospitalapp.samadnursinghome.databinding.FragmentAppointmentBinding

class AppointmentFragment : Fragment() {
    
    private var _binding: FragmentAppointmentBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppointmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup appointment booking
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}