package com.site7x24learn.internshipfrontend.data.repositories

import com.site7x24learn.internshipfrontend.domain.models.application.StudentStatus
import com.site7x24learn.internshipfrontend.domain.repositories.StudentStatusRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PreviewStudentStatusRepository : StudentStatusRepository {
    override fun getStudents(): Flow<List<StudentStatus>> = flow {
        emit(
            listOf(
                StudentStatus(1, "Alice Johnson", "alice@example.com", ""),
                StudentStatus(2, "Bob Smith", "bob@example.com", ""),
                StudentStatus(3, "Carol White", "carol@example.com", "")
            )
        )
    }

    override suspend fun updateStatus(id: Int, status: String) {}
}