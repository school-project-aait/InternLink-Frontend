package com.site7x24learn.internshipfrontend.ui.screens.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Popup
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.site7x24learn.internshipfrontend.ui.screens.admin.AddInternships
import com.site7x24learn.internshipfrontend.ui.screens.admin.AdminDashboardScreen
import com.site7x24learn.internshipfrontend.ui.screens.common.WaitingPage
import com.site7x24learn.internshipfrontend.ui.screens.home.HomePage
import com.site7x24learn.internshipfrontend.ui.screens.login.LoginScreen
import com.site7x24learn.internshipfrontend.ui.screens.login.SignUpScreen
import com.site7x24learn.internshipfrontend.ui.screens.student.StudentDashboardScreen

@Composable
fun AppNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "landing") {
        composable("landing"){
            HomePage(navController)
        }
        composable("signup") {
            SignUpScreen(navController)
        }
        composable("login") {
            LoginScreen(navController)
        }
        composable("admin_dashboard"){
            AdminDashboardScreen(navController)
        }
        composable("student_dashboard"){
            StudentDashboardScreen(navController)
        }
        composable("add_internship"){
            AddInternships(navController)
        }
        composable("waiting"){
            WaitingPage( navController)
        }
    }
}
