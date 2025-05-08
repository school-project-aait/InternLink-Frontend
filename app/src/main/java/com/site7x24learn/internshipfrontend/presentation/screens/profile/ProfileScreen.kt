package com.site7x24learn.internshipfrontend.presentation.screens.profile

import CategoryDropdown
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.site7x24learn.internshipfrontend.domain.models.internships.Category
import com.site7x24learn.internshipfrontend.domain.models.user.UserProfile

import com.site7x24learn.internshipfrontend.presentation.viewmodels.ProfileViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

@Composable
fun ProfileScreen(navController: NavHostController) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val context = LocalContext.current

    var name by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var selectedGenderId by remember { mutableStateOf<Int?>(null) }

    val genderOptions = listOf(
        Category(1, "Male"),
        Category(2, "Female")
    )

    val profile = viewModel.profileState
    val isLoading = viewModel.isLoading
    val error = viewModel.error

    LaunchedEffect(Unit) {
        viewModel.getProfile()
    }

    LaunchedEffect(profile) {
        profile?.let {
            name = it.name
            phone = it.phone.toString()
            address = it.address.toString()
            dob = it.birthDate.toString()
            selectedGenderId = genderOptions.find { gender -> gender.name == it.gender }?.id
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Profile", fontSize = 28.sp, modifier = Modifier.padding(bottom = 16.dp))

        if (error != null) {
            Text(text = error, color = MaterialTheme.colorScheme.error)
        }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = phone,
            onValueChange = { phone = it },
            label = { Text("Phone") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone, imeAction = ImeAction.Next),
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = address,
            onValueChange = { address = it },
            label = { Text("Address") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        OutlinedTextField(
            value = dob,
            onValueChange = { dob = it },
            label = { Text("Birth Date") },
            placeholder = { Text("YYYY-MM-DD") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        CategoryDropdown(
            categories = genderOptions,
            selectedCategoryId = selectedGenderId,
            onCategorySelected = { selectedGenderId = it }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val selectedGender = genderOptions.find { it.id == selectedGenderId }?.name ?: ""
                val updatedProfile = profile?.copy(
                    name = name,
                    phone = phone,
                    address = address,
                    birthDate = dob,
                    gender = selectedGender
                )
                if (updatedProfile != null) {
                    viewModel.updateProfile(updatedProfile)
                    Toast.makeText(context, "Profile updated", Toast.LENGTH_SHORT).show()
                }
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Update Profile")
        }

        Button(
            onClick = {
                viewModel.deleteProfile()
                Toast.makeText(context, "Profile deleted", Toast.LENGTH_SHORT).show()
                // Optionally navigate away
            },
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.error),
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            Text("Delete Profile")
        }
    }
}
