package com.site7x24learn.internshipfrontend.presentation.viewmodels


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.site7x24learn.internshipfrontend.data.repositories.PreviewStudentStatusRepository
import com.site7x24learn.internshipfrontend.domain.models.application.StudentStatus
import com.site7x24learn.internshipfrontend.domain.repositories.StudentStatusRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


class StudentStatusViewModel(
    private val repository: StudentStatusRepository=PreviewStudentStatusRepository()
) : ViewModel() {

    private val _uiState = MutableStateFlow(StudentStatusUiState())
    val uiState: StateFlow<StudentStatusUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getStudents().collect { list ->
                _uiState.value = StudentStatusUiState(students = list)
            }
        }
    }

    fun updateStatus(id: Int, newStatus: String) {
        viewModelScope.launch {
            repository.updateStatus(id, newStatus)
        }
    }
}


data class StudentStatusUiState(
    val students: List<StudentStatus> = emptyList()
)
