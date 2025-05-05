package com.site7x24learn.internshipfrontend.data.datasources.models.response

data class InternshipDataDto(
    val internship_id: Int,
    val title: String,
    val description: String?,
    val deadline: String,
    val is_active: Boolean,
    val status: String,
    val company_name: String,
    val category_name: String,
    val created_by_name: String,
    val created_at: String,
    val updated_at: String
)
