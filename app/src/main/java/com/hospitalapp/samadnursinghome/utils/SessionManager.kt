package com.hospitalapp.samadnursinghome.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey

class SessionManager(context: Context) {
    
    private val masterKey = MasterKey.Builder(context)
        .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
        .build()
    
    private val sharedPreferences: SharedPreferences = EncryptedSharedPreferences.create(
        context,
        "hospital_session",
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
    
    companion object {
        private const val KEY_IS_LOGGED_IN = "is_logged_in"
        private const val KEY_USER_EMAIL = "user_email"
        private const val KEY_USER_NAME = "user_name"
        private const val KEY_USER_ID = "user_id"
        private const val KEY_LOGIN_TIME = "login_time"
    }
    
    /**
     * Save user session after successful login
     */
    fun createSession(email: String, name: String = "", userId: String = "") {
        with(sharedPreferences.edit()) {
            putBoolean(KEY_IS_LOGGED_IN, true)
            putString(KEY_USER_EMAIL, email)
            putString(KEY_USER_NAME, name)
            putString(KEY_USER_ID, userId)
            putLong(KEY_LOGIN_TIME, System.currentTimeMillis())
            apply()
        }
    }
    
    /**
     * Check if user is logged in
     */
    fun isLoggedIn(): Boolean {
        return sharedPreferences.getBoolean(KEY_IS_LOGGED_IN, false)
    }
    
    /**
     * Get user email
     */
    fun getUserEmail(): String? {
        return sharedPreferences.getString(KEY_USER_EMAIL, null)
    }
    
    /**
     * Get user name
     */
    fun getUserName(): String? {
        return sharedPreferences.getString(KEY_USER_NAME, null)
    }
    
    /**
     * Get user ID
     */
    fun getUserId(): String? {
        return sharedPreferences.getString(KEY_USER_ID, null)
    }
    
    /**
     * Get login time
     */
    fun getLoginTime(): Long {
        return sharedPreferences.getLong(KEY_LOGIN_TIME, 0)
    }
    
    /**
     * Clear session (logout)
     */
    fun clearSession() {
        with(sharedPreferences.edit()) {
            clear()
            apply()
        }
    }
    
    /**
     * Check if session is expired (optional - 30 days)
     */
    fun isSessionExpired(): Boolean {
        val loginTime = getLoginTime()
        val currentTime = System.currentTimeMillis()
        val thirtyDaysInMillis = 30L * 24 * 60 * 60 * 1000 // 30 days
        
        return (currentTime - loginTime) > thirtyDaysInMillis
    }
}