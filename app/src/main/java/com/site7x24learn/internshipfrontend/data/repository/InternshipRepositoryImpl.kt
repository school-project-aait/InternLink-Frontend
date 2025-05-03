package com.site7x24learn.internshipfrontend.data.repository


import com.site7x24learn.internshipfrontend.data.model.CategoriesResponse
import com.site7x24learn.internshipfrontend.data.network.ApiService
import com.site7x24learn.internshipfrontend.data.model.Category
import com.site7x24learn.internshipfrontend.data.model.InternshipRequest
import com.site7x24learn.internshipfrontend.data.model.InternshipResponse
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class InternshipRepositoryImpl(
    private val apiService: ApiService
) : InternshipRepository {

    override suspend fun getCategories(): List<Category> {
        return try {
            val response = apiService.getCategories()
            handleCategoriesResponse(response)
        } catch (e: IOException) {
            emptyList()
        } catch (e: HttpException) {
            emptyList()
        }
    }

    private fun handleCategoriesResponse(response: Response<CategoriesResponse>): List<Category> {
        return if (response.isSuccessful) {
            response.body()?.data?.categories ?: emptyList()
        } else {
            emptyList()
        }
    }

    override suspend fun createInternship(request: InternshipRequest): Result<InternshipResponse> {
        return try {
            // directly get the resposnse without .excute()
            val response = apiService.createInternship(request)
            handleCreateInternshipResponse(response)
        } catch (e: IOException) {
            Result.Failure(e)
        } catch (e: HttpException) {
            Result.Failure(e)
        }
    }

    private fun handleCreateInternshipResponse(response: Response<InternshipResponse>): Result<InternshipResponse> {
        return if (response.isSuccessful) {
            response.body()?.let {
                if (it.success) {
                    Result.Success(it)
                } else {
                    Result.Failure(Exception("API reported failure: ${it.message}"))
                }
            } ?: Result.Failure(Exception("Empty response body"))
        } else {
            Result.Failure(Exception("HTTP error: ${response.code()}"))
        }
    }
}