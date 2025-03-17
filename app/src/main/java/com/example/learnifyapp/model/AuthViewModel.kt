package com.example.learnifyapp.model

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnifyapp.apiinterface.LoginRequest
import com.example.learnifyapp.apiinterface.LoginResponse
import com.example.learnifyapp.apiinterface.RegisterRequest
import com.example.learnifyapp.apiinterface.RegisterResponse
import com.example.learnifyapp.RetrofitClient
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    var loginResponse by mutableStateOf<LoginResponse?>(null)
        private set

    var registerResponse by mutableStateOf<RegisterResponse?>(null)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun login(email: String, password: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.userApiService.login(LoginRequest(email, password))
                if (response.isSuccessful) {
                    loginResponse = response.body()
                } else {
                    error = "Login failed: ${response.message()}"
                }
            } catch (e: Exception) {
                error = "Network error: ${e.message}"
            }
        }
    }

    fun register(name: String, email: String, password: String, grade: String, instituteName: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.userApiService.register(RegisterRequest(name, email, password, grade, instituteName))
                if (response.isSuccessful) {
                    registerResponse = response.body()
                } else {
                    error = "Registration failed: ${response.message()}"
                }
            } catch (e: Exception) {
                error = "Network error: ${e.message}"
            }
        }
    }
}