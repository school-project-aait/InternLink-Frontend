package com.site7x24learn.internshipfrontend.domain.usecases.student

import javax.inject.Inject
import com.site7x24learn.internshipfrontend.domain.models.Student.ProfileUiState
import com.site7x24learn.internshipfrontend.domain.repositories.ProfileRepository

class SaveProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke(profile: ProfileUiState) {
        repository.saveProfile(profile)
    }
}
