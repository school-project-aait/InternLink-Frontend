package com.site7x24learn.internshipfrontend.presentation.viewmodels



import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.site7x24learn.internshipfrontend.domain.models.internships.Internship
import com.site7x24learn.internshipfrontend.domain.usecases.internships.GetInternshipsUseCase
import com.site7x24learn.internshipfrontend.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InternshipListViewModel @Inject constructor() : ViewModel() {

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _internships = MutableStateFlow<List<Internship>>(emptyList())
    val internships: StateFlow<List<Internship>> = _internships


    init {
        // Dummy data with all required parameters
        _internships.value = listOf(
            Internship(
                id = 12,
                title = "Android Developer",
                categoryName = "Mobile Dev",
                companyName = "Tech Co",
                description = "Knowledge of Kotlin",
                deadline = "2025-06-01",
                isActive = true,
                status = "Open",
                createdByName = "Admin",
                createdAt = "2025-01-01"

            ),
            Internship(
                id = 11,
                title = "Web Designer",
                categoryName = "Design",
                companyName = "Creative Inc",
                description = "Figma & Adobe XD",
                deadline = "2025-05-20",
                isActive = true,
                status = "Open",
                createdByName = "Admin",
                createdAt = "2025-01-02"
            )
        )
    }

    fun onSearchQueryChanged(query: String) {
        _searchQuery.value = query
        // Optional: filtering logic
    }
}




















































//import androidx.lifecycle.ViewModel
//import androidx.lifecycle.viewModelScope
//import com.site7x24learn.internshipfrontend.domain.models.internships.Internship
//import com.site7x24learn.internshipfrontend.domain.usecases.internships.DeleteInternshipUseCase
//import com.site7x24learn.internshipfrontend.domain.usecases.internships.GetInternshipsUseCase
//import com.site7x24learn.internshipfrontend.utils.Resource
//import dagger.hilt.android.lifecycle.HiltViewModel
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.StateFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//import javax.inject.Inject

//
//@HiltViewModel
//class InternshipListViewModel @Inject constructor(
//    private val getInternships: GetInternshipsUseCase
//) : ViewModel() {
//
//    // UI State
//    private val _state = MutableStateFlow(InternshipListState())
//    val state: StateFlow<InternshipListState> = _state
//
//    init {
//        loadInternships()
//    }
//
//    fun loadInternships() {
//        _state.update { it.copy(isLoading = true, error = null) }
//
//        viewModelScope.launch {
//            when (val result = getInternships()) {
//                is Resource.Success -> {
//                    _state.update {
//                        it.copy(
//                            internships = result.data ?: emptyList(),
//                            isLoading = false,
//                            error = null
//                        )
//                    }
//                }
//                is Resource.Error -> {
//                    _state.update {
//                        it.copy(
//                            isLoading = false,
//                            error = result.message ?: "Failed to load internships"
//                        )
//                    }
//                }
//                is Resource.Loading -> {
//                    // Optional: Can update loading state if needed
//                }
//            }
//        }
//    }
//}
//
//// State definition
//data class InternshipListState(
//    val internships: List<Internship> = emptyList(),
//    val isLoading: Boolean = false,
//    val error: String? = null
//)