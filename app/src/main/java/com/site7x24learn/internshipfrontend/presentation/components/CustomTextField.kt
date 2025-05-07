package com.site7x24learn.internshipfrontend.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp


@Composable
fun CustomTextField(
    label: String,
    value: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPassword: Boolean = false,
    placeholder: String = "",
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(label) },
        placeholder = { Text(placeholder) },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 12.dp),
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        visualTransformation = if (isPassword) PasswordVisualTransformation()
        else VisualTransformation.None
    )
}