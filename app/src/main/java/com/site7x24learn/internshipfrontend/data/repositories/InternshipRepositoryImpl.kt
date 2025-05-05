package com.site7x24learn.internshipfrontend.data.repositories

import com.site7x24learn.internshipfrontend.data.datasources.models.request.CreateInternshipRequestDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.CategoryDto
import com.site7x24learn.internshipfrontend.data.datasources.models.response.InternshipDataDto
import com.site7x24learn.internshipfrontend.data.datasources.remote.ApiService
import com.site7x24learn.internshipfrontend.domain.models.internships.Category
import com.site7x24learn.internshipfrontend.domain.models.internships.Internship
import com.site7x24learn.internshipfrontend.domain.repositories.InternshipRepository
import com.site7x24learn.internshipfrontend.utils.Resource
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Singleton

@Singleton
class InternshipRepositoryImpl(private val apiService: ApiService) : InternshipRepository {

    override suspend fun getCategories(): Resource<List<Category>> {
        return try {
            val response = apiService.getCategories()
            if (response.isSuccessful) {
                response.body()?.let { body ->
                    if (body.success) {
                        val categories = body.data.categories.map { categoryDto: CategoryDto ->
                            Category(
                                id = categoryDto.category_id,
                                name = categoryDto.category_name
                            )
                        }
                        Resource.Success(categories)
                    } else {
                        Resource.Error("API request was not successful")
                    }
                } ?: Resource.Error("Response body is null")
            } else {
                Resource.Error(response.message())
            }
        } catch (e: HttpException) {
            Resource.Error(e.message ?: "HTTP error occurred")
        } catch (e: IOException) {
            Resource.Error("Network error: ${e.message}")
        }
    }

    override suspend fun createInternship(
        title: String,
        description: String?,
        deadline: String,
        companyName: String,
        categoryId: Int
    ): Resource<Internship> {
        return try {
            val request = CreateInternshipRequestDto(
                title = title,
                description = description,
                deadline = deadline,
                company_name = companyName,
                category_id = categoryId
            )

            val response = apiService.createInternship(request)

            if (response.isSuccessful) {
                response.body()?.let { body ->
                    if (body.success) {
                        val data: InternshipDataDto = body.data
                        Resource.Success(
                            Internship(
                                id = data.internship_id,
                                title = data.title,
                                description = data.description,
                                deadline = data.deadline,
                                companyName = data.company_name,
                                categoryName = data.category_name,
                                createdByName = data.created_by_name,
                                createdAt = data.created_at,
                                status = data.status,
                                isActive = data.is_active
                            )
                        )
                    } else {
                        Resource.Error(body.message ?: "Failed to create internship")
                    }
                } ?: Resource.Error("Response body is null")
            } else {
                Resource.Error(response.message())
            }
        } catch (e: HttpException) {
            Resource.Error(e.message ?: "HTTP error occurred")
        } catch (e: IOException) {
            Resource.Error("Network error: ${e.message}")
        }
    }
}