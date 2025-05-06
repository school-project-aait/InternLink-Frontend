package com.site7x24learn.internshipfrontend.presentation.screens.admin





//import androidx.compose.foundation.layout.*
//import androidx.compose.foundation.lazy.LazyColumn
//import androidx.compose.foundation.lazy.items
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Add
//import androidx.compose.material3.*
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.stringResource
//import androidx.compose.ui.unit.dp
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.NavController
//import com.site7x24learn.internshipfrontend.R
//import com.site7x24learn.internshipfrontend.domain.models.internships.Internship
//import com.site7x24learn.internshipfrontend.presentation.components.InternshipCard
//
//import com.site7x24learn.internshipfrontend.presentation.viewmodels.InternshipListViewModel
//import kotlinx.coroutines.flow.collectLatest
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun InternshipListScreen(
//    navController: NavController,
//    viewModel: InternshipListViewModel = hiltViewModel()
//) {
//    val state by viewModel.state.collectAsState()
//
//    // Show snackbar for errors
//    val snackbarHostState = SnackbarHostState()
//
//    LaunchedEffect(key1 = state.error) {
//        state.error?.let { error ->
//            snackbarHostState.showSnackbar(
//                message = error,
//                actionLabel = "Retry"
//            ).also {
//                if (it == SnackbarResult.ActionPerformed) {
//                    viewModel.loadInternships()
//                }
//            }
//        }
//    }
//
//    Scaffold(
//        snackbarHost = { SnackbarHost(snackbarHostState) },
//        topBar = {
//            CenterAlignedTopAppBar(
//                title = { Text("Internship Opportunities") }
//            )
//        },
//        floatingActionButton = {
//            FloatingActionButton(
//                onClick = { navController.navigate(Screen.AddInternship.route) }
//            ) {
//                Icon(Icons.Default.Add, contentDescription = "Add Internship")
//            }
//        }
//    ) { paddingValues ->
//        Box(
//            modifier = Modifier
//                .padding(paddingValues)
//                .fillMaxSize()
//        ) {
//            when {
//                state.isLoading && state.internships.isEmpty() -> {
//                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
//                }
//                state.internships.isEmpty() -> {
//                    Text(
//                        text = "No internships available",
//                        modifier = Modifier.align(Alignment.Center)
//                    )
//                }
//                else -> {
//                    LazyColumn(
//                        modifier = Modifier.fillMaxSize(),
//                        contentPadding = PaddingValues(8.dp),
//                        verticalArrangement = Arrangement.spacedBy(8.dp)
//                    ) {
//                        items(state.internships) { internship ->
//                            InternshipCard(
//                                internship = internship,
//                                onClick = {
//                                    navController.navigate(
//                                        Screen.InternshipDetail.createRoute(internship.id)
//                                    )
//                                },
//                                modifier = Modifier.fillMaxWidth()
//                            )
//                        }
//                    }
//                }
//            }
//        }
//    }
//}