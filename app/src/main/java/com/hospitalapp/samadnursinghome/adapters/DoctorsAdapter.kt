package com.hospitalapp.samadnursinghome.adapters

import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hospitalapp.samadnursinghome.R
import com.hospitalapp.samadnursinghome.databinding.ItemDoctorBinding
import com.hospitalapp.samadnursinghome.models.Doctor

class DoctorsAdapter(
    private val doctors: List<Doctor>,
    private val onDoctorClick: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorsAdapter.DoctorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val binding = ItemDoctorBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return DoctorViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.bind(doctors[position])
    }

    override fun getItemCount(): Int = doctors.size

    inner class DoctorViewHolder(private val binding: ItemDoctorBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(doctor: Doctor) {
            binding.apply {
                tvDoctorName.text = doctor.name
                tvSpecialization.text = doctor.specialization
                
                // Set availability status
                if (doctor.isAvailable) {
                    tvAvailability.text = root.context.getString(R.string.available)
                    tvAvailability.setBackgroundResource(R.drawable.badge_background_green)
                } else {
                    tvAvailability.text = "Busy"
                    tvAvailability.setBackgroundResource(R.drawable.badge_background_red)
                }

                btnMessage.setOnClickListener {
                    // TODO: Implement messaging
                }

                btnCall.setOnClickListener {
                    val intent = Intent(Intent.ACTION_DIAL)
                    intent.data = Uri.parse("tel:+917860120688")
                    root.context.startActivity(intent)
                }

                root.setOnClickListener {
                    onDoctorClick(doctor)
                }
            }
        }
    }
}