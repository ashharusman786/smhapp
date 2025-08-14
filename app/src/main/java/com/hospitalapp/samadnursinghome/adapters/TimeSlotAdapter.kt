package com.hospitalapp.samadnursinghome.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hospitalapp.samadnursinghome.R
import com.hospitalapp.samadnursinghome.databinding.ItemTimeSlotBinding
import com.hospitalapp.samadnursinghome.models.TimeSlot

class TimeSlotAdapter(
    private val timeSlots: List<TimeSlot>,
    private val onTimeSlotClick: (TimeSlot) -> Unit
) : RecyclerView.Adapter<TimeSlotAdapter.TimeSlotViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TimeSlotViewHolder {
        val binding = ItemTimeSlotBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TimeSlotViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TimeSlotViewHolder, position: Int) {
        holder.bind(timeSlots[position])
    }

    override fun getItemCount(): Int = timeSlots.size

    inner class TimeSlotViewHolder(private val binding: ItemTimeSlotBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(timeSlot: TimeSlot) {
            binding.apply {
                tvTime.text = timeSlot.time
                
                if (timeSlot.isAvailable) {
                    root.setCardBackgroundColor(root.context.getColor(R.color.card_dark))
                    tvTime.setTextColor(root.context.getColor(R.color.text_primary))
                    root.isClickable = true
                } else {
                    root.setCardBackgroundColor(root.context.getColor(R.color.surface_dark))
                    tvTime.setTextColor(root.context.getColor(R.color.text_tertiary))
                    root.isClickable = false
                }

                root.setOnClickListener {
                    if (timeSlot.isAvailable) {
                        onTimeSlotClick(timeSlot)
                    }
                }
            }
        }
    }
}