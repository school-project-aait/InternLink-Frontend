package com.site7x24learn.internshipfrontend.data.model


data class InternshipResponse(
    val success: Boolean,
    val message: String,
    val data: InternshipData
)

data class InternshipData(
    val internship_id: Int,
    val title: String,
    val description: String,
    val deadline: String,
    val is_active: Int,
    val status: String,
    val company_name: String,
    val category_name: String,
    val created_by_name: String,
    val company_id: Int,
    val category_id: Int,
    val created_by: Int,
    val created_at: String,
    val updated_at: String
)
