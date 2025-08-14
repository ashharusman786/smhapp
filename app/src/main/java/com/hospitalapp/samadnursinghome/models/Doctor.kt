package com.hospitalapp.samadnursinghome.models

data class Doctor(
    val id: String,
    val name: String,
    val specialization: String,
    val isAvailable: Boolean,
    val imageUrl: String
)