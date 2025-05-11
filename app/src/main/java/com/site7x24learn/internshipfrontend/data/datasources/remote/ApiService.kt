// data/datasources/remote/ApiService.kt
package com.site7x24learn.internshipfrontend.data.datasources.remote

import com.site7x24learn.internshipfrontend.data.datasources.models.request.ApplicationUpdateRequest
import com.site7x24learn.internshipfrontend.data.datasources.models.request.CreateInternshipRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.LoginRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.SignUpRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.request.UpdateInternshipRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.ApplicationListResponse
import com.site7x24learn.internshipfrontend.data.datasources.models.response.ApplicationsResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.AuthResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.CreateApplicationResponse
import com.site7x24learn.internshipfrontend.data.datasources.models.response.DropdownDataResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.InternshipListResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.InternshipResponseDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.SingleApplicationResponse
import okhttp3.MultipartBody
import okhttp3.RequestBody

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query

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

    @GET("api/applications/only-admins")
    suspend fun getApplications(): Response<ApplicationsResponseDto>

    @PATCH("api/applications/{id}/status")
    suspend fun updateApplicationStatus(
        @Path("id") id: Int,
        @Body request: String
    ): Response<BaseResponseDto>

    @Multipart
    @POST("api/applications")
    suspend fun createApplication(
        @Part("internship_id") internshipId: RequestBody,
        @Part("university") university: RequestBody,
        @Part("degree") degree: RequestBody,
        @Part("graduation_year") graduationYear: RequestBody,
        @Part("linkdIn") linkedIn: RequestBody,
        @Part resume: MultipartBody.Part
    ): Response<CreateApplicationResponse>


    @GET("api/applications")
    suspend fun getUserApplications(): Response<ApplicationListResponse> // Changed from List<ApplicationResponse>

    @GET("api/applications/{id}")
    suspend fun getApplicationById(@Path("id") id: Int): Response<SingleApplicationResponse> // Changed from ApplicationResponse


    @PUT("api/applications/{id}")
    suspend fun updateApplication(
        @Path("id") applicationId: Int,
        @Body request: ApplicationUpdateRequest
    ): Response<Unit>

    @DELETE("api/applications/{id}")
    suspend fun deleteApplication(@Path("id") applicationId: Int): Response<Unit>

    @GET("api/applications/check")
    suspend fun checkExistingApplication(@Query("internshipId") internshipId: Int): Boolean

//    @PATCH("api/applications/{id}/status")
//    suspend fun updateApplicationStatus(
//        @Path("id") applicationId: Int,
//        @Query("status") status: String
//    )

    data class BaseResponseDto(
        val success: Boolean,
        val message: String?
    )
}





