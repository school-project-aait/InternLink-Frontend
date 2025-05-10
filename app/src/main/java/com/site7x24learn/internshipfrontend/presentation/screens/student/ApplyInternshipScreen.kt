package com.site7x24learn.internshipfrontend.presentation.screens.student



import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.site7x24learn.internshipfrontend.presentation.components.CustomTextField
//import com.site7x24learn.internshipfrontend.presentation.components.RoundedBorderButton
import com.site7x24learn.internshipfrontend.presentation.components.RoundedBorderButtonForApplication
import com.site7x24learn.internshipfrontend.presentation.navigation.Routes
import com.site7x24learn.internshipfrontend.presentation.viewmodels.ApplyInternshipEvent
import com.site7x24learn.internshipfrontend.presentation.viewmodels.ApplyInternshipViewModel
import com.site7x24learn.internshipfrontend.presentation.viewmodels.StudentDashboardViewModel

@Composable
fun ApplyInternshipScreen(
    applicationId: Int = -1,
    internshipId: Int?,
    onBack: () -> Unit,
    onSuccess:()->Unit,

    viewModel: ApplyInternshipViewModel = hiltViewModel(),
    dashboardViewModel: StudentDashboardViewModel = hiltViewModel()
) {
    LaunchedEffect(applicationId) {
        if (applicationId > 0) {
            viewModel.loadExistingApplication(applicationId)
        }
    }
    val state = viewModel.state
    val uiState = viewModel.uiState
    val context = LocalContext.current

    // File picker launcher
    val filePickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        uri?.let {
            val fileName = context.contentResolver.query(uri, null, null, null, null)?.use { cursor ->
                if (cursor.moveToFirst()) {
                    val displayNameIndex = cursor.getColumnIndex("_display_name")
                    cursor.getString(displayNameIndex)
                } else null
            }
            fileName?.let { name ->
                viewModel.onEvent(ApplyInternshipEvent.OnResumeSelected(uri, name))
            }
        }
    }

    // Handle successful submission
    LaunchedEffect(uiState.isApplicationSubmitted) {
        if (uiState.isApplicationSubmitted) {
            dashboardViewModel.refreshApplications()
            onSuccess()
//            navController.popBackStack()
//            navController.navigate(Routes.STUDENT_DASHBOARD)
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // Header with back button
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(onClick = onBack) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back"
                )
            }
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = "Apply for Internship",
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1B2A80)
            )
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Internship ID display
        Text(
            text = "Application for Internship #${internshipId ?: "N/A"}",
            style = MaterialTheme.typography.titleLarge,
            color = Color(0xFF2196F3),
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        // Error message
        uiState.error?.let { error ->
            Text(
                text = error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }

        // --- Card 1: Personal Information ---
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFF8E98A8)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .background(Color(0xF4EDEDED).copy(alpha = 0.23f))
            ) {
                Text(
                    text = "Personal Information",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                CustomTextField(
                    label = "University Name",
                    value = state.university,
                    onValueChange = { viewModel.onEvent(ApplyInternshipEvent.OnUniversityChange(it)) },
                    isError = uiState.fieldErrors.containsKey("university"),
                    errorMessage = uiState.fieldErrors["university"]
                )

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(
                    label = "Degree Program",
                    value = state.degree,
                    onValueChange = { viewModel.onEvent(ApplyInternshipEvent.OnDegreeChange(it)) },
                    isError = uiState.fieldErrors.containsKey("degree"),
                    errorMessage = uiState.fieldErrors["degree"]
                )

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(
                    label = "LinkedIn Profile",
                    value = state.linkedIn,
                    onValueChange = { viewModel.onEvent(ApplyInternshipEvent.OnLinkedInChange(it)) },
                    isError = uiState.fieldErrors.containsKey("linkedIn"),
                    errorMessage = uiState.fieldErrors["linkedIn"]
                )
            }
        }

        // --- Card 2: Education Information ---
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFF8E98A8)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = "Educational Background",
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier.padding(bottom = 8.dp)
                )

                CustomTextField(
                    label = "Expected Graduation Year",
                    value = state.graduationYear,
                    onValueChange = { viewModel.onEvent(ApplyInternshipEvent.OnGraduationYearChange(it)) },
                    keyboardType = KeyboardType.Number,
                    isError = uiState.fieldErrors.containsKey("graduationYear"),
                    errorMessage = uiState.fieldErrors["graduationYear"]
                )
            }
        }

        // --- Card 3: Resume Upload ---
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFF8E98A8)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp)
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Resume Upload",
                    style = MaterialTheme.typography.titleMedium
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Please upload your current resume (PDF format)",
                    style = MaterialTheme.typography.bodyMedium
                )

                Spacer(modifier = Modifier.height(16.dp))

                // Show selected file name if any
                state.resumeFileName?.let { fileName ->
                    Text(
                        text = "Selected: $fileName",
                        style = MaterialTheme.typography.bodySmall,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                // Show error if any
                uiState.fieldErrors["resume"]?.let { error ->
                    Text(
                        text = error,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.padding(bottom = 8.dp)
                    )
                }

                RoundedBorderButtonForApplication (
                    buttonText = "Upload Resume",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    filePickerLauncher.launch("application/pdf")
                }
            }
        }

        // Submit Button
        RoundedBorderButtonForApplication (
            buttonText = if (viewModel.isEditMode) "Update Application"
            else "Submit Application",
//            buttonText = if (uiState.isLoading) "Submitting..." else "Submit Application",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            enabled = !uiState.isLoading && internshipId != null
        ) {
            internshipId?.let {
                viewModel.onEvent(ApplyInternshipEvent.SubmitApplication(it, context))
            }
        }
    }
}