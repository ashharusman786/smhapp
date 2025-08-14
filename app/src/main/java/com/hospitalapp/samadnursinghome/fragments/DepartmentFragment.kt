package com.hospitalapp.samadnursinghome.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hospitalapp.samadnursinghome.databinding.FragmentDepartmentBinding

class DepartmentFragment : Fragment() {
    
    private var _binding: FragmentDepartmentBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDepartmentBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup departments content
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}