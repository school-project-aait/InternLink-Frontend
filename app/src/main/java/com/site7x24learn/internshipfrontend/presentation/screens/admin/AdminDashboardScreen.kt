package com.site7x24learn.internshipfrontend.presentation.screens.admin





import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.site7x24learn.internshipfrontend.presentation.components.InternshipCard
import com.site7x24learn.internshipfrontend.presentation.navigation.Routes
import com.site7x24learn.internshipfrontend.presentation.viewmodels.InternshipListViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AdminDashboard(
    navController: NavController,
    viewModel: InternshipListViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()

    // Handle side effects like showing snackbars for delete operations
    LaunchedEffect(key1 = state.errorMessage) {
        state.errorMessage?.let { message ->
            // You'll need a scaffoldState here to show snackbar
            // scaffoldState.snackbarHostState.showSnackbar(message)
            viewModel.onErrorMessageShown()
        }
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Admin Dashboard") }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = { navController.navigate(Routes.ADD_INTERNSHIP) },
                icon = { Icon(Icons.Default.Add, contentDescription = "Add") },
                text = { Text("Add Internship") }
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                state.internships.isEmpty() -> {
                    Column(
                        modifier = Modifier.align(Alignment.Center),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text("No internships available")
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = { navController.navigate(Routes.ADD_INTERNSHIP) }
                        ) {
                            Text("Create First Internship")
                        }
                    }
                }
                else -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        contentPadding = PaddingValues(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp)
                    ) {
                        items(state.internships) { internship ->
                            InternshipCard(
                                internship = internship,
                                onClick = {
                                    navController.navigate("internship_detail/${internship.id}")
                                },
                                onEdit = {
                                    navController.navigate("edit_internship/${internship.id}")
                                },
                                onDelete = {
                                    viewModel.deleteInternship(internship.id)
                                },
                                isAdmin = true
                            )
                        }
                    }
                }
            }
        }
    }
}
