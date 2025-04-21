package com.site7x24learn.internshipfrontend.data.model

data class SignupRequest(
    val name: String,
    val gender: String,
    val dob: String,
    val phone: String,
    val address: String,
    val email: String,
    val password: String
)