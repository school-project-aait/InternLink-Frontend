package com.site7x24learn.internshipfrontend.presentation.screens.student



import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.site7x24learn.internshipfrontend.presentation.components.CustomTextField
import com.site7x24learn.internshipfrontend.presentation.components.RoundedBorderButton

@Composable
fun ApplyInternshipScreen(
    internshipId: Int?,
    onBack: () -> Unit
) {
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
                    label = "Full Name",
                    value = "",
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(
                    label = "Gender",
                    value = "",
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(
                    label = "Email",
                    value = "",
                    keyboardType = KeyboardType.Email,
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(
                    label = "Contact Number",
                    value = "",
                    keyboardType = KeyboardType.Phone,
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(
                    label = "Address",
                    value = "",
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(
                    label = "LinkedIn Profile",
                    value = "",
                    onValueChange = {}
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
                    label = "University Name",
                    value = "",
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(
                    label = "Degree Program",
                    value = "",
                    onValueChange = {}
                )

                Spacer(modifier = Modifier.height(8.dp))

                CustomTextField(
                    label = "Expected Graduation Year",
                    value = "",
                    keyboardType = KeyboardType.Number,
                    onValueChange = {}
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

                RoundedBorderButton(
                    buttonText = "Upload Resume",
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Handle file upload
                }
            }
        }

        // Submit Button
        RoundedBorderButton(
            buttonText = "Submit Application",
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            // Handle application submission
            onBack()
        }
    }
}