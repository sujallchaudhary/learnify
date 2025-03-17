package com.example.learnifyapp.loginregister

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKeys

// Save login credentials or token
fun saveLoginCredentials(context: Context, email: String, password: String, token: String) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.putString("email", email)
    editor.putString("password", password)
    editor.putString("token", token)
    editor.putBoolean("isLoggedIn", true)
    editor.apply()
}

// Retrieve login credentials or token
fun getLoginCredentials(context: Context): Triple<String?, String?, String?> {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
    val email = sharedPreferences.getString("email", null)
    val password = sharedPreferences.getString("password", null)
    val token = sharedPreferences.getString("token", null)
    return Triple(email, password, token)
}

// Check if the user is logged in
fun isLoggedIn(context: Context): Boolean {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
    return sharedPreferences.getBoolean("isLoggedIn", false)
}

// Clear login credentials (for logout)
fun clearLoginCredentials(context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences("AuthPrefs", Context.MODE_PRIVATE)
    val editor = sharedPreferences.edit()
    editor.clear()
    editor.apply()
}

fun getEncryptedSharedPreferences(context: Context): SharedPreferences {
    val masterKeyAlias = MasterKeys.getOrCreate(MasterKeys.AES256_GCM_SPEC)
    return EncryptedSharedPreferences.create(
        "secure_auth_prefs",
        masterKeyAlias,
        context,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )
}