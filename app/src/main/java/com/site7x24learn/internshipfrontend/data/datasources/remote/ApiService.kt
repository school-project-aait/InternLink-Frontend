// data/datasources/remote/ApiService.kt
package com.site7x24learn.internshipfrontend.data.datasources.remote

import com.site7x24learn.internshipfrontend.data.datasources.models.request.CreateInternshipRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.LoginRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.SignUpRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.UpdateInternshipRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.UpdateProfileRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.AuthResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.DropdownDataResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.InternshipListResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.InternshipResponseDto

import com.site7x24learn.internshipfrontend.data.datasources.models.response.UserDto

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ApiService {
    @POST("api/auth/signup")
    suspend fun signUp(
        @Body signUpRequest: SignUpRequestDto
    ): Response<AuthResponseDto>

    @POST("api/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequestDto
    ): Response<AuthResponseDto>
    @GET("api/internships/dropdown-data")
    suspend fun getCategories(): Response<DropdownDataResponseDto>

    @POST("api/internships")
    suspend fun createInternship(
        @Body request: CreateInternshipRequestDto
    ): Response<InternshipResponseDto>
    @GET("api/internships")
    suspend fun getInternships(): Response<InternshipListResponseDto>

    @GET("api/internships/{id}")
    suspend fun getInternshipById(@Path("id") id: Int): Response<InternshipResponseDto>
    @PUT("api/internships/{id}")
    suspend fun updateInternship(
        @Path("id") id: Int,
        @Body request: UpdateInternshipRequestDto
    ): Response<BaseResponseDto>

    @DELETE("api/internships/{id}")
    suspend fun deleteInternship(@Path("id") id: Int): Response<BaseResponseDto>


    @POST("api/internships/{id}/review")
    suspend fun reviewApplications(@Path("id") internshipId: Int): Response<BaseResponseDto>

    @GET("api/users/profile")
    suspend fun getProfile(): Response<UserDto>

    @PUT("api/users/{id}")
    suspend fun updateProfile(
        @Path("id") id: Int,
        @Body request: UpdateProfileRequestDto
    ): Response<BaseResponseDto>

    @DELETE("api/users/{id}")
    suspend fun deleteProfile(@Path("id") id: Int): Response<BaseResponseDto>

//    @POST("api/internships/{id}/review")
//    suspend fun reviewApplications(@Path("id") internshipId: Int): Response<BaseResponseDto>

}

data class BaseResponseDto(
    val success: Boolean,
    val message: String?
)






