package com.site7x24learn.internshipfrontend.data.repositories

import com.site7x24learn.internshipfrontend.data.datasources.local.PreferencesManager
import com.site7x24learn.internshipfrontend.data.datasources.models.request.LoginRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.SignUpRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.AuthResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.remote.ApiService
import com.site7x24learn.internshipfrontend.domain.models.auth.AuthUser
import com.site7x24learn.internshipfrontend.domain.models.auth.LoginRequest
import com.site7x24learn.internshipfrontend.domain.models.auth.SignUpRequest
import com.site7x24learn.internshipfrontend.domain.repositories.AuthRepository
import retrofit2.Response
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val preferencesManager: PreferencesManager
) : AuthRepository {

    override suspend fun login(request: LoginRequest): Result<AuthUser> {
        return try {
            val response = apiService.login(request.toDto())
            handleLoginResponse(response)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun signUp(request: SignUpRequest): Result<AuthUser> {
        return try {
            val response = apiService.signUp(request.toDto())

            if (response.isSuccessful) {
                // Create minimal AuthUser since backend doesn't return full user data
                Result.success(
                    AuthUser(
                        email = request.email,
                        name = request.name,
                        // Add other minimal required fields
                        role = "student" // Default role
                    )
                )
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Exception(errorBody ?: "Registration failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    private fun handleLoginResponse(response: Response<AuthResponseDto>): Result<AuthUser> {
        return if (response.isSuccessful) {
            response.body()?.let { dto ->
                if (dto.success && dto.user != null && dto.token != null) {
                    val authUser = dto.toDomain()
                    preferencesManager.saveAuthToken(authUser.token)
                    Result.success(authUser)
                } else {
                    Result.failure(Exception(dto.message ?: "Authentication failed"))
                }
            } ?: Result.failure(Exception("Empty response body"))
        } else {
            // Improved error handling
            val errorBody = response.errorBody()?.string() ?: "Unknown error"
            Result.failure(Exception("HTTP ${response.code()}: $errorBody"))
        }
    }

    private fun handleSignUpResponse(
        response: Response<AuthResponseDto>,
        request: SignUpRequest
    ): Result<AuthUser> {
        return if (response.isSuccessful) {
            response.body()?.let { dto ->
                if (dto.success) {
                    // Create minimal AuthUser from request data
                    val authUser = AuthUser(
                        name = request.name,
                        email = request.email,
                        gender = request.gender,
                        birthDate = request.dob,
                        phone = request.phone,
                        address = request.address,
                        role = dto.user?.role ?: "student", // Use role from response if available
                        token = dto.token ?: "" // Include token if available
                    )
                    // Save token if present
                    dto.token?.let { token -> preferencesManager.saveAuthToken(token) }
                    Result.success(authUser)
                } else {
                    Result.failure(Exception(dto.message ?: "Registration failed"))
                }
            } ?: Result.failure(Exception("Empty response body"))
        } else {
            // Parse error message from response body if available
            val errorBody = response.errorBody()?.string() ?: response.message()
            Result.failure(Exception("HTTP ${response.code()}: $errorBody"))
        }
    }

    override suspend fun logout() {
        preferencesManager.clearToken()
    }

    // Updated extension functions for model conversion
    private fun LoginRequest.toDto() = LoginRequestDto(
        email = email,
        password = password
    )

    private fun SignUpRequest.toDto() = SignUpRequestDto(
        name = name,
        email = email,
        password = password,
        confirmPassword = password, // Assuming confirmPassword is same as password after validation
        gender = gender,
        birthDate = dob,
        phone = phone,
        address = address
    )

    private fun AuthResponseDto.toDomain() = AuthUser(
        id = user?.id ?: -1,
        name = user?.name ?: "",
        email = user?.email ?: "",
        gender = user?.gender ?: "",
        birthDate = user?.birthDate ?: "",
        phone = user?.phone ?: "",
        address = user?.address ?: "",
        role = user?.role ?: "student",
        token = token ?: ""
    )
}