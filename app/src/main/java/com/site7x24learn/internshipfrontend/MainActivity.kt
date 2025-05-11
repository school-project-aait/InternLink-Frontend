package com.site7x24learn.internshipfrontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.site7x24learn.internshipfrontend.presentation.navigation.Routes
import com.site7x24learn.internshipfrontend.presentation.screens.admin.AddInternships
//import com.site7x24learn.internshipfrontend.presentation.screens.admin.AddInternships
import com.site7x24learn.internshipfrontend.presentation.screens.admin.AdminDashboard
//import com.site7x24learn.internshipfrontend.presentation.screens.admin.AdminDashboard

import com.site7x24learn.internshipfrontend.presentation.screens.auth.LoginScreen
import com.site7x24learn.internshipfrontend.presentation.screens.auth.SignUpScreen
import com.site7x24learn.internshipfrontend.presentation.screens.profile.ProfileScreen
import com.site7x24learn.internshipfrontend.presentation.screens.student.ApplyInternshipScreen
import com.site7x24learn.internshipfrontend.presentation.screens.student.StudentDashboardScreen


import com.site7x24learn.internshipfrontend.presentation.screens.student.StudentInternshipListScreen
import com.site7x24learn.internshipfrontend.presentation.theme.InternshipFrontendTheme
import com.site7x24learn.internshipfrontend.presentation.viewmodels.StudentDashboardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InternshipFrontendTheme {
                AuthApp()
            }
        }
    }
}

@Composable
fun AuthApp() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = Routes.SIGN_UP
    ) {
        composable(Routes.SIGN_UP) {
            SignUpScreen(navController = navController)
        }
        composable(Routes.LOGIN) {
            LoginScreen(navController = navController)
        }
        composable(Routes.ADMIN_DASHBOARD){
            AdminDashboard(navController=navController)
        }
        composable(Routes.INTERNSHIP_LIST){
            StudentInternshipListScreen(navController=navController)
        }
        composable(Routes.ADD_INTERNSHIP) {
            AddInternships(navController = navController) // Admin screen
        }
        composable(
            route = Routes.APPLY_INTERNSHIP,
            arguments = listOf(
                navArgument("internshipId") { type = NavType.IntType }
            )
        ) { backStackEntry ->
            val internshipId = backStackEntry.arguments?.getInt("internshipId")
            val dashboardViewModel: StudentDashboardViewModel = hiltViewModel()

            ApplyInternshipScreen(
                internshipId = internshipId,
                onBack = { navController.popBackStack() },
                onSuccess = {
                    // Force refresh before navigation
                    dashboardViewModel.refreshApplications()

                    // Navigate with clean back stack
                    navController.navigate(Routes.STUDENT_DASHBOARD) {
                        popUpTo(Routes.STUDENT_DASHBOARD) { inclusive = true }
                        launchSingleTop = true
                    }
                }
            )
        }


        composable(Routes.STUDENT_DASHBOARD) {
            StudentDashboardScreen(navController = navController)
        }
        composable(Routes.PROFILE) {
            ProfileScreen(navController = navController)
        }




//        composable(
//            route = Routes.APPLY_INTERNSHIP,
//            arguments = listOf(
//                navArgument("internshipId") { type = NavType.IntType }
//            )
//        ) { backStackEntry ->
//            val internshipId = backStackEntry.arguments?.getInt("internshipId")
//            val dashboardViewModel: StudentDashboardViewModel = hiltViewModel()
//
//            ApplyInternshipScreen(
//                internshipId = internshipId,
//                onBack = { navController.popBackStack() },
//                onSuccess = {
//                    // Force refresh before navigation
//                    dashboardViewModel.refreshApplications()
//
//                    // Navigate with clean back stack
//                    navController.navigate(Routes.STUDENT_DASHBOARD) {
//                        popUpTo(Routes.STUDENT_DASHBOARD) { inclusive = true }
//                        launchSingleTop = true
//                    }
//                }
//            )
//        }


        composable(Routes.STUDENT_DASHBOARD) {
            StudentDashboardScreen(navController = navController)
        }

//        composable(Routes.STUDENT_APPLICATIONS) {
//            StudentApplicationsScreen(navController = navController)
//        }
//        composable(Routes.STUDENT_DASHBOARD){
//            StudentDashboardScreen(navController = navController)
//        }
//        composable(Routes.STUDENT_DASHBOARD) {
//            StudentDashboardScreen(
//                onBack = { navController.popBackStack() },
//                onNavigateToUpdate = { applicationId ->
//                    navController.navigate(Routes.UPDATE_APPLICATION.replace("{applicationId}", applicationId.toString()))
//                }
//            )
//        }


//        composable(Routes.STUDENT_DASHBOARD) {
//            // Define a composable for student dashboard, if any
//        }
//        composable(
//            route = Routes.EDIT_INTERNSHIP,
//            arguments = listOf(
//                navArgument("internshipId") { type = NavType.IntType }
//            )
//        ) { backStackEntry ->
//            AddInternships(
//                navController = navController,
//                internshipId = backStackEntry.arguments?.getInt("internshipId")
//            )
//        }

        // Student Screens
//        composable(Routes.STUDENT_DASHBOARD) {
//            StudentDashboard(navController = navController)
//        }

        composable(Routes.PROFILE) {
            ProfileScreen(navController)
        }


    }
}
//
//@Composable
//fun Greeting(name: String, modifier: Modifier = Modifier) {
//    Text(
//        text = "Hello $name!",
//        modifier = modifier
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    InternshipFrontendTheme {
//        Greeting("Android")
//    }
//}
//@Preview
//@Composable
//fun PreviewLoginPage() {
//    AppTheme {
//        LoginScreen()
//    }
//}
//@Preview
//@Composable
//fun PreviewSignUpPage(){
//    AppTheme {
//        SignUpScreen()
//    }
//}
//InternshipFrontendTheme {
//    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//        Greeting(
//            name = "Android",
//            modifier = Modifier.padding(innerPadding)
//        )
//    }
//}