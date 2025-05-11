package com.site7x24learn.internshipfrontend.presentation.viewmodels



import android.content.Context
import android.net.Uri
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.site7x24learn.internshipfrontend.domain.models.application.ApplicationRequest
import com.site7x24learn.internshipfrontend.domain.repositories.ApplicationRepository
import com.site7x24learn.internshipfrontend.domain.usecases.application.CheckExistingApplicationUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.application.CreateApplicationUseCase
import com.site7x24learn.internshipfrontend.domain.usecases.application.UpdateApplicationUseCase
import com.site7x24learn.internshipfrontend.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ApplyInternshipViewModel @Inject constructor(
    // 🚨 Add repository to load existing application
    private val repository: ApplicationRepository,
    private val updateApplicationUseCase: UpdateApplicationUseCase,
    private val createApplicationUseCase: CreateApplicationUseCase,
    private val checkExistingApplicationUseCase: CheckExistingApplicationUseCase
) : ViewModel() {

    // 🚨 Track edit mode state
    var isEditMode by mutableStateOf(false)
        private set

    // 🚨 Add method to load existing application
    fun loadExistingApplication(applicationId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)
            when (val result = repository.getApplicationById(applicationId)) {
                is Resource.Success -> {
                    result.data?.let { app ->
                        state = state.copy(
                            university = app.university,
                            degree = app.degree,
                            graduationYear = app.graduationYear.toString(),
                            linkedIn = app.linkdIn ?: ""
                        )
                        isEditMode = true
                    }
                    uiState = uiState.copy(isLoading = false)
                }

                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = result.message ?: "Failed to load application"
                    )
                }

                Resource.Loading -> Unit
            }
        }
    }

    var state by mutableStateOf(ApplyInternshipState())
        private set

    var uiState by mutableStateOf(ApplyInternshipUiState())
        private set

    private var applicationContext: Context? = null

    fun onEvent(event: ApplyInternshipEvent) {
        when (event) {
            is ApplyInternshipEvent.OnUniversityChange -> {
                state = state.copy(university = event.university)
            }
            is ApplyInternshipEvent.OnDegreeChange -> {
                state = state.copy(degree = event.degree)
            }
            is ApplyInternshipEvent.OnGraduationYearChange -> {
                state = state.copy(graduationYear = event.graduationYear)
            }
            is ApplyInternshipEvent.OnLinkedInChange -> {
                state = state.copy(linkedIn = event.linkedIn)
            }
            is ApplyInternshipEvent.OnResumeSelected -> {
                state = state.copy(
                    resumeUri = event.uri,
                    resumeFileName = event.fileName
                )
            }
            is ApplyInternshipEvent.SubmitApplication -> {
                applicationContext = event.context
                submitApplication(event.internshipId)
            }
        }
    }
    // 🚨 Modified submit method
    private fun submitApplication(internshipId: Int) {
        viewModelScope.launch {
            uiState = uiState.copy(isLoading = true)

            // Skip existing check only if we're editing AND keeping same internship
            if (!isEditMode) {
                val alreadyApplied = checkExistingApplicationUseCase(internshipId)
                if (alreadyApplied is Resource.Success && alreadyApplied.data) {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = "You've already applied to this internship",
                        isApplicationSubmitted = false
                    )
                    return@launch
                }
            }

            // 🚨 Split create/update logic
            if (isEditMode) {
                handleUpdateApplication(internshipId)
            } else {
                handleCreateApplication(internshipId)
            }



//    private fun submitApplication(internshipId: Int) {
//        viewModelScope.launch {
//            uiState = uiState.copy(isLoading = true)
//
//            val alreadyApplied = checkExistingApplicationUseCase(internshipId)
//            if (alreadyApplied is Resource.Success && alreadyApplied.data) {
//                uiState = uiState.copy(
//                    isLoading = false,
//                    error = "You've already applied to this internship",
//                    isApplicationSubmitted = false
//                )
//                return@launch
//            }

//            val validationErrors = validateFields()
//            if (validationErrors.isNotEmpty()) {
//                uiState = uiState.copy(
//                    isLoading = false,
//                    fieldErrors = validationErrors,
//                    isApplicationSubmitted = false
//                )
//                return@launch
//            }

            val resumeBytes = applicationContext?.let { context ->
                state.resumeUri?.let { uri ->
                    context.contentResolver.openInputStream(uri)?.use { stream ->
                        stream.readBytes()
                    }
                }
            } ?: run {
                uiState = uiState.copy(
                    isLoading = false,
                    error = "Resume is required",
                    isApplicationSubmitted = false
                )
                return@launch
            }

            val applicationRequest = ApplicationRequest(
                internshipId = internshipId,
                university = state.university,
                degree = state.degree,
                graduationYear = state.graduationYear.toIntOrNull() ?: 0,
                linkdIn = state.linkedIn
            )

            when (val result = createApplicationUseCase(
                applicationRequest,
                resumeBytes,
                state.resumeFileName ?: "resume.pdf"
            )) {
                is Resource.Success -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        isApplicationSubmitted = true,
                        error = null,
                        fieldErrors = emptyMap()
                    )
                }
                is Resource.Error -> {
                    uiState = uiState.copy(
                        isLoading = false,
                        error = result.message,
                        isApplicationSubmitted = false
                    )
                }
                Resource.Loading -> {
                    // Optional: maintain loading state
                    uiState = uiState.copy(isLoading = true)
                }
            }
        }
    }

    private fun validateFields(): Map<String, String> {
        val errors = mutableMapOf<String, String>()

        if (state.university.isBlank()) {
            errors["university"] = "University name is required"
        }

        if (state.degree.isBlank()) {
            errors["degree"] = "Degree program is required"
        }

        state.graduationYear.toIntOrNull()?.let { year ->
            if (year < 2000 || year > 2030) {
                errors["graduationYear"] = "Enter a valid year between 2000-2030"
            }
        } ?: run {
            errors["graduationYear"] = "Graduation year is required"
        }

        if (state.linkedIn.isBlank()) {
            errors["linkedIn"] = "LinkedIn URL is required"
        } else if (!state.linkedIn.contains("linkedin.com")) {
            errors["linkedIn"] = "Enter a valid LinkedIn URL"
        }

        if (state.resumeUri == null) {
            errors["resume"] = "Resume is required"
        }

        return errors
    }
    private suspend fun handleCreateApplication(internshipId: Int) {
        val resumeBytes = applicationContext?.let { context ->
            state.resumeUri?.let { uri ->
                context.contentResolver.openInputStream(uri)?.use { stream ->
                    stream.readBytes()
                }
            }
        } ?: run {
            uiState = uiState.copy(
                isLoading = false,
                error = "Resume is required",
                isApplicationSubmitted = false
            )
            return
        }

        val applicationRequest = ApplicationRequest(
            internshipId = internshipId,
            university = state.university,
            degree = state.degree,
            graduationYear = state.graduationYear.toIntOrNull() ?: 0,
            linkdIn = state.linkedIn
        )

        when (val result = createApplicationUseCase(
            applicationRequest,
            resumeBytes,
            state.resumeFileName ?: "resume.pdf"
        )) {
            is Resource.Success -> {
                uiState = uiState.copy(
                    isLoading = false,
                    isApplicationSubmitted = true,
                    error = null,
                    fieldErrors = emptyMap()
                )
            }
            is Resource.Error -> {
                uiState = uiState.copy(
                    isLoading = false,
                    error = result.message,
                    isApplicationSubmitted = false
                )
            }
            Resource.Loading -> Unit
        }
    }

    private suspend fun handleUpdateApplication(internshipId: Int) {
        val updates = mapOf(
            "university" to state.university,
            "degree" to state.degree,
            "graduationYear" to state.graduationYear.toInt(),
            "linkdIn" to state.linkedIn
        )

        when (val result = updateApplicationUseCase(internshipId, updates)) {
            is Resource.Success -> {
                uiState = uiState.copy(
                    isLoading = false,
                    isApplicationSubmitted = true,
                    error = null,
                    fieldErrors = emptyMap()
                )
            }
            is Resource.Error -> {
                uiState = uiState.copy(
                    isLoading = false,
                    error = result.message,
                    isApplicationSubmitted = false
                )
            }
            Resource.Loading -> Unit
        }
    }

}

// State and Event classes
data class ApplyInternshipState(
    val university: String = "",
    val degree: String = "",
    val graduationYear: String = "",
    val linkedIn: String = "",
    val resumeUri: Uri? = null,
    val resumeFileName: String? = null
)

data class ApplyInternshipUiState(
    val isLoading: Boolean = false,
    val isApplicationSubmitted: Boolean = false,
    val error: String? = null,
    val fieldErrors: Map<String, String> = emptyMap()
)

sealed class ApplyInternshipEvent {
    data class OnUniversityChange(val university: String) : ApplyInternshipEvent()
    data class OnDegreeChange(val degree: String) : ApplyInternshipEvent()
    data class OnGraduationYearChange(val graduationYear: String) : ApplyInternshipEvent()
    data class OnLinkedInChange(val linkedIn: String) : ApplyInternshipEvent()
    data class OnResumeSelected(val uri: Uri, val fileName: String) : ApplyInternshipEvent()
    data class SubmitApplication(val internshipId: Int, val context: Context) : ApplyInternshipEvent()
}
