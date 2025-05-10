package com.site7x24learn.internshipfrontend.presentation.screens.student



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.site7x24learn.internshipfrontend.domain.models.application.Application
import com.site7x24learn.internshipfrontend.presentation.components.ApplicationCard
import com.site7x24learn.internshipfrontend.presentation.navigation.Routes
import com.site7x24learn.internshipfrontend.presentation.viewmodels.StudentDashboardViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StudentDashboardScreen(
    navController: NavController,
    viewModel: StudentDashboardViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    val isRefreshing by viewModel.isRefreshing.collectAsState()

    // Auto-load on first launch and when returning from other screens
    LaunchedEffect(Unit) {
        viewModel.loadApplications()
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(
                        text = "My Applications",
                        fontWeight = FontWeight.Bold,
                        color = Color(0xFF1B2A80))
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "Back"
                        )
                    }
                },
                actions = {
                    if (state.isLoading && !isRefreshing) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(24.dp)
                                .padding(end = 16.dp),
                            strokeWidth = 2.dp
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.isLoading && !isRefreshing -> LoadingScreen()
                state.applications.isEmpty() -> EmptyScreen()
                else -> ApplicationsList(
                    applications = state.applications,
                    padding = innerPadding,
                    viewModel = viewModel,
                    navController = navController
                )
            }

            // Show error message if exists
            state.error?.let { error ->
                Snackbar(
                    modifier = Modifier
                        .align(Alignment.BottomCenter)
                        .padding(16.dp),
                    action = {
                        TextButton(
                            onClick = { viewModel.loadApplications() }
                        ) {
                            Text("Retry")
                        }
                    }
                ) {
                    Text(error)
                }
            }
        }
    }
}

@Composable
private fun LoadingScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator()
    }
}

@Composable
private fun EmptyScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "No applications found",
            style = MaterialTheme.typography.bodyLarge
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Apply to internships to see them here",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.Gray
        )
    }
}

@Composable
private fun ApplicationsList(
    applications: List<Application>,
    padding: PaddingValues,
    viewModel: StudentDashboardViewModel,
    navController: NavController
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(
            items = applications,
            key = { it.id }
        ) { application ->
            ApplicationCard(
                application = application,
                onWithdraw = {
                    viewModel.deleteApplication(application.id) {
                        // Optional: Show snackbar or confirmation
                    }
                },
                onUpdate = {
                    // Prepare updates (example - modify as needed)
                    val updates = buildMap<String, Any> {
                        put("university", application.university)
                        put("degree", application.degree)
                        put("graduation_year", application.graduationYear.toString().toInt())
                        application.linkdIn?.let { put("linkdIn", it) } // Only include if not null
                    }

                    viewModel.updateApplication(application.id, updates) {
                        navController.navigate(
                            "${Routes.APPLY_INTERNSHIP}/${application.internshipId}"
                        ) {
                            launchSingleTop = true
                            // Preserve back stack while ensuring fresh data
                            restoreState = true
                        }
                    }
                }
            )
        }
    }
}
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.ArrowBack
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import com.site7x24learn.internshipfrontend.R
//import com.site7x24learn.internshipfrontend.domain.models.application.Application
//import com.site7x24learn.internshipfrontend.domain.models.application.ApplicationStatus
//import com.site7x24learn.internshipfrontend.presentation.components.ApplicationCard
//import com.site7x24learn.internshipfrontend.presentation.components.StatusIndicator
//import com.site7x24learn.internshipfrontend.presentation.viewmodels.StudentDashboardEvent
//import com.site7x24learn.internshipfrontend.presentation.viewmodels.StudentDashboardViewModel
//
//@Composable
//fun StudentDashboardScreen(
//    onBack: () -> Unit,
//    onNavigateToUpdate: (Int) -> Unit,
//    viewModel: StudentDashboardViewModel = hiltViewModel()
//) {
//    val state = viewModel.state
//
//    // Load applications when the screen is first displayed
//    LaunchedEffect(Unit) {
//        viewModel.onEvent(StudentDashboardEvent.LoadApplications)
//    }
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ) {
//        // Header with back button
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(16.dp),
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            IconButton(onClick = onBack) {
//                Icon(
//                    imageVector = Icons.Default.ArrowBack,
//                    contentDescription = "Back"
//                )
//            }
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = "My Applications",
//                style = MaterialTheme.typography.headlineSmall,
//                fontWeight = FontWeight.Bold,
//                color = Color(0xFF1B2A80)
//            )
//        }
//
//        // Error message
//        state.error?.let { error ->
//            Text(
//                text = error,
//                color = MaterialTheme.colorScheme.error,
//                modifier = Modifier.padding(horizontal = 16.dp)
//            )
//        }
//
//        // Loading indicator
//        if (state.isLoading) {
//            Box(
//                modifier = Modifier.fillMaxSize(),
//                contentAlignment = Alignment.Center
//            ) {
//                CircularProgressIndicator()
//            }
//        } else {
//            // Application list
//            LazyColumn(
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(horizontal = 16.dp),
//                verticalArrangement = Arrangement.spacedBy(12.dp)
//            ) {
//                if (state.applications.isEmpty()) {
//                    item {
//                        Box(
//                            modifier = Modifier
//                                .fillMaxWidth()
//                                .padding(32.dp),
//                            contentAlignment = Alignment.Center
//                        ) {
//                            Text("No applications found")
//                        }
//                    }
//                } else {
//                    items(state.applications) { application ->
//                        ApplicationCard(
//                            application = application,
//                            onWithdraw = {
//                                viewModel.onEvent(
//                                    StudentDashboardEvent.DeleteApplication(application.id)
//                                )
//                            },
//                            onUpdate = {
//                                onNavigateToUpdate(application.id)
//                            }
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//
////@Composable
////fun StatusIndicator(status: ApplicationStatus) {
////    val color = when (status) {
////        ApplicationStatus.PENDING -> Color(0xFFF39C12) // Orange
////        ApplicationStatus.ACCEPTED -> Color(0xFF2ECC71) // Green
////        ApplicationStatus.REJECTED -> Color(0xFFE74C3C) // Red
//////        ApplicationStatus.WITHDRAWN -> Color(0xFF7F8C8D) // Gray
////    }
////
////    Box(
////        modifier = Modifier
////            .size(12.dp)
////            .background(color, shape = RoundedCornerShape(50))
////    )
////}