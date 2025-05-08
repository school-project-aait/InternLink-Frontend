package com.site7x24learn.internshipfrontend.data.repositories

import com.site7x24learn.internshipfrontend.data.datasources.local.PreferencesManager
import com.site7x24learn.internshipfrontend.data.datasources.models.request.UpdateProfileRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.UserDto
import com.site7x24learn.internshipfrontend.data.datasources.remote.ApiService
import com.site7x24learn.internshipfrontend.data.datasources.remote.BaseResponseDto


import com.site7x24learn.internshipfrontend.domain.repositories.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val preferencesManager: PreferencesManager
) : UserRepository {

    override suspend fun getProfile(): UserDto {
        val response = apiService.getProfile()
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty profile response")
        } else {
            throw Exception("Get profile failed: ${response.code()} ${response.message()}")
        }
    }

    override suspend fun updateProfile(profile: UserDto): BaseResponseDto {
        val request = UpdateProfileRequestDto(
            name = profile.name.toString(),
            email = profile.email,
            phone = profile.phone.toString(),
            address = profile.address.toString(),
            gender = profile.gender.toString(),
            birthDate = profile.birthDate.toString(),
            role = TODO()
        )

        val response = apiService.updateProfile(profile.id, request)
        if (response.isSuccessful) {
            return response.body() ?: throw Exception("Empty update response")
        } else {
            throw Exception("Update profile failed: ${response.code()} ${response.message()}")
        }
    }

    override suspend fun deleteProfile() {
        val id = getUserId()
        val response = apiService.deleteProfile(id)
        if (!response.isSuccessful) {
            throw Exception("Delete profile failed: ${response.code()} ${response.message()}")
        }
    }

    private fun getUserId(): Int {
        return preferencesManager.getUserId()
            ?: throw Exception("User ID not found in preferences")
    }
}



