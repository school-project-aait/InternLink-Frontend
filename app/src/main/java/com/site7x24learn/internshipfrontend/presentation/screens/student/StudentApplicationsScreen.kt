package com.site7x24learn.internshipfrontend.presentation.screens.student


//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.platform.LocalContext
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.site7x24learn.internshipfrontend.presentation.components.HeaderComponent
//import com.site7x24learn.internshipfrontend.presentation.components.StudentApplicationCard
//import com.site7x24learn.internshipfrontend.presentation.navigation.Routes
//import com.site7x24learn.internshipfrontend.presentation.viewmodels.StudentApplicationsViewModel
//import java.text.SimpleDateFormat
//import java.util.*
//
//@Composable
//fun StudentApplicationsScreen(
//    navController: NavController,
//    viewModel: StudentApplicationsViewModel = hiltViewModel()
//) {
//    val state = viewModel.state.collectAsState().value
//
//    LaunchedEffect(Unit) {
//        viewModel.loadApplications()
//    }
//
//    Scaffold(
//        topBar = {
//            HeaderComponent(
//                title = "My Applications",
//                onBack = { navController.popBackStack() },
//                onLogout = {
//                    navController.navigate(Routes.LOGIN) { popUpTo(0) }
//                }
//            )
//        }
//    ) { paddingValues ->
//        Column(
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(paddingValues)
//                .padding(16.dp)
//        ) {
//            when {
//                state.isLoading -> CircularProgressIndicator(Modifier.align(Alignment.CenterHorizontally))
//                state.error != null -> Text(
//                    text = state.error ?: "Unknown error",
//                    color = MaterialTheme.colorScheme.error,
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//                state.applications.isEmpty() -> Text(
//                    "No applications found",
//                    modifier = Modifier.align(Alignment.CenterHorizontally)
//                )
//                else -> LazyColumn(
//                    verticalArrangement = Arrangement.spacedBy(16.dp),
//                    modifier = Modifier.fillMaxWidth()
//                ) {
//                    items(state.applications) { application ->
//                        // Add debug logging
//                        println("Application data: $application")
//
//                        StudentApplicationCard(
//                            application = application,
//                            onUpdateClick = {
//                                navController.navigate(
//                                    "${Routes.UPDATE_APPLICATION}/${application.id}"
//                                )
//                            },
//                            onWithdrawClick = { viewModel.deleteApplication(application.id) },
//                            canUpdate = application.internshipDeadline?.let { deadline ->
//                                isBeforeDeadline(deadline)
//                            } ?: false,
//                            modifier = Modifier.fillMaxWidth()
//                        )
//                    }
//                }
//            }
//        }
//    }
//}
//
//// Keep your existing isBeforeDeadline function
//
//// Date comparison that works on all API levels
//fun isBeforeDeadline(deadline: String?): Boolean {
//    if (deadline == null) return false
//
//    return try {
//        val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
//        val deadlineDate = format.parse(deadline)
//        val currentDate = Date()
//        deadlineDate?.after(currentDate) ?: false
//    } catch (e: Exception) {
//        false
//    }
//}