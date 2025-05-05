// data/datasources/remote/ApiService.kt
package com.site7x24learn.internshipfrontend.data.datasources.remote

import com.site7x24learn.internshipfrontend.data.datasources.models.request.CreateInternshipRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.LoginRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.SignUpRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.AuthResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.DropdownDataResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.InternshipResponseDto

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


}



