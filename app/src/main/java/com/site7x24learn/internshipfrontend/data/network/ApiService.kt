package com.site7x24learn.internshipfrontend.data.network

import com.site7x24learn.internshipfrontend.data.model.LoginRequest
import com.site7x24learn.internshipfrontend.data.model.LoginResponse
import com.site7x24learn.internshipfrontend.data.model.SignupRequest
import com.site7x24learn.internshipfrontend.data.model.SignupResponse
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("api/auth/login")
    suspend fun login(@Body loginRequest: LoginRequest): Response<LoginResponse>

    @POST("api/auth/signup")
    suspend fun signup(@Body request: SignupRequest): Response<SignupResponse>
}