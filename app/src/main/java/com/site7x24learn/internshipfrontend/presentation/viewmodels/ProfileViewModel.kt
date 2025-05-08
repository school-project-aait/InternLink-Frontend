package com.site7x24learn.internshipfrontend.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.site7x24learn.internshipfrontend.domain.models.user.UserProfile
import com.site7x24learn.internshipfrontend.domain.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val repository: UserRepository
) : ViewModel() {
    var profileState by mutableStateOf<UserProfile?>(null)
        private set

    var isLoading by mutableStateOf(false)
        private set

    var error by mutableStateOf<String?>(null)
        private set

    fun getProfile() {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                profileState = repository.getProfile()
            } catch (e: Exception) {
                error = e.message ?: "Failed to load profile"
            } finally {
                isLoading = false
            }
        }
    }

    fun updateProfile(profile: UserProfile) {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                profileState = repository.updateProfile(profile)
            } catch (e: Exception) {
                error = e.message ?: "Failed to update profile"
            } finally {
                isLoading = false
            }
        }
    }

    fun deleteProfile() {
        viewModelScope.launch {
            isLoading = true
            error = null
            try {
                repository.deleteProfile()
                profileState = null
            } catch (e: Exception) {
                error = e.message ?: "Failed to delete profile"
            } finally {
                isLoading = false
            }
        }
    }
}