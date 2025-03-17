package com.example.learnifyapp.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.learnifyapp.RetrofitClient
import com.example.learnifyapp.data.UserProfileResponse
import kotlinx.coroutines.launch

class ProfileViewModel : ViewModel() {
    private val _userProfile = MutableLiveData<UserProfileResponse?>()
    val userProfile: LiveData<UserProfileResponse?> get() = _userProfile

    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> get() = _error

    fun fetchUserProfile(token: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitClient.userApiService.getUserProfile(token)
                if (response.isSuccessful) {
                    _userProfile.value = response.body()
                } else {
                    _error.value = "Failed to fetch user profile: ${response.message()}"
                }
            } catch (e: Exception) {
                _error.value = "Network error: ${e.message}"
            }
        }
    }
}