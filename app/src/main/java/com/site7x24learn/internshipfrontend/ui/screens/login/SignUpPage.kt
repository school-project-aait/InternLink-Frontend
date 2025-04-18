package com.site7x24learn.internshipfrontend.ui.screens.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun SignUpScreen(navController:NavHostController) {
    var name by remember { mutableStateOf("") }
    var gender by remember { mutableStateOf("") }
    var dob by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var address by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
            .padding(6.dp)
            .paddingFromBaseline(90.dp,60.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row {
//                title text Link
            Text(
                text = "Intern",
                fontSize = 40.sp,
                color = Color(0xFF1B2A80),
                modifier = Modifier.padding(bottom = 16.dp)
            )
            // Title text (Intern)
            Text(
                text = "Link",
                fontSize = 40.sp,
                color = Color(0xFF2196F3),
                modifier = Modifier.padding(bottom = 16.dp)

            )

        }
//        Text("Intern", fontSize = 30.sp, color = Color(0xFF2196F3))
//        Spacer(modifier = Modifier.height(8.dp))
//        Text("Link", fontSize = 30.sp, color = Color(0xFF1B2A80))
//        Spacer(modifier = Modifier.height(8.dp))
        Text("Let's get started!", fontSize = 34.sp, color = Color(0xFF1B2A80))
        Spacer(modifier = Modifier.height(8.dp))
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text("Already have an account?", fontSize = 16.sp)
            Spacer(modifier = Modifier.width(4.dp))
            Text(
                "Sign in",
                fontSize = 16.sp,
                color = Color(0xFF2196F3),
                modifier = Modifier.clickable {
                    navController.navigate("login")

                }
            )
        }
        Spacer(modifier = Modifier.height(32.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = MaterialTheme.shapes.medium,
            colors = CardDefaults.cardColors(containerColor = Color.White)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                CustomTextField("Name", name) { name = it }
                CustomTextField("Gender", gender) { gender = it }
                CustomTextField("Date of Birth", dob) { dob = it }
                CustomTextField("Contact number", phone, KeyboardType.Phone) { phone = it }
                CustomTextField("Address", address) { address = it }
                CustomTextField("Email", email, KeyboardType.Email) { email = it }
                CustomTextField("Enter Password", password, KeyboardType.Password, true) { password = it }
                CustomTextField("Confirm Password", confirmPassword, KeyboardType.Password, true) { confirmPassword = it }

                Spacer(modifier = Modifier.height(16.dp))
                Button(
                    onClick = {
                        navController.navigate("login")
                    },
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF2196F3),
                         )
                ) {
                    Text("Sign up", fontSize = 20.sp, color = Color.White)
                }
            }
        }
    }
}

@Composable
fun CustomTextField(
    label: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    onValueChange: (String) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth().padding(bottom = 16.dp)) {
        Text(text = label, fontSize = 20.sp, modifier = Modifier.padding(bottom = 4.dp))
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(text = label) },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = keyboardType),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}
