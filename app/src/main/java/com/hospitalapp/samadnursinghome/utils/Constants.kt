package com.hospitalapp.samadnursinghome.utils

/**
 * Application constants
 * Centralized location for all app constants to ensure consistency
 */
object Constants {
    
    // Contact Information
    const val EMERGENCY_NUMBER = "+917860120688"
    const val WHATSAPP_NUMBER = "917860120688"
    const val HOSPITAL_EMAIL = "info@samadnursinghome.com"
    
    // Hospital Address
    const val HOSPITAL_ADDRESS = "Samad Nursing Home, Rangpuri Road, Mahipalpur, New Delhi, District Azamgarh, Uttar Pradesh, PIN: 276001"
    
    // Firebase Database Paths
    const val FIREBASE_DOCTORS = "doctors"
    const val FIREBASE_APPOINTMENTS = "appointments"
    const val FIREBASE_SERVICES = "services"
    const val FIREBASE_USERS = "users"
    
    // Appointment Status
    const val APPOINTMENT_STATUS_SCHEDULED = "scheduled"
    const val APPOINTMENT_STATUS_CONFIRMED = "confirmed"
    const val APPOINTMENT_STATUS_COMPLETED = "completed"
    const val APPOINTMENT_STATUS_CANCELLED = "cancelled"
    
    // Consultation Types
    const val CONSULTATION_IN_PERSON = "in_person"
    const val CONSULTATION_VIDEO_CALL = "video_call"
    
    // Hospital Timings
    const val HOSPITAL_OPEN_TIME = "08:00"
    const val HOSPITAL_CLOSE_TIME_WEEKDAY = "22:00"
    const val HOSPITAL_CLOSE_TIME_SUNDAY = "14:00"
    
    // Notification Channels
    const val NOTIFICATION_CHANNEL_APPOINTMENTS = "appointments"
    const val NOTIFICATION_CHANNEL_EMERGENCY = "emergency"
    const val NOTIFICATION_CHANNEL_GENERAL = "general"
    
    // SharedPreferences Keys
    const val PREF_THEME = "theme_preference"
    const val PREF_LANGUAGE = "language_preference"
    const val PREF_NOTIFICATIONS = "notifications_enabled"
    
    // Request Codes
    const val REQUEST_CODE_CALL_PERMISSION = 100
    const val REQUEST_CODE_NOTIFICATION_PERMISSION = 101
    
    // Date Formats
    const val DATE_FORMAT_DISPLAY = "dd MMM yyyy"
    const val DATE_FORMAT_API = "yyyy-MM-dd"
    const val TIME_FORMAT_DISPLAY = "hh:mm a"
    const val TIME_FORMAT_API = "HH:mm"
    
    // Chat Bot Responses
    val CHATBOT_RESPONSES = mapOf(
        "hospital timings" to "Our hospital is open Monday to Saturday from 8:00 AM to 10:00 PM, and Sunday from 8:00 AM to 2:00 PM.",
        "emergency" to "For emergencies, please call our 24/7 emergency number: +91 7860120688",
        "appointment" to "You can book an appointment through our app or call us at +91 7860120688",
        "doctors" to "We have experienced doctors specializing in Cardiology, General Medicine, and Pediatrics.",
        "services" to "We provide Emergency Care, Cardiology, Pediatrics, Laboratory services, and Pharmacy.",
        "address" to "We are located at Rangpuri Road, Mahipalpur, New Delhi, District Azamgarh, Uttar Pradesh, PIN: 276001",
        "contact" to "You can reach us at +91 7860120688 or email us at info@samadnursinghome.com"
    )
    
    // Default Responses
    const val CHATBOT_DEFAULT_RESPONSE = "I'm here to help! You can ask me about hospital timings, appointments, doctors, services, emergency care, or our location."
    const val CHATBOT_GREETING = "Hello! I'm your AI health assistant. How can I help you today?"
}