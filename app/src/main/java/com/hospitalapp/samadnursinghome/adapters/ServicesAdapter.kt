package com.hospitalapp.samadnursinghome.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hospitalapp.samadnursinghome.databinding.ItemServiceBinding
import com.hospitalapp.samadnursinghome.models.HospitalService

/**
 * Adapter for displaying hospital services in a horizontal RecyclerView
 * Uses proper color theming and accessibility features
 */
class ServicesAdapter(
    private val services: List<HospitalService>,
    private val onServiceClick: (HospitalService) -> Unit
) : RecyclerView.Adapter<ServicesAdapter.ServiceViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceViewHolder {
        val binding = ItemServiceBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ServiceViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServiceViewHolder, position: Int) {
        holder.bind(services[position])
    }

    override fun getItemCount(): Int = services.size

    inner class ServiceViewHolder(private val binding: ItemServiceBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(service: HospitalService) {
            binding.apply {
                tvServiceName.text = service.name
                tvServiceDescription.text = service.description
                ivServiceIcon.setImageResource(service.iconRes)

                root.setOnClickListener {
                    onServiceClick(service)
                }
                
                // Set content description for accessibility
                root.contentDescription = "${service.name}: ${service.description}"
            }
        }
    }
}