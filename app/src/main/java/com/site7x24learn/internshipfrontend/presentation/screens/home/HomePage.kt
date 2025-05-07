package com.site7x24learn.internshipfrontend.presentation.screens.home


import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.navigation.NavHostController
import com.site7x24learn.internshipfrontend.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.site7x24learn.internshipfrontend.presentation.components.HeaderComponent
import com.site7x24learn.internshipfrontend.presentation.navigation.Routes
import com.site7x24learn.internshipfrontend.presentation.viewmodels.LandingViewModel
import kotlinx.coroutines.flow.collectLatest

@Composable
fun HomePage(
    navController: NavHostController,
    viewModel: LandingViewModel= hiltViewModel()
) {
    val alreadyNavigated = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        if (!alreadyNavigated.value) {
            alreadyNavigated.value = true
            viewModel.navigationEvents.collectLatest { destination ->
                navController.navigate(destination)
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 24.dp, end = 24.dp)

        ){
            HeaderComponent(
                onLogout = { viewModel.navigateWithLoading(Routes.LOGIN)},
                buttonText = "Login"

            )

        }

        // Main Content
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(24.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(0.5f))

            // Image
            Image(
                painter = painterResource(id = R.drawable.landing_image),
                contentDescription = "App Logo",
                modifier = Modifier.size(220.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(6.dp))

            // Text
            Text(
                text = "Connecting Talent with Opportunity",
                style = MaterialTheme.typography.titleMedium,
                color = Color(0xFF1B2A80),
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(start = 4.dp, end = 4.dp)
            )

            Spacer(modifier = Modifier.height(28.dp))

            // Button
            Button(
                onClick = { viewModel.navigateWithLoading(Routes.SIGN_UP) },
                modifier = Modifier.width(200.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFF2196F3)
                )
            ) {
                Text("Get Started",
                    style = MaterialTheme.typography.titleLarge)
            }

            Spacer(modifier = Modifier.weight(1f))
        }
    }
}
