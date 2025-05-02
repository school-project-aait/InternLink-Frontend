package com.site7x24learn.internshipfrontend.data.repository


import com.site7x24learn.internshipfrontend.data.model.Category
import com.site7x24learn.internshipfrontend.data.model.InternshipRequest
import com.site7x24learn.internshipfrontend.data.model.InternshipResponse

// Abstraction layer between ViewModel and data sources
interface InternshipRepository {
    suspend fun getCategories(): List<Category>
    suspend fun createInternship(request: InternshipRequest): Result<InternshipResponse>
}

sealed class Result<out T> {
    data class Success<out T>(val data: T) : Result<T>()
    data class Failure(val exception: Exception) : Result<Nothing>()
}