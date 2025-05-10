package com.site7x24learn.internshipfrontend.domain.usecases.student

import javax.inject.Inject
import com.site7x24learn.internshipfrontend.domain.repositories.ProfileRepository

class DeleteProfileUseCase @Inject constructor(
    private val repository: ProfileRepository
) {
    suspend operator fun invoke() {
        repository.deleteProfile()
    }
}
