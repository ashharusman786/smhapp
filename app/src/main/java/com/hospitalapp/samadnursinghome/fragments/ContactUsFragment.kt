package com.hospitalapp.samadnursinghome.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hospitalapp.samadnursinghome.databinding.FragmentContactUsBinding

class ContactUsFragment : Fragment() {
    
    private var _binding: FragmentContactUsBinding? = null
    private val binding get() = _binding!!
    
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContactUsBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // Setup contact information
    }
    
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}