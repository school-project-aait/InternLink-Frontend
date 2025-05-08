package com.site7x24learn.internshipfrontend.domain.models.Application

data class Application(
    val id: Int,
    val userId: Int,
    val internshipId: Int,
    val university: String,
    val degree: String,
    val graduationYear: String,
    val linkedIn: String,
    val status: String, // "pending", "accepted", "rejected"
    val appliedAt: String,
    val resumeId: Int,
    val internshipTitle: String? = null,
    val companyName: String? = null
)
