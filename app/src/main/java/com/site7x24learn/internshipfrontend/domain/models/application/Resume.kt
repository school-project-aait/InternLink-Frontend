package com.site7x24learn.internshipfrontend.domain.models.application

data class Resume(
    val id: Int,
    val userId: Int,
    val originalFilename: String,
    val fileExtension: String,
    val attachmentPath: String,
    val uploadedAt: String
)
