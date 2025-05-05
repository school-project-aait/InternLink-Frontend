package com.site7x24learn.internshipfrontend.presentation.screens.admin

import androidx.compose.foundation.text.KeyboardOptions
import com.site7x24learn.internshipfrontend.presentation.components.CategoryDropdown
import com.site7x24learn.internshipfrontend.presentation.components.FormButtons
import com.site7x24learn.internshipfrontend.presentation.components.FormSection



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.site7x24learn.internshipfrontend.presentation.components.HeaderComponent
import com.site7x24learn.internshipfrontend.presentation.viewmodels.InternshipEvent
import com.site7x24learn.internshipfrontend.presentation.viewmodels.InternshipViewModel

@Composable
fun AddInternships(
    navController: NavHostController,
    viewModel: InternshipViewModel = hiltViewModel()
) {
    val state by viewModel.state

    LaunchedEffect(Unit) {
        // Categories are already fetched in init block
    }

    if (state.isSuccess) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = "Internship created successfully",
                color = Color.Green,
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .padding(16.dp)
            )
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderComponent(onLogout = {
            navController.navigate("login") {
                popUpTo("add_internship") { inclusive = true }
            }
        })

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {

                FormSection(title = "Job Title") {
                    OutlinedTextField(
                        value = state.title,
                        onValueChange = { viewModel.onEvent(InternshipEvent.TitleChanged(it)) },
                        label = { Text("Enter Job Title") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FormSection(title = "Company Name") {
                    OutlinedTextField(
                        value = state.company,
                        onValueChange = { viewModel.onEvent(InternshipEvent.CompanyChanged(it)) },
                        label = { Text("Enter company name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FormSection(title = "Requirements / Description") {
                    OutlinedTextField(
                        value = state.description,
                        onValueChange = { viewModel.onEvent(InternshipEvent.DescriptionChanged(it)) },
                        label = { Text("Enter Requirements or Description") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FormSection(title = "Deadline") {
                    OutlinedTextField(
                        value = state.deadline,
                        onValueChange = { viewModel.onEvent(InternshipEvent.DeadlineChanged(it)) },
                        label = { Text("Enter deadline") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = androidx.compose.ui.text.input.KeyboardType.Text
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FormSection(title = "Category") {
                    CategoryDropdown(
                        categories = state.categories,
                        selectedCategoryId = state.selectedCategoryId,
                        onCategorySelected = {
                            viewModel.onEvent(InternshipEvent.CategoryChanged(it))
                        }
                    )
                }

                if (state.error != null) {
                    Text(
                        text = state.error ?: "",
                        color = Color.Red,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }

                FormButtons(
                    onSave = { viewModel.onEvent(InternshipEvent.Submit) },
                    onCancel = { viewModel.onEvent(InternshipEvent.Reset) }
                )
            }
        }
    }
}

