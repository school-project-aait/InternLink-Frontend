package com.site7x24learn.internshipfrontend.data.datasources.models.request

data class ApplicationUpdateRequest(
    val university: String,
    val degree: String,
    val graduation_year: Int,
    val linkdIn: String? = null
)