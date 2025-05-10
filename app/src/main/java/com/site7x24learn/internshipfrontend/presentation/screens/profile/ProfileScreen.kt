package com.site7x24learn.internshipfrontend.presentation.screens.profile




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
import com.site7x24learn.internshipfrontend.presentation.components.CategoryDropdown
import com.site7x24learn.internshipfrontend.presentation.navigation.Routes
import com.site7x24learn.internshipfrontend.presentation.viewmodels.ProfileViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileScreen(navController: NavHostController) {
    val viewModel: ProfileViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val error by viewModel.error.collectAsState()
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

    LaunchedEffect(Unit) {
        viewModel.getProfile()
    }

    LaunchedEffect(uiState) {
        when (uiState) {
            is ProfileViewModel.ProfileUiState.Success -> {
                val profile = (uiState as ProfileViewModel.ProfileUiState.Success).profile
                name = profile.name
                phone = profile.phone ?: ""
                address = profile.address ?: ""
                dob = profile.birthDate ?: ""
                selectedGenderId = genderOptions.find { it.name == profile.gender }?.id
            }
            is ProfileViewModel.ProfileUiState.Deleted -> {
                // Handle navigation after deletion
                navController.popBackStack()
                // Optionally navigate to login screen
                navController.navigate(Routes.LOGIN) {
                    popUpTo(Routes.PROFILE) { inclusive = true }
                }
            }
            else -> {}
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
            Text(
                text = error!!,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 8.dp)
            )
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
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Phone,
                imeAction = ImeAction.Next
            ),
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
            onCategorySelected = { selectedGenderId = it },
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val selectedGender = genderOptions.find { it.id == selectedGenderId }?.name ?: ""
                val updatedProfile = UserProfile(
                    id = (uiState as? ProfileViewModel.ProfileUiState.Success)?.profile?.id ?: 0,
                    name = name,
                    email =  "",
                    gender = selectedGender,
                    birthDate = dob,
                    phone = phone,
                    address = address,
                    role = (uiState as? ProfileViewModel.ProfileUiState.Success)?.profile?.role
                )
                viewModel.updateProfile(updatedProfile)
            },
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onPrimary)
            } else {
                Text("Update Profile")
            }
        }

        Button(
            onClick = { viewModel.deleteProfile() },
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.error,
                contentColor = MaterialTheme.colorScheme.onError
            ),
            enabled = !isLoading,
            modifier = Modifier.fillMaxWidth().padding(vertical = 8.dp)
        ) {
            if (isLoading) {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.onError)
            } else {
                Text("Delete Profile")
            }
        }
    }
}