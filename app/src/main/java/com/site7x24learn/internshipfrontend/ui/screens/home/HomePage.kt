package com.site7x24learn.internshipfrontend.ui.screens.home

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.navigation.NavHostController
import com.site7x24learn.internshipfrontend.R

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.rememberNavController
import com.site7x24learn.internshipfrontend.ui.components.HeaderComponent
import com.site7x24learn.internshipfrontend.ui.screens.navigation.AppNavGraph
import com.site7x24learn.internshipfrontend.viewmodel.LandingViewModel

@Composable
fun HomePage(
    navController: NavHostController,
    viewModel: LandingViewModel = viewModel()
) {
    LaunchedEffect(Unit) {
        viewModel.navigationEvents.collect { route ->
            when(route){
                "waiting"->navController.navigate(route)
                 "signup","login"->navController.navigate(route){
                    popUpTo("waiting"){inclusive=true}
                }

            }
//            navController.navigate(route) {
//                popUpTo("landing") { inclusive = true }
//            }
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
                onLogout = { viewModel.navigateWithLoading("login")},
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
                onClick = { viewModel.navigateWithLoading("signup") },
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
//@Preview(showBackground = true)
//@Composable
//fun HomePagePreview() {
//    val navController = rememberNavController() // fake navController for preview
//    HomePage(navController = navController)
//}

