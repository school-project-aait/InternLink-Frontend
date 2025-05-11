//package com.site7x24learn.internshipfrontend.data.repositories
//
//import com.site7x24learn.internshipfrontend.domain.models.Student.ProfileUiState
//import com.site7x24learn.internshipfrontend.domain.repositories.ProfileRepository
//import com.site7x24learn.internshipfrontend.data.datasources.remote.ApiService
//import com.site7x24learn.internshipfrontend.data.datasources.models.request.StudentProfileRequestDto
//import com.site7x24learn.internshipfrontend.data.datasources.models.response.StudentProfileResponseDto
//import retrofit2.Response
//import javax.inject.Inject
//import java.lang.Exception
//
//class ProfileRepositoryImpl @Inject constructor(
//    private val api: ApiService
//) : ProfileRepository {
//
//    override suspend fun saveProfile(profile: ProfileUiState): Result<Boolean> {
//        return try {
//            val request = StudentProfileRequestDto(
//            fullName = profile.fullName,
//            gender = profile.gender,
//            email = profile.email,
//            dob = profile.dob,
//            contact = profile.contact,
//            address = profile.address,
//            password = profile.password
//        )
//
//        val response = apiService.saveProfile(request)
//        if (response.isSuccessful && response.body()?.success == true) {
//            Result.success(true)
//        } else {
//            Result.failure(Exception("Save failed: ${response.body()?.message ?: "Unknown error"}"))
//        }
//        } catch (e: Exception) {
//            Result.failure(Exception("Failed to save profile: ${e.message}"))
//        }
//    }
//
//    override suspend fun deleteProfile(id: Int): Result<Boolean> {
//        return try {
//            val response = apiService.deleteProfile(id)
//            if (response.isSuccessful && response.body()?.success == true) {
//                Result.success(true)
//            } else {
//                Result.failure(Exception("Delete failed: ${response.body()?.message ?: "Unknown error"}"))
//            }
//        } catch (e: Exception) {
//            Result.failure(Exception("Network error: ${e.message}"))
//        }
//    }
//}
