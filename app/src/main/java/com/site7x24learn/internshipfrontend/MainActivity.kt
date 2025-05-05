package com.site7x24learn.internshipfrontend

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.site7x24learn.internshipfrontend.presentation.navigation.Routes
import com.site7x24learn.internshipfrontend.presentation.screens.admin.AddInternships
import com.site7x24learn.internshipfrontend.presentation.screens.auth.LoginScreen
import com.site7x24learn.internshipfrontend.presentation.screens.auth.SignUpScreen
import com.site7x24learn.internshipfrontend.presentation.theme.InternshipFrontendTheme
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
        composable(Routes.ADMIN_DASHBOARD) {
            AddInternships(navController = navController) // Admin screen
        }
        composable(Routes.STUDENT_DASHBOARD) {
            // Define a composable for student dashboard, if any
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