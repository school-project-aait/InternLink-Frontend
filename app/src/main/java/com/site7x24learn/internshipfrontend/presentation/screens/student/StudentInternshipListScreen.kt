package com.site7x24learn.internshipfrontend.presentation.screens.student



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.site7x24learn.internshipfrontend.presentation.components.HeaderComponent
import com.site7x24learn.internshipfrontend.presentation.components.InternshipCard
import com.site7x24learn.internshipfrontend.presentation.components.StudentInternshipCard
import com.site7x24learn.internshipfrontend.presentation.navigation.Routes
import com.site7x24learn.internshipfrontend.presentation.viewmodels.InternshipListViewModel
// StudentInternshipListScreen.kt
@Composable
fun StudentInternshipListScreen(
    navController: NavHostController,
    viewModel: InternshipListViewModel = hiltViewModel()
) {
//    val state by viewModel.state.collectAsState().value

    val state = viewModel.state.collectAsState().value
    LaunchedEffect(Unit) {
        viewModel.loadInternships()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        HeaderComponent(
            onLogout = {
                navController.navigate(Routes.LOGIN) {
                    // Clear back stack completely
                    popUpTo(0)
                }
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Available Internships",
            style = MaterialTheme.typography.headlineMedium,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(16.dp))

        when {
            state.isLoading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.CenterHorizontally))
            }
            state.errorMessage != null -> {
                Text(
                    text = state.errorMessage,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            state.internships.isEmpty() -> {
                Text(
                    text = "No internships available",
                    modifier = Modifier.align(Alignment.CenterHorizontally)
                )
            }
            else -> {
                LazyColumn(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    items(state.internships) { internship ->
                        StudentInternshipCard(
                            internship = internship,
                            onApplyClick = { internshipId ->
                                navController.navigate(
                                    Routes.APPLY_INTERNSHIP.replace("{internshipId}", internshipId.toString())
                                ) {
                                    launchSingleTop = true
                                }
                            }
                        )

//                        StudentInternshipCard(
//                            internship = internship,
//                            onApplyClick = { internshipId ->
//                                navController.navigate(
//                                    Routes.APPLY_INTERNSHIP.replace("{internshipId}", internshipId.toString()
//                                ){
//                                    launchSingleTop = true
//                                }
//
//                            }
//                        )
                    }
                }
            }
        }
    }
}