package com.site7x24learn.internshipfrontend.ui.screens.admin

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.site7x24learn.internshipfrontend.data.network.RetrofitClient
import com.site7x24learn.internshipfrontend.data.repository.InternshipRepositoryImpl
import com.site7x24learn.internshipfrontend.ui.components.*
import com.site7x24learn.internshipfrontend.viewmodel.InternshipViewModel
import com.site7x24learn.internshipfrontend.viewmodel.InternshipEvent
import com.site7x24learn.internshipfrontend.viewmodel.FormField

@Composable
fun AddInternships(
    navController:NavHostController,
//    viewModel: InternshipViewModel = viewModel()
    viewModel: InternshipViewModel = viewModel(
        factory = object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val apiService = RetrofitClient.getApiService()
                val repository = InternshipRepositoryImpl(apiService)
                return InternshipViewModel(repository) as T
            }
        }
    )

) {
    LaunchedEffect(Unit) {
        viewModel.fetchCategories()
    }
    val state = viewModel.formState
    val isFormSaved=viewModel.isFormSaved
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ){
        if (isFormSaved){
            Text("Internship created successully", color = Color.Green, modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 16.dp))
        }
    }


    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(6.dp)
            .paddingFromBaseline(90.dp, 60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        HeaderComponent(onLogout = {
            navController.navigate("login"){
                popUpTo("add_internship"){inclusive=true}
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
                        value = state.job,
                        onValueChange = { viewModel.onEvent(InternshipEvent.FieldUpdated(FormField.JOB, it)) },
                        label = { Text("Enter Job Title") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FormSection(title = "Company Name") {
                    OutlinedTextField(
                        value = state.company,
                        onValueChange = { viewModel.onEvent(InternshipEvent.FieldUpdated(FormField.COMPANY, it)) },
                        label = { Text("Enter company name") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FormSection(title = "Requirements") {
                    OutlinedTextField(
                        value = state.requirements,
                        onValueChange = { viewModel.onEvent(InternshipEvent.FieldUpdated(FormField.REQUIREMENTS, it)) },
                        label = { Text("Enter Requirements") },
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                FormSection(title = "Deadline") {
                    OutlinedTextField(
                        value = state.deadline,
                        onValueChange = { viewModel.onEvent(InternshipEvent.FieldUpdated(FormField.DEADLINE, it)) },
                        label = { Text("Enter deadline") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                            keyboardType = KeyboardType.Phone
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                }
                FormSection(title = "Category") {
                    CategoryDropdown(
                        categories = viewModel.categories,
                        selectedCategory = viewModel.formState.category,
                        onCategorySelected = { id ->
                            // Add null check
                            if (id.isNotEmpty()) {
                                viewModel.onEvent(InternshipEvent.FieldUpdated(FormField.CATEGORY, id))
                            }
                        }
                    )
                }

                FormButtons(
                    onSave = { viewModel.onEvent(InternshipEvent.Save) },
                    onCancel = { viewModel.onEvent(InternshipEvent.Cancel) }
                )
            }
        }
    }
}
//@Preview
//@Composable
//fun AddInternshipsPreview() {
//    AddInternships()
//}