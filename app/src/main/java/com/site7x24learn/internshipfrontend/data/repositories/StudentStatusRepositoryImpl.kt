package com.site7x24learn.internshipfrontend.data.repositories


import com.site7x24learn.internshipfrontend.data.datasources.models.request.UpdateStatusRequestDto
import kotlinx.coroutines.flow.flow
import com.site7x24learn.internshipfrontend.data.datasources.remote.ApiService
import com.site7x24learn.internshipfrontend.domain.models.Application.StudentStatus
import com.site7x24learn.internshipfrontend.domain.repositories.StudentStatusRepository
import kotlinx.coroutines.flow.Flow
import com.site7x24learn.internshipfrontend.utils.Constants
import com.site7x24learn.internshipfrontend.utils.Constants.BASE_URL

class StudentStatusRepositoryImpl(
    private val api: ApiService
) : StudentStatusRepository {

    override fun getStudents(): Flow<List<StudentStatus>> = flow {
        try {
            val response = api.getApplications() // This returns StudentsResponse object
            if (response.isSuccessful) {
                val body = response.body()
                if (body?.success == true) {
                    // Map from ApplicationDto to StudentStatus
                    val students = body.data.map { application ->
                        StudentStatus(
                            id = application.id,
                            name = application.studentName,
                            email = application.companyName,
                            resume = application.resumePath?.let {
                                "${BASE_URL}${it.replace("\\", "/")}"
                            } ?: "No resume",
                            status = application.status
//                            id = application.id,
//                            name = "N/A", // ⚠️ Temporary - see note below
//                            email = "user${application.userId}@temp.com", // ⚠️ Temporary
//                            resume = application.resumePath ?: "No resume",
//                            status = application.status
                        )
                    }
                    emit(students)
                }
            }
              // Extract students list from response and emit
        } catch (e: Exception) {
            // Handle error (e.g., log or re-throw)
        }
    }

    override suspend fun updateStatus(id: Int, status: String) {
        // Create an instance of UpdateStatusRequestDto with the status string
        val requestDto = UpdateStatusRequestDto(status)

        // Now call the API with the correct type
        api.updateApplicationStatus(id, requestDto)
    }
}

//class StudentStatusRepositoryImpl(
//    private val api: ApiService
//) : StudentStatusRepository {
//
//    override fun getStudents(): Flow<List<StudentStatus>> = flow {
//        try {
//            val response = api.getStudents() // This returns StudentsResponse object
//            emit(response.students) // Extract students list from response and emit
//        } catch (e: Exception) {
//            // Handle error (e.g., log or re-throw)
//            throw e
//        }
//    }
//
//    override suspend fun updateStatus(id: Int, status: String) {
//        // Implement the update logic here, this would be a separate API call
//        api.updateApplicationStatus(id, status)
//    }
//}


//class StudentStatusRepositoryImpl(
//    private val api: ApiService
//) : StudentStatusRepository {
//
//    override fun getStudents(): Flow<List<StudentStatus>> = flow {
//        val response = api.getStudentApplications()
//        if (response.isSuccessful) {
//            val studentList = response.body()?.map {
//                StudentStatus(
//                    id = it.id,
//                    name = it.name,
//                    email = it.email,
//                    resume = it.resume,
//                    status = it.status
//                )
//            } ?: emptyList()
//            emit(studentList)
//        } else {
//            throw Exception("Failed to fetch students: ${response.message()}")
//        }
//    }


//    override fun getStudents(): Flow<List<StudentStatus>> {
//        // Keep using your existing implementation or replace with real API
//        return flow {
//            emit(
//                listOf(
//                    StudentStatus(1, "Alice Johnson", "alice@example.com", "resume", "Pending"),
//                    StudentStatus(2, "Bob Smith", "bob@example.com", "resume", "Pending"),
//                    StudentStatus(3, "Carol White", "carol@example.com", "resume", "Pending")
//                )
//            )
//        }
//    }

//    override suspend fun updateStatus(id: Int, status: String) {
//        val response = api.updateApplicationStatus(id, UpdateStatusRequestDto(status.lowercase()))
//        if (!response.isSuccessful || response.body()?.success != true) {
//            throw Exception("Failed to update status: ${response.body()?.message}")
//        }
//    }
//}
