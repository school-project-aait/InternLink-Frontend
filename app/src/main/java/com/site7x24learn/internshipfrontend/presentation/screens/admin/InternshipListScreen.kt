package com.site7x24learn.internshipfrontend.presentation.screens.admin



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.site7x24learn.internshipfrontend.domain.models.internships.Internship
import com.site7x24learn.internshipfrontend.presentation.components.HeaderComponent
import com.site7x24learn.internshipfrontend.presentation.components.InternshipCard
import com.site7x24learn.internshipfrontend.presentation.navigation.Routes
import com.site7x24learn.internshipfrontend.presentation.viewmodels.InternshipListViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun InternshipListScreen(navController: NavController?=null,
    viewModel: InternshipListViewModel = hiltViewModel())
{
    val internships by viewModel.internships.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // HeaderComponent placeholder
        HeaderComponent(
            onLogout = { navController?.navigate(Routes.LOGIN){
                popUpTo(Routes.INTERNSHIP_LIST){inclusive=true}
            } },

        )

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Internship Lists",
            style = MaterialTheme.typography.bodyLarge,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedTextField(
            value = searchQuery,
            onValueChange = viewModel::onSearchQueryChanged,
            modifier = Modifier.fillMaxWidth(),
            label = { Text("Search internships...") },
            singleLine = true
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            items(internships) { internship ->
                InternshipCard(internship = internship)
            }
        }
    }
}




























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