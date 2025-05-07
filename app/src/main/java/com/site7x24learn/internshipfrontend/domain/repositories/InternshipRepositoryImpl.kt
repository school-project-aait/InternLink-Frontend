package com.site7x24learn.internshipfrontend.domain.repositories
//
//import com.site7x24learn.internshipfrontend.domain.models.internships.Category
//import com.site7x24learn.internshipfrontend.domain.models.internships.Internship
//import com.site7x24learn.internshipfrontend.utils.Resource
//import javax.inject.Inject
//
//// data/repository/InternshipRepositoryImpl.kt
//class InternshipRepositoryImpl @Inject constructor(
//    private val api: InternshipApi
//) : InternshipRepository {
//
//    override suspend fun getInternships(): Resource<List<Internship>> {
//        return try {
//            val response = api.getInternships()
//            if (response.isSuccessful && response.body()?.success == true) {
//                val internships = response.body()!!.data.map { dto ->
//                    Internship(
//                        id = dto.internship_id,
//                        title = dto.title,
//                        description = dto.description,
//                        deadline = dto.deadline,
//                        isActive = dto.is_active == 1,
//                        status = dto.status,
//                        companyName = dto.company_name,
//                        categoryName = dto.category_name,
//                        createdByName = dto.created_by_name,
//                        createdAt = dto.created_at
//                    )
//                }
//                Resource.Success(internships)
//            } else {
//                Resource.Error("Failed to load internships")
//            }
//        } catch (e: Exception) {
//            Resource.Error("Error: ${e.message}")
//        }
//    }
//    override suspend fun getCategories(): Resource<List<Category>> {
//        return try {
//            val response = api.getCategories()
//            if (response.isSuccessful && response.body()?.success == true) {
//                val categories = response.body()!!.data.map { dto ->
//                    Category(
//                        id = dto.category_id,
//                        name = dto.category_name
//                    )
//                }
//                Resource.Success(categories)
//            } else {
//                Resource.Error("Failed to load categories")
//            }
//        } catch (e: Exception) {
//            Resource.Error("Error: ${e.message}")
//        }
//    }
//
//
//}
