package com.hospitalapp.samadnursinghome.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hospitalapp.samadnursinghome.databinding.ItemDoctorSelectionBinding
import com.hospitalapp.samadnursinghome.models.Doctor

class DoctorSelectionAdapter(
    private val doctors: List<Doctor>,
    private val onDoctorClick: (Doctor) -> Unit
) : RecyclerView.Adapter<DoctorSelectionAdapter.DoctorViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoctorViewHolder {
        val binding = ItemDoctorSelectionBinding.inflate(
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

    inner class DoctorViewHolder(private val binding: ItemDoctorSelectionBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(doctor: Doctor) {
            binding.apply {
                tvDoctorName.text = doctor.name
                tvSpecialization.text = doctor.specialization

                root.setOnClickListener {
                    onDoctorClick(doctor)
                }
            }
        }
    }
}