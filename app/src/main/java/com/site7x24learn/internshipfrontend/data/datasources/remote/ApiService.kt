// data/datasources/remote/ApiService.kt
package com.site7x24learn.internshipfrontend.data.datasources.remote

import com.site7x24learn.internshipfrontend.data.datasources.models.request.CreateInternshipRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.LoginRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.SignUpRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.UpdateInternshipRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.UpdateStatusRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.StudentProfileRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.ApplicationsResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.AuthResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.DropdownDataResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.InternshipListResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.InternshipResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.StudentProfileResponseDto

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
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

//    @PATCH("api/applications/{id}/status")
//    suspend fun updateApplicationStatus(
//        @Path("id") id: Int,
//        @Body request: UpdateStatusRequestDto
//    ): Response<BaseResponseDto>

    @GET("api/applications")
    suspend fun getApplications(): Response<ApplicationsResponseDto>

    @PATCH("api/applications/{id}/status")
    suspend fun updateApplicationStatus(
        @Path("id") id: Int,
        @Body request: UpdateStatusRequestDto
    ): Response<BaseResponseDto>

//    @GET("api/applications")
//    suspend fun getStudentApplications(): Response<List<StudentStatusResponseDto>>
//
//    @GET("api/applications")
//    suspend fun getStudents(): StudentStatusResponseDto


//    @POST("api/internships/{id}/review")
//    suspend fun reviewApplications(@Path("id") internshipId: Int): Response<BaseResponseDto>

    @POST("api/profile")
    suspend fun saveProfile(
        @Body request: StudentProfileRequestDto
    ): Response<StudentProfileResponseDto>

    @DELETE("api/profile")
    suspend fun deleteProfile(): Response<StudentProfileResponseDto>
}

data class BaseResponseDto(
    val success: Boolean,
    val message: String?
)






