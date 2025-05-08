package com.site7x24learn.internshipfrontend.domain.repositories

import com.site7x24learn.internshipfrontend.data.datasources.models.response.UserDto
import com.site7x24learn.internshipfrontend.domain.models.user.UserProfile

interface UserRepository {
    suspend fun getProfile(): UserDto
    suspend fun updateProfile(profile: UserProfile): UserProfile
    suspend fun deleteProfile()
}
