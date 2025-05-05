package com.site7x24learn.internshipfrontend.domain.repositories

import com.site7x24learn.internshipfrontend.domain.models.internships.Category
import com.site7x24learn.internshipfrontend.domain.models.internships.Internship
import com.site7x24learn.internshipfrontend.utils.Resource

interface InternshipRepository {
    suspend fun getCategories(): Resource<List<Category>>
    suspend fun createInternship(
        title: String,
        description: String?,
        deadline: String,
        companyName: String,
        categoryId: Int
    ): Resource<Internship>
}

