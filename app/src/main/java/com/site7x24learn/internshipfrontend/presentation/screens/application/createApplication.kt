package com.site7x24learn.internshipfrontend.presentation.screens.application

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.site7x24learn.internshipfrontend.presentation.components.CustomTextField
import com.site7x24learn.internshipfrontend.presentation.components.HeaderComponent
import com.site7x24learn.internshipfrontend.presentation.components.RoundedBorderButton
import com.site7x24learn.internshipfrontend.presentation.navigation.Routes
import com.site7x24learn.internshipfrontend.presentation.viewmodels.ApplicationViewModel

@Composable
fun CreateApplicationScreen(appviewModel: ApplicationViewModel = hiltViewModel(),
                            navController: NavHostController,) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HeaderComponent(onLogout = {
            navController.navigate(Routes.LOGIN) {
                popUpTo(Routes.ADD_INTERNSHIP) { inclusive = true }
            }
        })
        // --- Card 1: Personal Information ---
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFF8E98A8)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 50.dp, bottom = 40.dp, start = 10.dp, end = 10.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)
                                      .background(Color(0xF4EDEDED).copy(alpha = 0.23f))) {
                Text(
                    text = "Personal Information",
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.CenterHorizontally)
                )
                CustomTextField ("Name", appviewModel.name) { appviewModel.name = it }
                Spacer(modifier = Modifier.height(8.dp))
                CustomTextField("Gender", appviewModel.gender) { appviewModel.gender = it }
                Spacer(modifier = Modifier.height(8.dp))
                CustomTextField("Email", appviewModel.email, keyboardType = KeyboardType.Email) { appviewModel.email = it }
                Spacer(modifier = Modifier.height(8.dp))
                CustomTextField("Contact Number", appviewModel.contactNumber, keyboardType = KeyboardType.Phone) { appviewModel.contactNumber = it }
                Spacer(modifier = Modifier.height(8.dp))
                CustomTextField("Address", appviewModel.address) { appviewModel.address = it }
                Spacer(modifier = Modifier.height(8.dp))
                CustomTextField("LinkedIn Profile (if applicable)", appviewModel.linkedinUrl) { appviewModel.linkedinUrl = it }
                Spacer(modifier = Modifier.height(8.dp))

            }
        }

        // --- Card 2: Education Information ---
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFF8E98A8)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 40.dp, start = 10.dp, end = 10.dp)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text(
                    text = "Educational Background",
                    modifier = Modifier
                        .padding(12.dp)
                        .align(Alignment.CenterHorizontally)
                )
                CustomTextField("University Name", appviewModel.universityName) { appviewModel.universityName = it }
                Spacer(modifier = Modifier.height(8.dp))
                CustomTextField("Degree Program", appviewModel.degreeProgram) { appviewModel.degreeProgram = it }
                Spacer(modifier = Modifier.height(8.dp))
                CustomTextField("Expected Graduation Year", appviewModel.graduationYear, keyboardType = KeyboardType.Number) { appviewModel.graduationYear = it }
                Spacer(modifier = Modifier.height(8.dp))

            }
        }

        // --- Card 3: Upload CV ---
        Card(
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
            shape = RoundedCornerShape(12.dp),
            border = BorderStroke(1.dp, Color(0xFF8E98A8)),
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 20.dp, bottom = 40.dp, start = 10.dp, end = 10.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Resume",
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.titleMedium // optional: apply heading style
                )
                Text(
                    text = "Be sure to include an updated resume",
                    modifier = Modifier.padding(12.dp),
                    style = MaterialTheme.typography.bodyMedium
                )
                Spacer(modifier = Modifier.height(12.dp))
                RoundedBorderButton(
                    buttonText = "Upload CV",
                    modifier = Modifier.padding(16.dp)
                ) {
                    // Upload CV logic here
                }
            }
        }


        // --- Apply Button ---
        RoundedBorderButton(
            buttonText = "Apply",
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                .padding(bottom = 10.dp)
        ) {
            // Apply logic here
        }

    }
}
@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreviewCreateApplicationScreen() {
    val fakeNavController = rememberNavController()
    val fakeViewModel = ApplicationViewModel().apply {
        name = "John Doe"
        gender = "Male"
        email = "john.doe@example.com"
        contactNumber = "1234567890"
        address = "123 Main Street"
        linkedinUrl = "https://linkedin.com/in/johndoe"
        universityName = "AAiT"
        degreeProgram = "Software Engineering"
        graduationYear = "2025"
    }

    CreateApplicationScreen(
        appviewModel = fakeViewModel,
        navController = fakeNavController
    )
}
